package life.pahtlicoo.application.usecase.site;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.site.GetAllSitesResDTO;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.domain.model.Site;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetAllSitesUseCase {
    @Inject
    SiteService siteService;

    public List<GetAllSitesResDTO> execute() {
        List<Site> siteList =  siteService.findAllSites();

        if (siteList == null || siteList.isEmpty()) {
            return new ArrayList<>();
        }

        List<GetAllSitesResDTO> resDTOList = new ArrayList<>();
        for (Site site : siteList) {
            GetAllSitesResDTO resDTO = new GetAllSitesResDTO(site.getSiteId(), site.getName(), site.getRegion());
            resDTOList.add(resDTO);
        }
        return resDTOList;
    }
}
