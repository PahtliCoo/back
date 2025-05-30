package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Request;

import java.util.Date;
import java.util.List;

public interface RequestRepository {
    public void createRequest(Request request);
    public Request getRequest(int requestId);
    public List<Request> getAllRequestsByUserId(int userId);
    public void updateRequestStatus(int requestId, int state);
    public void deleteRequest(int requestId);
    public List<Request> getAllRequestsByUserIdByStateAndDate(int sysUserId, int state, int year, int month, int day,int page);
    public List<Request> getAllRequestsByUserIdByState(int sysUserId, int state,int page);
}
