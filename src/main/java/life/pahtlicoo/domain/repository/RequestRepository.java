package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Request;

import java.util.Date;
import java.util.List;

public interface RequestRepository {
    public boolean createRequest(Request request);
    public Request getRequest(int requestId);
    public List<Request> getAllRequestsByUserId(int userId);
    public void updateRequestStatus(int requestId, int state);
    public boolean deleteRequest(int requestId);
    public List<Request> getAllRequestsByUserIdByStateAndDate(int sysUserId, int state, int year, int month, int day,int page);
    public List<Request> getAllRequestsByUserIdByState(int sysUserId, int state,int page);
    public List<Request> getAllRequestsByUserIdByDate(int sysUserId, int year, int month, int day,int page);
    public List<Request> getAllRequest(int page);
    public List<Request> getAllRequestsByDate(int year, int month, int day,int page);
    public List<Request> getAllRequestsByDateByState(int state,int year, int month, int day,int page);
    public List<Request> getAllRequestsByState(int state,int page);
    public List<Request> getAllRequestsBySearch(String search, int page);
}
