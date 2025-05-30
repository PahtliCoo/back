package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.GetRequestByStateReqDTO;
import life.pahtlicoo.application.dto.request.RequestResponseDTO;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.domain.model.Request;

import java.util.List;

@ApplicationScoped
public class GetAllRequestByUserIdAndStateUseCase {
    @Inject
    RequestService requestService;

    public List<Request> execute(int userId, int state,int page) {
        try{
            // Check that the data is valid
            if( state >= 5 || state < 0){
                return null;
            }
            // 1. get all the request requests that have a state and
            List<Request> requestList = requestService.getAllRequestsByUserIdByState(userId, state,page);

            // 2. Clean the data that were sending
            //Todo Send the correct data

            return requestList;
        }catch (Exception e) {
            // 2. There was an error in the request
            return null;
        }
    }
}
