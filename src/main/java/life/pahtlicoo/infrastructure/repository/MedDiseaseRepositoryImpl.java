/**
 * Med Disease repository Implementation
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */
package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.MedDisease;
import life.pahtlicoo.domain.repository.MedDiseaseRepository;
import life.pahtlicoo.infrastructure.entity.MedDiseaseEntity;
import life.pahtlicoo.infrastructure.entity.compositeid.MedDiseaseID;
import life.pahtlicoo.infrastructure.mapper.MedDiseaseEntityMapper;

@ApplicationScoped
public class MedDiseaseRepositoryImpl implements MedDiseaseRepository, PanacheRepositoryBase<MedDiseaseEntity,Integer> {
    @Inject
    MedDiseaseEntityMapper medDiseaseEntityMapper;

    @Override
    @Transactional
    public boolean createMedDisease(MedDisease medDisease){
        MedDiseaseEntity medDiseaseEntity = medDiseaseEntityMapper.toEntity(medDisease);
        persist(medDiseaseEntity);
        if(medDiseaseEntity.getCreatedAt() == null){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteMedDiseaseByMedId(int medId){
        return MedDiseaseEntity.delete("medDiseaseID.medId", medId) > 0;
    }

    @Override
    @Transactional
    public boolean deleteMedDiseaseByDiseaseId(int diseaseId){
        return MedDiseaseEntity.delete("medDiseaseID.diseaseId",diseaseId) > 0;
    }

    @Override
    @Transactional
    public boolean updateMedDiseaseByMedId(int oldMedId, int newMedId){
        MedDiseaseEntity medDiseaseEntity = MedDiseaseEntity.find("medDiseaseID.medId", oldMedId).firstResult();
        if(medDiseaseEntity == null){
            return false;
        }
        MedDiseaseID medDiseaseID = new MedDiseaseID(newMedId,medDiseaseEntity.getMedDiseaseID().getDiseaseId());
        medDiseaseEntity.setMedDiseaseID(medDiseaseID);
        return true;
    }

    @Override
    @Transactional
    public boolean updateMedDiseaseByDiseaseId(int oldDiseaseId,int newDiseaseId){
        MedDiseaseEntity medDiseaseEntity = MedDiseaseEntity.find("medDiseaseID.diseaseId", oldDiseaseId).firstResult();
        if(medDiseaseEntity == null){
            return false;
        }
        MedDiseaseID medDiseaseID = new MedDiseaseID(medDiseaseEntity.getMedDiseaseID().getMedId(),newDiseaseId);
        medDiseaseEntity.setMedDiseaseID(medDiseaseID);
        return true;
    }

    @Override
    public MedDisease getMedDiseaseByMedId(int medId){
        MedDiseaseEntity medDiseaseEntity = MedDiseaseEntity.find("medDiseaseID.medId", medId).firstResult();
        if(medDiseaseEntity == null){
            return null;
        }
        return medDiseaseEntityMapper.toDomain(medDiseaseEntity);
    }
    @Override
    public MedDisease getMedDiseaseByDiseaseId(int diseaseId){
        MedDiseaseEntity medDiseaseEntity = MedDiseaseEntity.find("medDiseaseID.diseaseId",diseaseId).firstResult();
        if(medDiseaseEntity == null){
            return null;
        }
        return medDiseaseEntityMapper.toDomain(medDiseaseEntity);
    }
}
