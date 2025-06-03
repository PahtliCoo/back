package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.domain.model.MedSite;

import java.util.List;

@ApplicationScoped
public class GetMedSiteBySiteIdUseCase {
    @Inject
    MedSiteService medSiteService;

    public List<MedSite> execute(int siteId) {
        try{
            List<MedSite> medSiteList = medSiteService.getMedSiteBySiteId(siteId);
            if (medSiteList.isEmpty()) {
                return null;
            }
            return medSiteList;

        }catch (Exception e) {
            return null;
        }

    }
}
