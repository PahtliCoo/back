package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastReqDTO;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastResDTO;
import life.pahtlicoo.application.dto.historicdata.GetRecentHistoricDataResDTO;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.domain.repository.HistoricDataRepository;

import java.util.List;

@ApplicationScoped
public class HistoricDataService {
    @Inject
    HistoricDataRepository historicDataRepository;
    @Inject
    ForecastService forecastService;

    public void createHistoricData(HistoricData historicData) {
        historicDataRepository.createHistoricData(historicData);
    }
    public List<HistoricData> getAllHistoricDataBySiteId(int siteId) {
        return historicDataRepository.getAllHistoricDataBySiteId(siteId);
    }
    public List<HistoricData> getHistoricDataByDateRange(int year, int startMonth, int endMonth, String type) {
        return historicDataRepository.getAllByYearAndMonthRange(year, startMonth, endMonth, type);
    }
    public void deleteHistoricData(int historicDataId) {
        historicDataRepository.deleteHistoricData(historicDataId);
    }
    public HistoricData getHistoricDataBySiteIdAndMedIdAndDate( HistoricData historicData){
        return historicDataRepository.getHistoricDataBySiteIdAndMedIdAndDate( historicData);
    }
    public void updateHistoricDataByDateMedSite(List<HistoricData> historicData){
        historicDataRepository.updateHistoricDataByDateMedSite(historicData);
    }
    public void createListOfHistoricData(List<HistoricData> historicData){
        historicDataRepository.createListOfHistoricData(historicData);
    }
    public List<GenerateForecastResDTO> generateForecast(GenerateForecastReqDTO generateForecastReqDTO) {
        return forecastService.generateForecasts(generateForecastReqDTO); //delega al service de forecast
    }
    public List<GetRecentHistoricDataResDTO> getMostRecentHistoricData(int medId) {
        return historicDataRepository.getMostRecentHistoricData(medId);
    }
    public List<GetRecentHistoricDataResDTO> getHistoricDataByMedId(int medId) {
        return historicDataRepository.getHistoricDataByMedId(medId);
    }
    public List<GetRecentHistoricDataResDTO> getPredictiveDataByMedId(int medId) {
        return historicDataRepository.getPredictiveDataByMedId(medId);
    }
}
