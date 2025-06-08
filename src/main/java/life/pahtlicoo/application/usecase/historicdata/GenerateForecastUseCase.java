package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastReqDTO;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastResDTO;
import life.pahtlicoo.application.service.HistoricDataService;

import java.util.List;

@ApplicationScoped
public class GenerateForecastUseCase {
    @Inject
    HistoricDataService historicDataService;

    public List<GenerateForecastResDTO> execute(GenerateForecastReqDTO generateForecastReqDTO) {
        return historicDataService.generateForecast(generateForecastReqDTO);
    }
}
