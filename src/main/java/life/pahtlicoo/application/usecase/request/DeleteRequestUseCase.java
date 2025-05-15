package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.repository.RequestRepository;

@ApplicationScoped
public class DeleteRequestUseCase {
    @Inject
    RequestRepository requestRepository;

    public void execute(int requestId) {
        requestRepository.deleteRequest(requestId);
    }
}
