package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.SearchHistoricDataReqDTO;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.domain.repository.HistoricDataRepository;

import java.util.List;

@ApplicationScoped
public class HistoricDataService {
    @Inject
    HistoricDataRepository historicDataRepository;

    public void createHistoricData(HistoricData historicData) {
        historicDataRepository.createHistoricData(historicData);
    }

    public List<HistoricData> getAllHistoricDataBySiteId(int siteId) {
        return historicDataRepository.getAllHistoricDataBySiteId(siteId);
    }

    public List<HistoricData> getHistoricDataByDateRange(int year, int startMonth, int endMonth) {
        return historicDataRepository.getAllByYearAndMonthRange(year, startMonth, endMonth);
    }


    public void deleteHistoricData(int historicDataId) {
        historicDataRepository.deleteHistoricData(historicDataId);
    }
    public HistoricData getHistoricDataBySiteIdAndMedIdAndDate(SearchHistoricDataReqDTO searchHistoricDataReqDTO){
        return historicDataRepository.getHistoricDataBySiteIdAndMedIdAndDate(searchHistoricDataReqDTO);
    }
    public void updateHistoricData(HistoricData historicData){
        historicDataRepository.updateHistoricData(historicData);
    }
}
