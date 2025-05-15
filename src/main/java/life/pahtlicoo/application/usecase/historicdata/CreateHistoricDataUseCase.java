package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.CreateHistoricDataReqDTO;
import life.pahtlicoo.application.mapper.HistoricDataDomainMapper;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.domain.model.HistoricData;

@ApplicationScoped
public class CreateHistoricDataUseCase {
    @Inject
    HistoricDataService historicDataService;
    @Inject
    HistoricDataDomainMapper historicDataDomainMapper;

    public void execute(CreateHistoricDataReqDTO createHistoricDataReqDTO) {
        HistoricData historicData = historicDataDomainMapper.createHistoricDataToDomain(createHistoricDataReqDTO);
        historicDataService.createHistoricData(historicData);
    }
}
