package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Disease;
import life.pahtlicoo.infrastructure.entity.DiseaseEntity;

@ApplicationScoped
public class DiseaseEntityMapper {
    public Disease toDomain(DiseaseEntity diseaseEntity){
        Disease disease = new Disease();
        disease.setDiseaseId(diseaseEntity.getDiseaseId());
        disease.setName(diseaseEntity.getName());
        disease.setCreatedAt(diseaseEntity.getCreatedAt());
        disease.setUpdatedAt(diseaseEntity.getUpdatedAt());
        return disease;
    }

    public DiseaseEntity toEntity(Disease disease){
        DiseaseEntity diseaseEntity = new DiseaseEntity();
        diseaseEntity.setDiseaseId(disease.getDiseaseId());
        diseaseEntity.setName(disease.getName());
        diseaseEntity.setCreatedAt(disease.getCreatedAt());
        diseaseEntity.setUpdatedAt(disease.getUpdatedAt());
        return diseaseEntity;
    }
}
