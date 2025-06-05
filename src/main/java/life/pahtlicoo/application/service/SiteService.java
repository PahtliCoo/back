/**
 * Site Service
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-30
 */
package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.domain.repository.SiteRepository;

@ApplicationScoped
public class SiteService {
    @Inject
    SiteRepository siteRepository;

    public void createSite(Site site){
        siteRepository.createSite(site);
    }
    public Site findSite(int siteId){
        return siteRepository.findSite(siteId);
    }
    public void updateSiteName(int siteId, String newName){
        siteRepository.updateSiteName(siteId, newName);
    }
    public void deleteSite(int siteId){
        siteRepository.deleteSite(siteId);
    }
    public Site findSiteByName(String siteName){
        return siteRepository.findByName(siteName);
    }

}
