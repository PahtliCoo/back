/**
 * Med Disease Entity Mapper
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.MedDisease;
import life.pahtlicoo.infrastructure.entity.MedDiseaseEntity;
import life.pahtlicoo.infrastructure.entity.compositeid.MedDiseaseID;

@ApplicationScoped
public class MedDiseaseEntityMapper {
    public MedDisease toDomain(MedDiseaseEntity medDiseaseEntity) {
        return new MedDisease(medDiseaseEntity.getMedDiseaseID().getMedId(),
                medDiseaseEntity.getMedDiseaseID().getDiseaseId(), medDiseaseEntity.getCreatedAt(),
                medDiseaseEntity.getUpdatedAt());
    }

    public MedDiseaseEntity toEntity(MedDisease medDisease) {
        return new MedDiseaseEntity(new MedDiseaseID(medDisease.getMedId(), medDisease.getDiseaseId()),
                medDisease.getCreatedAt(), medDisease.getUpdatedAt());
    }
}
