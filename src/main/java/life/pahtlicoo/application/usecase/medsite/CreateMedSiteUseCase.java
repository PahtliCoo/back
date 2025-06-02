package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.CreateMedSiteReqDTO;
import life.pahtlicoo.application.mapper.medsite.MedSiteDomainMapper;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.domain.model.MedSite;

@ApplicationScoped
public class CreateMedSiteUseCase {
    @Inject
    MedSiteService medSiteService;
    @Inject
    MedSiteDomainMapper medSiteDomainMapper;

    public boolean execute(CreateMedSiteReqDTO createMedSiteReqDTO) {
        try{
            MedSite medSite = medSiteDomainMapper.createMedSiteReqToDomainMedSite(createMedSiteReqDTO);
            if(medSite == null){
                return false;
            }
            return medSiteService.createMedSite(medSite);
        } catch (Exception e) {
            return false;
        }
    }
}
