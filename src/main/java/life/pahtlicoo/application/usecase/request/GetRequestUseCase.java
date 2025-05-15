package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.domain.model.Request;

@ApplicationScoped
public class GetRequestUseCase {
    @Inject
    RequestService requestService;

    public Request execute(int requestId) {
        return requestService.getRequest(requestId);
    }
}
