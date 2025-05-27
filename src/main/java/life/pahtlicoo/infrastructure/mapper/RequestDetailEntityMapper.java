/**
 * Request Detail Entity Mapper.
 * @author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.RequestDetail;
import life.pahtlicoo.infrastructure.entity.RequestDetailEntity;
import life.pahtlicoo.infrastructure.entity.compositeid.RequestDetailID;

@ApplicationScoped
public class RequestDetailEntityMapper {
    public RequestDetail toDomain(RequestDetailEntity requestDetailEntity){
        return new RequestDetail(requestDetailEntity.getRequestDetailID().getMedId(),
                requestDetailEntity.getRequestDetailID().getRequestId(), requestDetailEntity.getQuantity(),
                requestDetailEntity.getCreatedAt(), requestDetailEntity.getUpdatedAt());
    }

    public RequestDetailEntity toEntity(RequestDetail requestDetail){
        return new RequestDetailEntity(new RequestDetailID(requestDetail.getMedId(), requestDetail.getRequestId()),
                requestDetail.getQuantity(), requestDetail.getCreatedAt(), requestDetail.getUpdatedAt());
    }
}
