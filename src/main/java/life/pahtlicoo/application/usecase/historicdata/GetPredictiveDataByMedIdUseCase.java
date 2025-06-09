package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastReqDTO;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastResDTO;
import life.pahtlicoo.application.dto.historicdata.GetRecentHistoricDataResDTO;
import life.pahtlicoo.application.service.ForecastService;
import life.pahtlicoo.application.service.HistoricDataService;

import java.util.List;

@ApplicationScoped
public class GetPredictiveDataByMedIdUseCase {
    @Inject
    HistoricDataService historicDataService;

    public List<GetRecentHistoricDataResDTO> execute(int medId) {
        return historicDataService.getPredictiveDataByMedId(medId);
    }
}
