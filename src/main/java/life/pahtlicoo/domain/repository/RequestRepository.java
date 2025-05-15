package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Request;

import java.util.List;

public interface RequestRepository {
    public void createRequest(Request request);
    public Request getRequest(int requestId);
    public List<Request> getAllRequestsByUserId(int userId);
    public void updateRequestStatus(int requestId, String status);
    public void deleteRequest(int requestId);
}
