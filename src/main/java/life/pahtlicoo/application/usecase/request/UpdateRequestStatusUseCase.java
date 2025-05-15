package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.UpdateRequestStatusReqDTO;
import life.pahtlicoo.application.service.RequestService;

@ApplicationScoped
public class UpdateRequestStatusUseCase {
    @Inject
    RequestService requestService;
    public void execute(int requestId, UpdateRequestStatusReqDTO updateRequestStatusReqDTO){
        requestService.updateRequestStatus(requestId, updateRequestStatusReqDTO.getStatus());
    }
}
