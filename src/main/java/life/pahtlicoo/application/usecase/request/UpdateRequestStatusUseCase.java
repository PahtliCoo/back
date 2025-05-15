package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.UpdateRequestStatusReqDTO;
import life.pahtlicoo.application.service.DiseaseService;

@ApplicationScoped
public class UpdateRequestStatusUseCase {
    @Inject
    DiseaseService diseaseService;
    public void execute(int requestId, UpdateRequestStatusReqDTO updateRequestStatusReqDTO){
        diseaseService.updateDiseaseName(requestId, updateRequestStatusReqDTO.getStatus());
    }
}
