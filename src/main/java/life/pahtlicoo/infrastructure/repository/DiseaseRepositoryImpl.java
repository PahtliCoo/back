package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Disease;
import life.pahtlicoo.domain.repository.DiseaseRepository;
import life.pahtlicoo.infrastructure.entity.DiseaseEntity;
import life.pahtlicoo.infrastructure.mapper.DiseaseEntityMapper;

@ApplicationScoped
public class DiseaseRepositoryImpl implements DiseaseRepository, PanacheRepositoryBase<DiseaseEntity, Integer> { //Por que usar panache repository base?
    //por que se le pasa un integer? Porque es el tipo de dato del Id

    @Inject
    DiseaseEntityMapper diseaseEntityMapper;

    @Override
    @Transactional
    public void createDisease (Disease disease){
        DiseaseEntity diseaseEntity = diseaseEntityMapper.toEntity(disease);
        persist(diseaseEntity);
        disease.setDiseaseId(diseaseEntity.getDiseaseId()); //Realmente ocupo esto?
    }

    @Override
    public Disease getDisease(int diseaseId) {
        DiseaseEntity diseaseEntity = findById(diseaseId);
        if(diseaseEntity==null) {
            return null;
        }
        return diseaseEntityMapper.toDomain(diseaseEntity);
    } //Los get no ocupan transactional, a menos que sea algo many to many

    @Override
    @Transactional
    public void updateDiseaseName(int diseaseId, String name){
        DiseaseEntity entity = findById(diseaseId);
        if (entity == null) {
            return; // o lanzar una excepción si quieres manejar "not found"
        }
        entity.setName(name);
    }

    @Override
    @Transactional
    public void deleteDisease(int diseaseId){
        deleteById(diseaseId);
    }
}
