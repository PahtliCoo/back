package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastReqDTO;
import life.pahtlicoo.application.dto.historicdata.GetRecentHistoricDataResDTO;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.domain.repository.HistoricDataRepository;
import life.pahtlicoo.infrastructure.entity.HistoricDataEntity;
import life.pahtlicoo.infrastructure.mapper.HistoricDataEntityMapper;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class HistoricDataRepositoryImpl implements HistoricDataRepository, PanacheRepositoryBase<HistoricDataEntity, Integer> {
    @Inject
    HistoricDataEntityMapper historicDataEntityMapper;

    @Override
    @Transactional
    public void createHistoricData(HistoricData historicData) {
        HistoricDataEntity historicDataEntity = historicDataEntityMapper.toEntity(historicData);
        persist(historicDataEntity);
        historicData.setHistoricDataId(historicDataEntity.getHistoricDataId());
    }

    @Override
    public List<HistoricData> getAllHistoricDataBySiteId(int siteId) {
        List<HistoricDataEntity> historicDataEntities =find("siteId", siteId).list();
        return historicDataEntities.stream()
                .map(historicDataEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<HistoricData> getAllHistoricDataBySiteIdAndMedId(int siteId, int medId) {
        List<HistoricDataEntity> historicDataEntities = find("siteId = ?1 AND medId =?2", siteId, medId).list();
        return historicDataEntities.stream()
                .map(historicDataEntityMapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<HistoricData> getAllByYearAndMonthRange(int year, int startMonth, int endMonth, String type) {
        List<HistoricDataEntity> entities = find(
                "dateYear = ?1 AND dateMonth >= ?2 AND dateMonth <= ?3",
                year, startMonth, endMonth
        ).list();

        return entities.stream()
                .map(historicDataEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void deleteHistoricData(int historicDataId) {
        deleteById(historicDataId);
    }

    @Override
    public HistoricData getHistoricDataBySiteIdAndMedIdAndDate( HistoricData historicData){
        HistoricDataEntity historicDataEntity = find("siteId = ?1 AND medId = ?2 AND dateMonth = ?3 AND " +
                "dateYear = ?4", historicData.getSiteId(),
                historicData.getMedId(),
                historicData.getDateMonth(),
                historicData.getDateYear()).firstResult();

        if(historicDataEntity == null){
            return null;
        }

        return historicDataEntityMapper.toDomain(historicDataEntity);
    }

    @Override
    @Transactional
    public void updateHistoricDataByDateMedSite(List<HistoricData> historicDataList){
        for (int i = 0; i < historicDataList.size(); i++) {
            HistoricData historicData = historicDataList.get(i);
            HistoricDataEntity historicDataEntity = findById(historicData.getHistoricDataId());
            if(historicDataEntity != null){
                historicDataEntity.setQuantity(historicData.getQuantity());
            }
        }
    }

    @Override
    @Transactional
    public void createListOfHistoricData(List<HistoricData> historicDataList) {
        List<HistoricDataEntity> historicDataEntities = new ArrayList<>();
        try{
            for (int i = 0; i < historicDataList.size(); i++){
                HistoricData historicData = historicDataList.get(i);
                HistoricDataEntity historicDataEntity = historicDataEntityMapper.toEntity(historicData);
                historicDataEntities.add(historicDataEntity);
            }
            persist(historicDataEntities);
        }catch (Exception e){
            throw new Error(e);
        }
    }

    @Override
    @Transactional
    public void createOrUpdateForecastData(List<HistoricData> historicDataList) {
        for (HistoricData data : historicDataList) {
            HistoricDataEntity existing = find("siteId = ?1 AND medId = ?2 AND dateMonth = ?3 AND dateYear = ?4",
                    data.getSiteId(), data.getMedId(), data.getDateMonth(), data.getDateYear()).firstResult();

            if (existing != null) {
                existing.setProjectedQuantity(data.getProjectedQuantity());
            } else {
                HistoricDataEntity newEntity = historicDataEntityMapper.toEntity(data);
                persist(newEntity);
            }
        }
    }

    @Override
    @Transactional
    public List<GetRecentHistoricDataResDTO> getMostRecentHistoricData(int medId) {
        List<YearMonth> yearMonthList = new ArrayList<>();
        YearMonth current = YearMonth.now();

        for (int i = 6; i >= 1; i--) {
            yearMonthList.add(current.minusMonths(i));
        }

        StringBuilder whereClause = new StringBuilder();
        List<Object> params = new ArrayList<>();
        int paramIndex = 2;

        for (int i = 0; i < yearMonthList.size(); i++) {
            YearMonth ym = yearMonthList.get(i);
            if (i > 0) whereClause.append(" OR ");
            whereClause.append("(date_year = ?").append(paramIndex++).append(" AND date_month = ?").append(paramIndex++).append(")");
            params.add(ym.getYear());
            params.add(ym.getMonthValue());
        }

        String sql = """
            SELECT 
                date_year, date_month, SUM(quantity) AS quantity
            FROM historic_data
            WHERE med_id = ?1 AND (""" + whereClause + ") " + """
            GROUP BY date_year, date_month
            ORDER BY date_year, date_month
        """;

        var query = getEntityManager().createNativeQuery(sql);
        query.setParameter(1, medId);

        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 2, params.get(i));
        }

        List<Object[]> results = query.getResultList();

        Map<String, Integer> ymQuantityMap = new HashMap<>();
        for (Object[] row : results) {
            int year = (int) row[0];
            int month = (int) row[1];
            int quantity = ((Number) row[2]).intValue();

            String key = String.format("%04d-%02d", year, month);
            ymQuantityMap.put(key, quantity);
        }

        List<GetRecentHistoricDataResDTO> response = yearMonthList.stream()
                .map(ym -> {
                    String key = ym.toString();
                    return new GetRecentHistoricDataResDTO(
                            key,
                            ymQuantityMap.getOrDefault(key, 0)
                    );
                })
                .toList();

        return response;
    }

    @Override
    @Transactional
    public List<GetRecentHistoricDataResDTO> getHistoricDataByMedId(int medId) {
        String sql = """
        SELECT 
            date_year, date_month, SUM(quantity) AS quantity
        FROM historic_data
        WHERE med_id = ?1
        GROUP BY date_year, date_month
        ORDER BY date_year DESC, date_month DESC
        LIMIT 40
    """;

        List<Object[]> results = getEntityManager()
                .createNativeQuery(sql)
                .setParameter(1, medId)
                .getResultList();

        return results.stream()
                .map(row -> {
                    int year = ((Number) row[0]).intValue();
                    int month = ((Number) row[1]).intValue();
                    int quantity = ((Number) row[2]).intValue();

                    String formattedMonth = String.format("%04d-%02d", year, month);
                    return new GetRecentHistoricDataResDTO(formattedMonth, quantity);
                })
                .toList();
    }

    @Override
    @Transactional
    public List<GetRecentHistoricDataResDTO> getPredictiveDataByMedId(int medId) {
        String sql = """
            SELECT 
                date_year, date_month, SUM(projected_quantity) AS quantity
            FROM historic_data
            WHERE med_id = ?1
            GROUP BY date_year, date_month
            ORDER BY date_year DESC, date_month DESC
            LIMIT 40
        """;

        List<Object[]> results = getEntityManager()
                .createNativeQuery(sql)
                .setParameter(1, medId)
                .getResultList();

        return results.stream()
                .map(row -> {
                    int year = ((Number) row[0]).intValue();
                    int month = ((Number) row[1]).intValue();
                    int quantity = ((Number) row[2]).intValue();

                    String formattedMonth = String.format("%04d-%02d", year, month);
                    return new GetRecentHistoricDataResDTO(formattedMonth, quantity);
                })
                .toList();

    }
}
