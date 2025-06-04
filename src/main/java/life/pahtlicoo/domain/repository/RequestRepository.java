/**
 * Request class.
 * @author Santiago Moreno Lacalle Quintero (a01663197@tec.mx)
 * @co-author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-02
 */
package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Request;

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
    public List<Request> searchUserRequestsByName(int sysUserId, String search, int page);
}
//TODO ver si la mejor práctica es tener en un solo método del repository o bien tener metodos acá y en el use case
//manejar ya lo que sea que llegue