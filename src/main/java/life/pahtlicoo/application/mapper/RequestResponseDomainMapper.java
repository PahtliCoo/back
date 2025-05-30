package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.request.RequestResponseDTO;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.Site;

@ApplicationScoped
public class RequestResponseDomainMapper {
    public RequestResponseDTO toRequestResponse(Request request, Site site) {
        return new RequestResponseDTO(request.getRequestId(),site.getName(),request.getName(),
                request.getDescription(),request.getState(),request.getCreatedAt());
    }
}
