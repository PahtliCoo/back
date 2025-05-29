package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.RequestResponseDTO;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.usecase.requestdetail.GetRequestDetailsUseCase;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.RequestDetail;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetAllRequestsByUserIdUseCase {
    @Inject
    RequestService requestService;
    @Inject
    GetRequestDetailsUseCase getRequestDetailsUseCase;

    public List<Request> execute(int userId){
        // 1. Traer todos los requests
        return requestService.getAllRequestsByUserId(userId);
    }
}
