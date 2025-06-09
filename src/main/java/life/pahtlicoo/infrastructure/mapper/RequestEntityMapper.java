/**
 * Request Entity Mapper.
 * @author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-05-26
 */

package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.infrastructure.entity.RequestEntity;

@ApplicationScoped
public class RequestEntityMapper {
    public Request toDomain(RequestEntity requestEntity){
        return new Request(requestEntity.getRequestId(), requestEntity.getSysUserId(), requestEntity.getState(),
                requestEntity.getName(), requestEntity.getDescription(), requestEntity.getCreatedAt(),
                requestEntity.getUpdatedAt());
    }

    public RequestEntity toEntity(Request request){
        return new RequestEntity(request.getRequestId(),request.getSysUserId(),request.getState(),
                request.getName(), request.getDescription(), request.getCreatedAt(), request.getUpdatedAt());
    }
}
