package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.domain.model.HistoricData;

import java.util.List;

@ApplicationScoped
public class GetHistoricDataBySiteIdUseCase {
    @Inject
    HistoricDataService historicDataService;

    public List<HistoricData> execute(int siteId) {
        return historicDataService.getAllHistoricDataBySiteId(siteId);
    }
}
