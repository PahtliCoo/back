package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.historicdata.SearchHistoricDataReqDTO;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.domain.repository.HistoricDataRepository;
import life.pahtlicoo.infrastructure.entity.HistoricDataEntity;
import life.pahtlicoo.infrastructure.mapper.HistoricDataEntityMapper;

import java.util.List;

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
        List<HistoricDataEntity> historicDataEntities =find("siteId", siteId).list(); //TODO es mejor con el question mark o sin el?
        return historicDataEntities.stream()
                .map(historicDataEntityMapper::toDomain)
                .toList();
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
    public HistoricData getHistoricDataBySiteIdAndMedIdAndDate(SearchHistoricDataReqDTO searchHistoricDataReqDTO){
        HistoricDataEntity historicDataEntity = find("siteId = ?1 AND medId = ?2 AND dateMonth = ?3 AND " +
                "dateYear = ?4", searchHistoricDataReqDTO.getSiteId(),
                searchHistoricDataReqDTO.getMedId(),
                searchHistoricDataReqDTO.getDateMonth(),
                searchHistoricDataReqDTO.getDateYear()).firstResult();

        if(historicDataEntity == null){
            return null;
        }

        return historicDataEntityMapper.toDomain(historicDataEntity);
    }

    @Override
    @Transactional
    public void updateHistoricData(HistoricData historicData){
        HistoricDataEntity.update("quantity = ?1 WHERE id = ?2", historicData.getQuantity(), historicData.getHistoricDataId());
    }
}
