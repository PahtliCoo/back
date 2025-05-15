package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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

    public void deleteHistoricData(int historicDataId) {
        historicDataRepository.deleteHistoricData(historicDataId);
    }
}
