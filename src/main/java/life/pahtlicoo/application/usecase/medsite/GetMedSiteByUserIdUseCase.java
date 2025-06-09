package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.GetUserMedSiteReqDTO;
import life.pahtlicoo.application.dto.medsite.MedSiteResDTO;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.domain.model.MedSite;
import life.pahtlicoo.domain.model.Site;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetMedSiteByUserIdUseCase {
    @Inject
    MedSiteService medSiteService;

    @Inject
    MedService medService;

    @Inject
    SiteService siteService;

    public List<MedSiteResDTO> execute(GetUserMedSiteReqDTO getUserMedSiteReqDTO) {
        List<MedSite> medSiteList = medSiteService.getMedSiteByUserId(getUserMedSiteReqDTO);

        if (medSiteList == null || medSiteList.isEmpty()) {
            return new ArrayList<>();
        }

        List<MedSiteResDTO> medSiteResDTOList = new ArrayList<>();
        for (MedSite medSite : medSiteList) {
            Med med = medService.getMed(medSite.getMedId());
            Site site = siteService.findSite(medSite.getSiteId());
            int total_used = medSite.getInitialQuantity() - medSite.getCurrentQuantity();
            MedSiteResDTO medSiteResDTO = new MedSiteResDTO(med.getName(), site.getName(), total_used,
                    medSite.getInitialQuantity(), medSite.getCurrentQuantity(), medSite.getMedId(),medSite.getSiteId());
            medSiteResDTOList.add(medSiteResDTO);
        }
        return medSiteResDTOList;
    }
}
