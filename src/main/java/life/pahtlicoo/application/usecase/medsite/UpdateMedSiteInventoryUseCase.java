package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.UpdateMedSiteQuantityReqDTO;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.domain.model.MedSite;

@ApplicationScoped
public class UpdateMedSiteInventoryUseCase {
    @Inject
    MedSiteService medSiteService;

    public boolean execute(UpdateMedSiteQuantityReqDTO updateMedSiteQuantityReqDTO) {
        try {
            MedSite medSite = medSiteService.getMedSiteByMedIdAndSiteId(updateMedSiteQuantityReqDTO.getMedId(), updateMedSiteQuantityReqDTO.getSiteId());
            if (medSite == null) {
                return false;
            }
            return medSiteService.updateMedSiteInventory(medSite, updateMedSiteQuantityReqDTO.getQuantity());


        } catch (Exception e) {
            return false;
        }
    }
}
