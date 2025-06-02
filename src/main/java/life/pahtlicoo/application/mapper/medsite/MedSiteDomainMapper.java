package life.pahtlicoo.application.mapper.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.medsite.CreateMedSiteReqDTO;
import life.pahtlicoo.domain.model.MedSite;

@ApplicationScoped
public class MedSiteDomainMapper {
    public MedSite createMedSiteReqToDomainMedSite(CreateMedSiteReqDTO createMedSiteReqDTO){
        MedSite medSite = new MedSite();
        medSite.setMedId(createMedSiteReqDTO.getMedId());
        medSite.setSiteId(createMedSiteReqDTO.getSiteId());
        return medSite;
    }
}
