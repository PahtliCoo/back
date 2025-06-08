/**
 * Site Repository.
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-30
 */
package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.domain.repository.SiteRepository;
import life.pahtlicoo.infrastructure.entity.SiteEntity;
import life.pahtlicoo.infrastructure.mapper.SiteEntityMapper;

import java.util.List;


@ApplicationScoped
public class SiteRepositoryImpl implements SiteRepository, PanacheRepositoryBase<SiteEntity, Integer> {
    @Inject
    SiteEntityMapper siteEntityMapper;

    @Override
    @Transactional
    public void createSite(Site site){
        SiteEntity siteEntity = siteEntityMapper.toEntity(site);
        persist(siteEntity);
        site.setSiteId(siteEntity.getSiteId());
    }

    @Override
    public Site findSite(int siteId){
        SiteEntity siteEntity = findById(siteId);
        if(siteEntity == null){
            return null;
        }
        return siteEntityMapper.toDomain(siteEntity);

    }

    @Override
    @Transactional
    public void updateSiteName(int siteId, String newName){
        SiteEntity siteEntity = findById(siteId);
        if(siteEntity == null){
            return;
        }
        siteEntity.setName(newName);
    }

    @Override
    @Transactional
    public void deleteSite(int siteId){
        deleteById(siteId);
    }

    @Override
    public Site findByName(String name){
        SiteEntity siteEntity = find("name", name.toLowerCase()).firstResult();
        if(siteEntity == null){
            return null;
        }
        return siteEntityMapper.toDomain(siteEntity);
    }

    @Override
    public List<Site> getAllSites(){
        List<SiteEntity> siteEntityList = SiteEntity.findAll().list();
        if(siteEntityList.isEmpty()){
            return null;
        }
        return siteEntityList.stream().map(siteEntityMapper::toDomain).toList();
    }

}
