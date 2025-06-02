/**
 * MedSite Repository Implementation
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.MedSite;
import life.pahtlicoo.domain.repository.MedSiteRepository;
import life.pahtlicoo.infrastructure.entity.MedEntity;
import life.pahtlicoo.infrastructure.entity.MedSiteEntity;
import life.pahtlicoo.infrastructure.entity.RequestDetailEntity;
import life.pahtlicoo.infrastructure.mapper.MedSiteEntityMapper;
import life.pahtlicoo.infrastructure.mapper.RequestDetailEntityMapper;

import java.util.List;

@ApplicationScoped
public class MedSiteRepositoryImpl implements MedSiteRepository, PanacheRepositoryBase<MedSiteEntity,Integer> {
    @Inject
    MedSiteEntityMapper medSiteEntityMapper;


    @Override
    @Transactional
    public boolean createMedSite(MedSite medSite){
        MedSiteEntity medSiteEntity = medSiteEntityMapper.toEntity(medSite);
        persist(medSiteEntity);
        if(medSiteEntity.getCreatedAt() == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteMedSite(int siteId, int medId) {
        return MedSiteEntity.delete("medSiteId.medId = ?1 and medSiteId.siteId = ?2",medId,siteId) > 0;
    }

    @Override
    public List<MedSite> getMedSiteBySiteId(int siteId){
        List<MedSiteEntity> medSiteEntityList = find("medSiteId.siteId", siteId).list();
        if(medSiteEntityList.isEmpty()) {
            return null;
        }
        return medSiteEntityList.stream().map(medSiteEntityMapper::toDomain).toList();

    }
    @Override
    @Transactional
    public boolean updateMedSiteCurrentQuantity(MedSite medSite, int currentQuantity){
        MedSiteEntity medSiteEntity =  find("medSiteId.medId = ?1 and medSiteId.siteId = ?2",medSite.getMedId(),medSite.getSiteId()).firstResult();
        if(medSiteEntity == null) {
            return false;
        }
        medSiteEntity.setCurrentQuantity(currentQuantity);
        return true;
    }

    @Override
    @Transactional
    public boolean updateMedSiteInventory(MedSite medSite, int newInitialQuantity){
        MedSiteEntity medSiteEntity =  find("medSiteId.medId = ?1 and medSiteId.siteId = ?2",medSite.getMedId(),medSite.getSiteId()).firstResult();
        if(medSiteEntity == null) {
            return false;
        }
        medSiteEntity.setInitialQuantity(newInitialQuantity);
        medSiteEntity.setCurrentQuantity(newInitialQuantity);
        return false;
    }
    @Override
    public List<MedSite> getMedSiteByMedId(int medId){
        List<MedSiteEntity> medSiteEntityList = find("medSiteId.medId", medId).list();
        if(medSiteEntityList.isEmpty()) {
            return null;
        }
        return medSiteEntityList.stream().map(medSiteEntityMapper::toDomain).toList();
    }
    @Override
    public MedSite getMedSiteByMedIdAndSiteId(int medId, int siteId){
        MedSiteEntity medSiteEntity = find("medSiteId.medId = ?1 and medSiteId.siteId = ?2",medId,siteId).firstResult();
        if(medSiteEntity == null) {
            return null;
        }
        return medSiteEntityMapper.toDomain(medSiteEntity);
    }

}
