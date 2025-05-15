package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.HistoricDataService;

@ApplicationScoped
public class DeleteHistoricDataUseCase {
    @Inject
    HistoricDataService historicDataService;

    public void execute(int historicDataId) {
        historicDataService.deleteHistoricData(historicDataId);
    }
}
