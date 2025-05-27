/**
 * Site Entity Mapper.
 * @author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @since 2025-05-26
 */

package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.infrastructure.entity.SiteEntity;

@ApplicationScoped
public class SiteEntityMapper {
    public Site toDomain(SiteEntity siteEntity){
        return new Site(siteEntity.getSiteId(), siteEntity.getName(), siteEntity.getRegion(), siteEntity.getCreatedAt(),
                siteEntity.getUpdatedAt());
    }

    public SiteEntity toEntity(Site site){
        return new SiteEntity(site.getSiteId(), site.getName(), site.getRegion(), site.getCreatedAt(),
                site.getUpdatedAt());
    }
}
