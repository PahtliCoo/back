package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.HistoricData;

import java.util.List;

public interface HistoricDataRepository {
    public void createHistoricData(HistoricData historicData);
    public HistoricData getHistoricData(int id);
    public List<HistoricData> getAllHistoricData();
    public void updateHistoricData(HistoricData historicData);
    public void deleteHistoricData(int id);
}
