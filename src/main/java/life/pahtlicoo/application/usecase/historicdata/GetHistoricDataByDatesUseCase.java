package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.application.dto.historicdata.GetHistoricDataReqDTO;

import java.util.List;

@ApplicationScoped
public class GetHistoricDataByDatesUseCase {
    @Inject
    HistoricDataService historicDataService;

    public List<HistoricData> execute(GetHistoricDataReqDTO dto) {
        return historicDataService.getHistoricDataByDateRange(dto.getYear(), dto.getStartMonth(), dto.getEndMonth(), dto.getType());
    }


}
