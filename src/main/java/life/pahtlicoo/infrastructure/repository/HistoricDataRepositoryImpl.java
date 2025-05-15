package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void deleteHistoricData(int historicDataId) {
        deleteById(historicDataId);
    }
}
