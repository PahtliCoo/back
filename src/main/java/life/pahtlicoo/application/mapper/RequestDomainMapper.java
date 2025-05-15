package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.request.CreateRequestReqDTO;
import life.pahtlicoo.domain.model.Request;

@ApplicationScoped
public class RequestDomainMapper {
    public Request createRequestToDomain(CreateRequestReqDTO createRequestReqDTO){
        Request request = new Request();
        request.setUserId(createRequestReqDTO.getUser_id());
        request.setSiteId(createRequestReqDTO.getSite_id());
        request.setStatus(createRequestReqDTO.getStatus());
        request.setDescription(createRequestReqDTO.getDescription());
        return request;
    }
}
