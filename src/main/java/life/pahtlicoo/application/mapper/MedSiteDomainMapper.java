package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.medsite.CreateMedSiteReqDTO;
import life.pahtlicoo.domain.model.MedSite;

@ApplicationScoped
public class MedSiteDomainMapper {
    public MedSite createMedSiteReqToDomainMedSite(CreateMedSiteReqDTO createMedSiteReqDTO){
        MedSite medSite = new MedSite();
        medSite.setMedId(createMedSiteReqDTO.getMed_id());
        medSite.setSiteId(createMedSiteReqDTO.getSite_id());
        medSite.setCurrentQuantity(createMedSiteReqDTO.getCurrent_quantity());
        medSite.setInitialQuantity(createMedSiteReqDTO.getInitial_quantity());
        return medSite;
    } //TODO iff possible refactor
}
