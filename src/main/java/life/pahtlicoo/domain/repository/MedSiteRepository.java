package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.MedSite;

import java.util.List;

public interface MedSiteRepository {
    public boolean createMedSite(MedSite medSite);
    public boolean deleteMedSite(int siteId,int medId);
    public List<MedSite> getMedSiteBySiteId(int siteId);
    public boolean updateMedSiteCurrentQuantity(MedSite medSite, int currentQuantity);
    public boolean updateMedSiteInventory(MedSite medSite, int newInitialQuantity);
    public List<MedSite> getMedSiteByMedId(int medId);
    public MedSite getMedSiteByMedIdAndSiteId(int medId, int siteId);
}
