/**
 * Historic Mapper DTO application.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
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
        historicData.setDateYear(createHistoricDataReqDTO.getDate_year());
        historicData.setDateMonth(createHistoricDataReqDTO.getDate_month());
        historicData.setQuantity(createHistoricDataReqDTO.getQuantity());
        return historicData;
    }
}
