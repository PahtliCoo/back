package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.UpdateMedSiteQuantityReqDTO;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.domain.model.MedSite;

@ApplicationScoped
public class UpdateMedSiteCurrentQuantityUseCase {
    @Inject
    MedSiteService medSiteService;

    public boolean execute(UpdateMedSiteQuantityReqDTO medSiteQuantityReqDTO) {
        try {
            MedSite medSite = medSiteService.getMedSiteByMedIdAndSiteId(medSiteQuantityReqDTO.getMedId(), medSiteQuantityReqDTO.getSiteId());
            if (medSite == null) {
                return false;
            }
            return medSiteService.updateMedSiteCurrentQuantity(medSite, medSiteQuantityReqDTO.getQuantity());
        } catch (Exception e) {
            return false;
        }
    }
}
