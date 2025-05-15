package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.CreateRequestReqDTO;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.application.mapper.RequestDomainMapper;
import life.pahtlicoo.application.service.RequestService;

import java.time.OffsetDateTime;

@ApplicationScoped
public class CreateRequestUseCase {
    @Inject
    RequestService requestService;

    @Inject
    RequestDomainMapper requestDomainMapper;

    public void execute(CreateRequestReqDTO createRequestReqDTO) {
        Request request = requestDomainMapper.createRequestToDomain(createRequestReqDTO);
        requestService.createRequest(request);
    }
}
