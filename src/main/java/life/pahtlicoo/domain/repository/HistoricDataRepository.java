package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.HistoricData;

import java.util.List;

public interface HistoricDataRepository {
    public void createHistoricData(HistoricData historicData);
    public List<HistoricData> getAllHistoricDataBySiteId(int siteId);
    //public void updateHistoricData(HistoricData historicData);
    public void deleteHistoricData(int historicDataId);
}
//TODO aqui debe haber un endpoint para cargar un friego de data
