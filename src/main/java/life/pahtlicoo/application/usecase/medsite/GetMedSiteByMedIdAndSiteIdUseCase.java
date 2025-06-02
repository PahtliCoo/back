package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.GetMedSiteByMedIdAndSiteIdReqDTO;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.domain.model.MedSite;

@ApplicationScoped
public class GetMedSiteByMedIdAndSiteIdUseCase {
    @Inject
    MedSiteService medSiteService;

    public MedSite execute(GetMedSiteByMedIdAndSiteIdReqDTO getMedSiteByMedIdAndSiteIdReqDTO) {
        try{
            MedSite medSite = medSiteService.getMedSiteByMedIdAndSiteId(getMedSiteByMedIdAndSiteIdReqDTO.getMedId(),
                    getMedSiteByMedIdAndSiteIdReqDTO.getSiteId());
            if (medSite == null) {
                return null;
            }
            return medSite;

        } catch (Exception e) {
            return null;
        }
    }
}
