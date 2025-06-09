/**
 * Disease Entity Mapper
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Disease;
import life.pahtlicoo.infrastructure.entity.DiseaseEntity;

@ApplicationScoped
public class DiseaseEntityMapper {
    public Disease toDomain(DiseaseEntity diseaseEntity){
        return new Disease(diseaseEntity.getDiseaseId(), diseaseEntity.getName(), diseaseEntity.getCreatedAt(),
                diseaseEntity.getUpdatedAt());
    }

    public DiseaseEntity toEntity(Disease disease){
        return new DiseaseEntity(disease.getDiseaseId(), disease.getName(),disease.getCreatedAt(),disease.getUpdatedAt());
    }
}
