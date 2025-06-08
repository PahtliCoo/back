package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.RegisterMedSiteConsumptionDTO;
import life.pahtlicoo.application.service.MedSiteService;

@ApplicationScoped
public class RegisterNewMedSiteConsumptionUseCase {
    @Inject
    MedSiteService medSiteService;
    public void execute(RegisterMedSiteConsumptionDTO registerMedSiteConsumptionDTO){
        medSiteService.registerNewMedSiteConsumption(registerMedSiteConsumptionDTO);
    }
}