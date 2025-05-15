package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.historicdata.CreateHistoricDataReqDTO;
import life.pahtlicoo.domain.model.HistoricData;

@ApplicationScoped
public class HistoricDataDomainMapper {
    public HistoricData createHistoricDataToDomain(CreateHistoricDataReqDTO createHistoricDataReqDTO){
        HistoricData historicData = new HistoricData();
        historicData.setSiteId(createHistoricDataReqDTO.getSite_id());
        historicData.setMedId(createHistoricDataReqDTO.getMed_id());
        historicData.setYear(createHistoricDataReqDTO.getYear());
        historicData.setMonth(createHistoricDataReqDTO.getMonth());
        historicData.setQuantity(createHistoricDataReqDTO.getQuantity());
        return historicData;
    }
}
