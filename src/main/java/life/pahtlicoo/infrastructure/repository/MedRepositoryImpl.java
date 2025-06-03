/**
 * Med Repository Implementation
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.domain.repository.MedRepository;
import life.pahtlicoo.infrastructure.entity.MedEntity;
import life.pahtlicoo.infrastructure.mapper.MedEntityMapper;

import java.util.List;


@ApplicationScoped
public class MedRepositoryImpl implements MedRepository , PanacheRepositoryBase<MedEntity,Integer> {
    @Inject
    MedEntityMapper medEntityMapper;

    @Override
    @Transactional
    public boolean createMed(Med med){
        MedEntity medEntity = medEntityMapper.toEntity(med);
        persist(medEntity);
        if(medEntity.isPersistent()){
            medEntity.setMedId(med.getMedId());
            return true;
        }
        return false;
    }

    @Override
    public Med getMed(int medId){
        MedEntity medEntity = findById(medId);
        if(medEntity == null){
            return null;
        }
        return medEntityMapper.toDomain(medEntity);
    }

    @Override
    public Med updateMedName(int medId, String name){
        MedEntity medEntity = findById(medId);
        if(medEntity == null){
            return null;
        }
        medEntity.setName(name);
        return medEntityMapper.toDomain(medEntity);
    }

    @Override
    public boolean deleteMed(int medId){
        return deleteById(medId);
    }

    @Override
    public List<Med> getAllMeds(){
        List<MedEntity> medEntityList = MedEntity.findAll().list();
        if(medEntityList.isEmpty()){
            return null;
        }
        return medEntityList.stream()
                .map(medEntityMapper::toDomain).toList();
    }

    @Override
    public List<Med> getMedsBySearchName(String name){
        List<MedEntity> medEntityList = MedEntity.find("name LIKE ?1", '%' + name +'%').list();
        if(medEntityList.isEmpty()){
            return null;
        }
        return medEntityList.stream()
                .map(medEntityMapper::toDomain).toList();
    }
}
