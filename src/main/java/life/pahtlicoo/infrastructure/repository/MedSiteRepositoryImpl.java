package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.repository.MedSiteRepository;
import life.pahtlicoo.infrastructure.entity.MedEntity;

@ApplicationScoped
public class MedSiteRepositoryImpl implements MedSiteRepository, PanacheRepositoryBase<MedEntity,Integer> {


}
