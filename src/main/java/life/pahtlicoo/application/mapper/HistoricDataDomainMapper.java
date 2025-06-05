/**
 * Historic Mapper DTO application.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.historicdata.CreateHistoricDataReqDTO;
import life.pahtlicoo.application.dto.historicdata.HistoricDataCSVDTO;
import life.pahtlicoo.application.dto.historicdata.SearchHistoricDataReqDTO;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.domain.model.Site;

@ApplicationScoped
public class HistoricDataDomainMapper {
    public HistoricData createHistoricDataToDomain(CreateHistoricDataReqDTO createHistoricDataReqDTO){
        HistoricData historicData = new HistoricData();
        historicData.setSiteId(createHistoricDataReqDTO.getSite_id());
        historicData.setMedId(createHistoricDataReqDTO.getMed_id());
        historicData.setDateYear(createHistoricDataReqDTO.getDate_year());
        historicData.setDateMonth(createHistoricDataReqDTO.getDate_month());
        historicData.setQuantity(createHistoricDataReqDTO.getQuantity());
        //TODO Historic data aqui nos pone 0
        return historicData;
    }

    public SearchHistoricDataReqDTO searchHistoricDataToDomain(Site site, Med med, HistoricDataCSVDTO historicDataCSVDTO){
        return new SearchHistoricDataReqDTO(site.getSiteId(), med.getMedId(),historicDataCSVDTO.getMonth(), historicDataCSVDTO.getYear(), historicDataCSVDTO.getQuantity());
    }
    public HistoricData createHistoricDataDomainFromSearchHistoricData(SearchHistoricDataReqDTO searchHistoricDataReqDTO ){
        HistoricData historicData = new HistoricData();
        historicData.setSiteId(searchHistoricDataReqDTO.getSiteId());
        historicData.setMedId(searchHistoricDataReqDTO.getMedId());
        historicData.setDateYear(searchHistoricDataReqDTO.getDateYear());
        historicData.setDateMonth(searchHistoricDataReqDTO.getDateMonth());
        historicData.setQuantity(searchHistoricDataReqDTO.getQuantity());
        // Siempre será 0 al insertar
        historicData.setProjectedQuantity(0);
        return historicData;
    }
}
