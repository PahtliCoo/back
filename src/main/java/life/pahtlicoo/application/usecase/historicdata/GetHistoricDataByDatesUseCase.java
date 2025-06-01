package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.application.dto.historicdata.GetHistoricDataByDatesDTO;

import java.util.List;

@ApplicationScoped
public class GetHistoricDataByDatesUseCase {
    @Inject
    HistoricDataService historicDataService;

    public List<HistoricData> execute(GetHistoricDataByDatesDTO dto) {
        return historicDataService.getHistoricDataByDateRange(dto.getYear(), dto.getStartMonth(), dto.getEndMonth());
    }


}
