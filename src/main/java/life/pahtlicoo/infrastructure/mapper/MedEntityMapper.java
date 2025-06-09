/**
 * Med Entity Mapper
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-26
 */

package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.infrastructure.entity.MedEntity;

@ApplicationScoped
public class MedEntityMapper {
    public Med toDomain(MedEntity medEntity){
        return new Med(medEntity.getMedId(), medEntity.getName(), medEntity.getCreatedAt(), medEntity.getUpdatedAt());
    }

    public MedEntity toEntity(Med med){
        return new MedEntity(med.getMedId(), med.getName(), med.getCreatedAt(), med.getUpdatedAt());
    }
}
