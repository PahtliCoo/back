package life.pahtlicoo.application.usecase.site;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.domain.model.Site;

@ApplicationScoped
public class GetSiteByIdUseCase {

    @Inject
    SiteService siteService;

    public String execute(int siteId) {
        Site site = siteService.findSite(siteId);
        if (site == null) {
            throw new RuntimeException("No se encontró el sitio con ID: " + siteId);
        }
        return site.getName();
    }


}
