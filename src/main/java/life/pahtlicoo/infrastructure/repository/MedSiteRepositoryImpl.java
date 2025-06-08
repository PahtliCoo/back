/**
 * MedSite Repository Implementation
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.MedSite;
import life.pahtlicoo.domain.repository.MedSiteRepository;
import life.pahtlicoo.infrastructure.entity.MedEntity;
import life.pahtlicoo.infrastructure.entity.MedSiteEntity;
import life.pahtlicoo.infrastructure.entity.RequestDetailEntity;
import life.pahtlicoo.infrastructure.entity.SysUserEntity;
import life.pahtlicoo.infrastructure.mapper.MedSiteEntityMapper;
import life.pahtlicoo.infrastructure.mapper.RequestDetailEntityMapper;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MedSiteRepositoryImpl implements MedSiteRepository, PanacheRepositoryBase<MedSiteEntity,Integer> {
    @Inject
    MedSiteEntityMapper medSiteEntityMapper;

    //TODO: Dependiendo del tiempo, lo corregimos a void
    @Override
    @Transactional
    public void createMedSite(MedSite medSite){
        MedSiteEntity medSiteEntity = medSiteEntityMapper.toEntity(medSite);
        persist(medSiteEntity);
    }

    @Override
    @Transactional
    public boolean deleteMedSite(int siteId, int medId) {
        return MedSiteEntity.delete("medSiteID.medId = ?1 and medSiteID.siteId = ?2",medId,siteId) > 0;
    }

    @Override
    public List<MedSite> getMedSiteBySiteId(int siteId){
        List<MedSiteEntity> medSiteEntityList = find("medSiteID.siteId", siteId).list();
        if(medSiteEntityList.isEmpty()) {
            return null;
        }
        return medSiteEntityList.stream().map(medSiteEntityMapper::toDomain).toList();

    }
    @Override
    @Transactional
    public boolean updateMedSiteCurrentQuantity(MedSite medSite, int currentQuantity){
        MedSiteEntity medSiteEntity = find("medSiteID.medId = ?1 and medSiteID.siteId = ?2",medSite.getMedId(),medSite.getSiteId()).firstResult();
        if(medSiteEntity == null) {
            return false;
        }
        medSiteEntity.setCurrentQuantity(currentQuantity);
        return true;
    }

    @Override
    @Transactional
    public boolean updateMedSiteInventory(MedSite medSite, int newInitialQuantity){
        MedSiteEntity medSiteEntity =  find("medSiteID.medId = ?1 and medSiteID.siteId = ?2",medSite.getMedId(),medSite.getSiteId()).firstResult();
        if(medSiteEntity == null) {
            return false;
        }
        medSiteEntity.setInitialQuantity(newInitialQuantity);
        medSiteEntity.setCurrentQuantity(newInitialQuantity);
        return true;
    }
    @Override
    public List<MedSite> getMedSiteByMedId(int medId){
        List<MedSiteEntity> medSiteEntityList = find("medSiteID.medId", medId).list();
        if(medSiteEntityList.isEmpty()) {
            return null;
        }
        return medSiteEntityList.stream().map(medSiteEntityMapper::toDomain).toList();
    }
    @Override
    public MedSite getMedSiteByMedIdAndSiteId(int medId, int siteId){
        MedSiteEntity medSiteEntity = find("medSiteID.medId = ?1 and medSiteID.siteId = ?2",medId,siteId).firstResult();
        if(medSiteEntity == null) {
            return null;
        }
        return medSiteEntityMapper.toDomain(medSiteEntity);
    }

    @Override
    public List<MedSite> getMedSiteByUserId(int sysUserId, String medName, int page){
        SysUserEntity sysUser = SysUserEntity.findById(sysUserId);

        if (sysUser == null) {
            throw new IllegalArgumentException("SysUser not found");
        }

        boolean isLogisticsAdmin = sysUser.getCredentialId() == 2;

        StringBuilder query = new StringBuilder("""
            SELECT ms FROM MedSiteEntity ms
            JOIN MedEntity m ON ms.medSiteID.medId = m.medId
        """);

        List<Object> params = new ArrayList<>();
        int paramIndex = 1;

        if (!isLogisticsAdmin) {
            query.append("WHERE ms.medSiteID.siteId = ?").append(paramIndex++);
            params.add(sysUser.getSiteId());
        } else {
            query.append("WHERE 1=1 ");
        }

        if (medName != null && !medName.isBlank()) {
            query.append(" AND LOWER(m.name) LIKE ?").append(paramIndex);
            params.add("%" + medName.toLowerCase() + "%");
        }

        List<MedSiteEntity> medSiteEntities = find(
                query.toString(),
                Sort.descending("ms.updatedAt"),
                params.toArray()
        ).page(page, 5).list();

        return medSiteEntities.stream()
                .map(medSiteEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void registerNewMedSiteConsumption(int medId, int siteId, int consumption){

        MedSiteEntity medSiteEntity =  find("medSiteID.medId = ?1 and medSiteID.siteId = ?2", medId, siteId).firstResult();

        if (medSiteEntity == null) {
            throw new Error("No se encontró la relación med-site para los IDs proporcionados.");
        }

        int currentQuantity = medSiteEntity.getCurrentQuantity();

        if (consumption > currentQuantity) {
            throw new Error("El consumo excede la cantidad actual disponible.");
        }

        medSiteEntity.setCurrentQuantity(currentQuantity - consumption);
    }

    @Override
    @Transactional
    public void registerNewMedSiteAddition(int medId, int siteId, int addition){
        MedSiteEntity medSiteEntity =  find("medSiteID.medId = ?1 and medSiteID.siteId = ?2", medId, siteId).firstResult();

        if (medSiteEntity == null) {
            throw new Error("No se encontró la relación med-site para los IDs proporcionados.");
        }

        int currentQuantity = medSiteEntity.getCurrentQuantity();

        if (addition <= 0) {
            throw new Error("La adición debe ser mayor que 0");
        }

        medSiteEntity.setCurrentQuantity(currentQuantity + addition);
        medSiteEntity.setInitialQuantity(medSiteEntity.getInitialQuantity() + addition);
        //TODO hacemos la suma directa al initial, si no, ocupariamos tener una columna u otra tabla de logs
    }
}