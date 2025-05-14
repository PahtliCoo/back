package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Disease;
import life.pahtlicoo.domain.repository.DiseaseRepository;
import life.pahtlicoo.infrastructure.entity.DiseaseEntity;

@ApplicationScoped
public class DiseaseRepositoryImpl implements DiseaseRepository, PanacheRepositoryBase<DiseaseEntity,Integer> {
    public void createDisease(Disease disease){
        return;
    } //Usar create, evitar statements tipo SQL
    public Disease getDiseaseById(int diseaseId){
        return null;
    }
    public void updateDiseaseName(int diseaseId, String name){
        return;
    }
    public void deleteDisease(int diseaseId){return;}
}
