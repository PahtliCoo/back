package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastResDTO;
import life.pahtlicoo.application.dto.historicdata.SearchHistoricDataReqDTO;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.domain.repository.HistoricDataRepository;
import life.pahtlicoo.infrastructure.entity.HistoricDataEntity;
import life.pahtlicoo.infrastructure.mapper.HistoricDataEntityMapper;

import java.util.ArrayList;
import java.util.List;
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
}
