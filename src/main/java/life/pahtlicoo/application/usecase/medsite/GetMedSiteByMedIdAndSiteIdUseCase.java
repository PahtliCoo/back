package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.GetMedSiteByMedIdAndSiteId;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.domain.model.MedSite;

@ApplicationScoped
public class GetMedSiteByMedIdAndSiteIdUseCase {
    @Inject
    MedSiteService medSiteService;

    public MedSite execute(GetMedSiteByMedIdAndSiteId getMedSiteByMedIdAndSiteId) {
        try{
            MedSite medSite = medSiteService.getMedSiteByMedIdAndSiteId(getMedSiteByMedIdAndSiteId.getMedId(),
                    getMedSiteByMedIdAndSiteId.getSiteId());
            if (medSite == null) {
                return null;
            }
            return medSite;

        } catch (Exception e) {
            return null;
        }
    }
}
