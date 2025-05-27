/**
 * Med Site Entity Mapper
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.MedSite;
import life.pahtlicoo.infrastructure.entity.MedSiteEntity;
import life.pahtlicoo.infrastructure.entity.compositeid.MedSiteID;

@ApplicationScoped
public class MedSiteEntityMapper {

    public MedSite toDomain(MedSiteEntity medSiteEntity){
        return new MedSite(medSiteEntity.getMedSiteID().getMedId(), medSiteEntity.getMedSiteID().getSiteId(),
                medSiteEntity.getInitialQuantity(), medSiteEntity.getCurrentQuantity(), medSiteEntity.getCreatedAt(),
                medSiteEntity.getUpdatedAt());
    }

    public MedSiteEntity toEntity(MedSite medSite) {
        return new MedSiteEntity(new MedSiteID(medSite.getMedId(), medSite.getSiteId()), medSite.getInitialQuantity(),
                medSite.getCurrentQuantity(), medSite.getCreatedAt(), medSite.getUpdatedAt() );
    }
}
