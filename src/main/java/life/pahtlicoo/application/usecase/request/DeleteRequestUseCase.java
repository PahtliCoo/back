package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RequestService;

@ApplicationScoped
public class DeleteRequestUseCase {
    @Inject
    RequestService requestService;

    public void execute(int requestId) {
        requestService.deleteRequest(requestId);
    }
}
