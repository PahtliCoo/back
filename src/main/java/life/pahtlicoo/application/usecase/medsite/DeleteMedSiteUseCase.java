package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.DeleteMedSiteReqDTO;
import life.pahtlicoo.application.service.MedSiteService;

@ApplicationScoped
public class DeleteMedSiteUseCase {
    @Inject
    MedSiteService medSiteService;
    public boolean execute(DeleteMedSiteReqDTO deleteMedSiteReqDTO) {
        try{
            if(medSiteService.deleteMedSite(deleteMedSiteReqDTO.getSiteId(),deleteMedSiteReqDTO.getMedId())){
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
