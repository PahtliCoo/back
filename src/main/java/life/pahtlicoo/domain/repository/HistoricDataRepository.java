package life.pahtlicoo.domain.repository;

import life.pahtlicoo.application.dto.historicdata.SearchHistoricDataReqDTO;
import life.pahtlicoo.domain.model.HistoricData;

import java.util.List;

public interface HistoricDataRepository {
    public void createHistoricData(HistoricData historicData);
    public List<HistoricData> getAllHistoricDataBySiteId(int siteId);
    public List<HistoricData> getAllByYearAndMonthRange(int year, int startMonth, int endMonth, String Type);
    public void updateHistoricDataByDateMedSite(List<HistoricData> historicData);
    public void deleteHistoricData(int historicDataId);
    public HistoricData getHistoricDataBySiteIdAndMedIdAndDate(HistoricData historicData);
    public boolean createListOfHistoricData(List<HistoricData> historicData);
}
//TODO aqui debe haber un endpoint para cargar un friego de data
