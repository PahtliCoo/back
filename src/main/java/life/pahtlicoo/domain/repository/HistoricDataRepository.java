package life.pahtlicoo.domain.repository;

import life.pahtlicoo.application.dto.historicdata.GetRecentHistoricDataResDTO;
import life.pahtlicoo.domain.model.HistoricData;

import java.util.List;

public interface HistoricDataRepository {
    public void createHistoricData(HistoricData historicData);
    public List<HistoricData> getAllHistoricDataBySiteId(int siteId);
    public List<HistoricData> getAllHistoricDataBySiteIdAndMedId(int siteId, int medId);
    public List<HistoricData> getAllByYearAndMonthRange(int year, int startMonth, int endMonth, String Type);
    public void updateHistoricDataByDateMedSite(List<HistoricData> historicData);
    public void deleteHistoricData(int historicDataId);
    public HistoricData getHistoricDataBySiteIdAndMedIdAndDate(HistoricData historicData);
    public void createListOfHistoricData(List<HistoricData> historicData);
    public void createOrUpdateForecastData(List<HistoricData> historicDataList);
    public List<GetRecentHistoricDataResDTO> getMostRecentHistoricData(int medId);
    public List<GetRecentHistoricDataResDTO> getHistoricDataByMedId(int medId);
    public List<GetRecentHistoricDataResDTO> getPredictiveDataByMedId(int medId);
}
