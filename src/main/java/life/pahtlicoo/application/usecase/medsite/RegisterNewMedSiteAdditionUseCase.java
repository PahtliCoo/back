package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.RegisterMedSiteAdditionDTO;
import life.pahtlicoo.application.service.MedSiteService;

@ApplicationScoped
public class RegisterNewMedSiteAdditionUseCase {
    @Inject
    MedSiteService medSiteService;

    public void execute(RegisterMedSiteAdditionDTO registerMedSiteAdditionDTO) {
        medSiteService.registerNewMedSiteAddition(registerMedSiteAdditionDTO);
    }
}
