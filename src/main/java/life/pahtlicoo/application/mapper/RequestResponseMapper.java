package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.request.RequestResponseDTO;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.RequestDetail;

import java.util.List;

@ApplicationScoped
public class RequestResponseMapper {
    public RequestResponseDTO toRequestResponseDTO(Request request, List<RequestDetail> requestDetail) {
        return null;
    }
}
