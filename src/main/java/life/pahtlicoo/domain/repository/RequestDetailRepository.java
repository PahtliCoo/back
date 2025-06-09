/**
 * Request Detail Repository.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-28
 */
package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.RequestDetail;

import java.util.List;

public interface RequestDetailRepository {
    public boolean createRequestDetail(List<RequestDetail> requestDetailList);
    public List<RequestDetail> getRequestDetailsByRequestId(int requestId);
    public boolean deleteRequestDetail(int requestId);
}
