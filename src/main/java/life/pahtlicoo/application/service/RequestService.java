/**
 * Request Service
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-30
 */
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

    public boolean createRequest(Request request) {
        return requestRepository.createRequest(request);
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

    public Boolean deleteRequest(int requestId){
       return requestRepository.deleteRequest(requestId);
    }

    public List<Request> getAllRequestsByUserIdByState(int userId, int state,int page){
        return requestRepository.getAllRequestsByUserIdByState(userId, state,page);
    }

    public List<Request> getAllRequestsByUserIdByStateAndDate(int sysUserId, int state, int year, int month, int day,int page){
        return requestRepository.getAllRequestsByUserIdByStateAndDate(sysUserId, state, year, month, day,page);
    }

    public List<Request> getAllRequestsByUserIdByDate(int userId, int year, int month, int day,int page){
        return requestRepository.getAllRequestsByUserIdByDate(userId,year,month,day, page);
    }

    public List<Request> getAllRequest(int page){
        return requestRepository.getAllRequest(page);
    }
    public List<Request> getAllRequestsByDate(int year, int month, int day,int page){
        return requestRepository.getAllRequestsByDate(year,month,day,page);
    }
    public List<Request> getAllRequestsByDateByState(int state,int year, int month, int day,int page){
        return requestRepository.getAllRequestsByDateByState(state,year,month,day,page);
    }
    public List<Request> getAllRequestsByState(int state,int page){
        return requestRepository.getAllRequestsByState(state,page);
    }
    public List<Request> getAllRequestsBySearch(String search, int page){
        return requestRepository.getAllRequestsBySearch(search,page);
    }

}
