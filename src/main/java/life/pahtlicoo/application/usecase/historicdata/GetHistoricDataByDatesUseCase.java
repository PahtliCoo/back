package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.domain.model.HistoricData;

import java.util.List;

@ApplicationScoped
public class GetHistoricDataByDatesUseCase {
    @Inject
    HistoricDataService historicDataService;

    public List<HistoricData> execute(int year, int startMonth, int endMonth) {
        return historicDataService.getHistoricDataByDateRange(year, startMonth, endMonth);
    }


}
