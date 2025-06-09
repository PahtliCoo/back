/**
 * MedSite Creation Use Case
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernández (a01664412@tec.mx)
 * @since 2025-06-06
 */
package life.pahtlicoo.application.usecase.medsite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.medsite.CreateMedSiteReqDTO;
import life.pahtlicoo.application.mapper.MedSiteDomainMapper;
import life.pahtlicoo.application.service.MedSiteService;
import life.pahtlicoo.domain.model.MedSite;

@ApplicationScoped
public class CreateMedSiteUseCase {
    @Inject
    MedSiteService medSiteService;
    @Inject
    MedSiteDomainMapper medSiteDomainMapper;

    public void execute(CreateMedSiteReqDTO createMedSiteReqDTO) {
        MedSite medSite = medSiteDomainMapper.createMedSiteReqToDomainMedSite(createMedSiteReqDTO);
        if(medSite == null){return ;}
        medSiteService.createMedSite(medSite);
    }
}
