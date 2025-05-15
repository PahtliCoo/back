package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.infrastructure.entity.HistoricDataEntity;

@ApplicationScoped
public class HistoricDataEntityMapper {

    public HistoricData toDomain(HistoricDataEntity historicDataEntity){
        return new HistoricData(historicDataEntity.getHistoricDataId(), historicDataEntity.getSiteId(),
                historicDataEntity.getYear(), historicDataEntity.getMonth(), historicDataEntity.getMedId(),
                historicDataEntity.getQuantity(), historicDataEntity.getCreatedAt(), historicDataEntity.getUpdatedAt());
    }

    public HistoricDataEntity toEntity(HistoricData historicData){
        return new HistoricDataEntity(historicData.getHistoricDataId(), historicData.getSiteId(),
                historicData.getYear(), historicData.getMonth(), historicData.getMedId(), historicData.getQuantity(),
                historicData.getCreatedAt(), historicData.getUpdatedAt());
    }

}
