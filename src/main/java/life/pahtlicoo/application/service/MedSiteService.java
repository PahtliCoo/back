/**
 * MedSite Service
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernández (a01664412@tec.mx)
 * @since 2025-06-06
 */
package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.MedSite;
import life.pahtlicoo.domain.repository.MedSiteRepository;

import java.util.List;

@ApplicationScoped
public class MedSiteService {
    @Inject
    MedSiteRepository medSiteRepository;

    public void createMedSite(MedSite medSite){
        medSiteRepository.createMedSite(medSite);
    }
    public boolean deleteMedSite(int siteId,int medId){
        return medSiteRepository.deleteMedSite(siteId,medId);
    }
    public List<MedSite> getMedSiteBySiteId(int siteId){
        return medSiteRepository.getMedSiteBySiteId(siteId);
    }
    public boolean updateMedSiteCurrentQuantity(MedSite medSite, int currentQuantity){
        return medSiteRepository.updateMedSiteCurrentQuantity(medSite,currentQuantity);
    }
    public boolean updateMedSiteInventory(MedSite medSite, int newInitialQuantity){
        return medSiteRepository.updateMedSiteInventory(medSite,newInitialQuantity);
    }
    public List<MedSite> getMedSiteByMedId(int medId){
        return medSiteRepository.getMedSiteByMedId(medId);
    }
    public MedSite getMedSiteByMedIdAndSiteId(int medId, int siteId){
        return medSiteRepository.getMedSiteByMedIdAndSiteId(medId,siteId);
    }
}
