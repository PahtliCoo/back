package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.repository.RequestRepository;

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

    public void updateRequestStatus(int requestId, String status){
        requestRepository.updateRequestStatus(requestId, status);
    }

    public void deleteRequest(int requestId){
       requestRepository.deleteRequest(requestId);
    }
}
