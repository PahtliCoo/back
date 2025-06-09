package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.GetMedSiteQuantityRequiredPerStateResDTO;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.domain.model.MedSite;
import life.pahtlicoo.domain.model.Site;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class GetMedSiteQuantityRequiredPerStateUseCase {

    @Inject
    MedSiteService medSiteService;

    @Inject
    SiteService siteService;

    public List<GetMedSiteQuantityRequiredPerStateResDTO> execute(int medId) {
        List<MedSite> medSites = medSiteService.getMedSiteByMedId(medId);

        if (medSites == null || medSites.isEmpty()) {
            return Collections.emptyList();
        }

        return medSites.stream()
                .map(ms -> {
                    Site site = siteService.findSite(ms.getSiteId());
                    String siteRegion = site != null ? site.getRegion() : "Sitio desconocido";
                    int quantity = ms.getInitialQuantity() - ms.getCurrentQuantity();
                    return new GetMedSiteQuantityRequiredPerStateResDTO(siteRegion, quantity);
                })
                .toList();
    }
}
