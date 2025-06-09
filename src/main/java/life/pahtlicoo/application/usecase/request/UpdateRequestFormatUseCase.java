package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.UpdateRequestFormatReqDTO;
import life.pahtlicoo.application.service.RequestService;

@ApplicationScoped
public class UpdateRequestFormatUseCase {
    @Inject
    RequestService requestService;

    public boolean execute(int request_id,UpdateRequestFormatReqDTO updateRequestFormatReqDTO) {
        try {
            // 1. Check if state is checked
            if(updateRequestFormatReqDTO.getState() != null) {
                requestService.updateRequestStatus(request_id, updateRequestFormatReqDTO.getState());
            }
            if(updateRequestFormatReqDTO.getDescription() != null) {
                requestService.updateRequestDescription(request_id, updateRequestFormatReqDTO.getDescription());
            }
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
