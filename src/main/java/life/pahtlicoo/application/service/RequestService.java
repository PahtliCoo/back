package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.repository.RequestRepository;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class RequestService {
    @Inject
    RequestRepository requestRepository;

    public void createRequest(Request request){
        requestRepository.createRequest(request);
    }

    public Request getRequest(int requestId){
        return requestRepository.getRequest(requestId);
    }

    public List<Request> getAllRequestsByUserId(int userId){
        return requestRepository.getAllRequestsByUserId(userId);
    }

    public void updateRequestStatus(int requestId, int state){
        requestRepository.updateRequestStatus(requestId, state);
    }

    public void deleteRequest(int requestId){
       requestRepository.deleteRequest(requestId);
    }

    public List<Request> getAllRequestsByUserIdByState(int userId, int state){
        return requestRepository.getAllRequestsByUserIdByState(userId, state);
    }

    public List<Request> getAllRequestsByUserIdByStateAndDate(int sysUserId, int state, Date date){
        return requestRepository.getAllRequestsByUserIdByStateAndDate(sysUserId, state, date);
    }
}
