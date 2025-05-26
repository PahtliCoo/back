package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.infrastructure.entity.RequestEntity;

@ApplicationScoped
public class RequestEntityMapper {
    public Request toDomain(RequestEntity requestEntity){
        Request request = new Request();
        request.setRequestId(requestEntity.getRequestId());
        request.setSysUserId(requestEntity.getSysUserId());
        request.setState(requestEntity.getState());
        request.setDescription(requestEntity.getDescription());
        request.setCreatedAt(requestEntity.getCreatedAt());
        request.setUpdatedAt(requestEntity.getUpdatedAt());
        return request;
    }

    public RequestEntity toEntity(Request request){
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setRequestId(request.getRequestId());
        requestEntity.setUserId(request.getUserId());
        requestEntity.setState(request.getState());
        requestEntity.setDescription(request.getDescription());
        requestEntity.setCreatedAt(request.getCreatedAt());
        requestEntity.setUpdatedAt(request.getUpdatedAt());
        return requestEntity;
    }
}
