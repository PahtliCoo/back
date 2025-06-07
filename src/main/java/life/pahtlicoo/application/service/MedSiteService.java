/**
 * MedSite Service
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernández (a01664412@tec.mx)
 * @since 2025-06-06
 */
package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.GetUserMedSiteReqDTO;
import life.pahtlicoo.application.dto.medsite.RegisterMedSiteConsumptionDTO;
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

    //El get que jala chido
    public List<MedSite> getMedSiteByUserId(GetUserMedSiteReqDTO getUserMedSiteReqDTO){
        return medSiteRepository.getMedSiteByUserId(getUserMedSiteReqDTO.getSysUserId(), getUserMedSiteReqDTO.getMedName(), getUserMedSiteReqDTO.getPage());
    }

    public void RegisterNewMedSiteConsumption(RegisterMedSiteConsumptionDTO registerMedSiteConsumptionDTO){
        medSiteRepository.registerNewMedSiteConsumption(registerMedSiteConsumptionDTO.getMedId(), registerMedSiteConsumptionDTO.getSiteId(), registerMedSiteConsumptionDTO.getConsumption());
    }
}
