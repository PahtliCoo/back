package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Site;

import java.util.List;

public interface SiteRepository {
    public void createSite(Site site);
    public Site findSite(int siteId);
    public void updateSiteName(int siteId, String newName);
    public void deleteSite(int siteId);
    public Site findByName(String name);
    public List<Site> getAllSites();
}
