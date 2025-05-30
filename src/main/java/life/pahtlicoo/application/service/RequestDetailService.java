/**
 * Request Detail Service.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.RequestDetail;
import life.pahtlicoo.domain.repository.RequestDetailRepository;

import java.util.List;
@ApplicationScoped
public class RequestDetailService {
    @Inject
    RequestDetailRepository requestDetailRepository;

    public boolean createRequestDetail(List<RequestDetail> requestDetailList){
        return requestDetailRepository.createRequestDetail(requestDetailList);
    }

    public List<RequestDetail> getRequestDetailList(int requestId){
        return requestDetailRepository.getRequestDetailsByRequestId(requestId);
    }

    public boolean deleteRequestDetail(int requestId){
        return requestDetailRepository.deleteRequestDetail(requestId);
    }
}
