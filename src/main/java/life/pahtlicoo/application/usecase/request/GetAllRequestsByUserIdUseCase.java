package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.domain.model.Request;

import java.util.List;

@ApplicationScoped
public class GetAllRequestsByUserIdUseCase {
    @Inject
    RequestService requestService;

    public List<Request> execute(int userId){
        return requestService.getAllRequestsByUserId(userId);
    }
}
