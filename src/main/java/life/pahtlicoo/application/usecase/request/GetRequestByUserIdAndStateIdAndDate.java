package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.GetRequestStateAndDateReqDTO;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.domain.model.Request;

import java.util.List;

@ApplicationScoped
public class GetRequestByUserIdAndStateIdAndDate {
    @Inject
    RequestService requestService;

    public List<Request> execute(int userId,int state, int year, int month, int day,int page) {
        // Check value of state
        if(state >= 5 || state < 0) {
            return null;
        }
        // 1. Get all the requests depending on the date and state
        List<Request> requestList = requestService.getAllRequestsByUserIdByStateAndDate(userId,state,year,month,day,page);
        return requestList;

    }
}
