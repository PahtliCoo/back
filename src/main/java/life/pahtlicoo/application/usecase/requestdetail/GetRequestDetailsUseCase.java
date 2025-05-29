/**
 * Get all request detail Use case.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */

package life.pahtlicoo.application.usecase.requestdetail;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RequestDetailService;
import life.pahtlicoo.domain.model.RequestDetail;

import java.util.List;
@ApplicationScoped
public class GetRequestDetailsUseCase {
    @Inject
    RequestDetailService requestDetailService;

    public List<RequestDetail> execute(int requestId){
        return requestDetailService.getRequestDetailList(requestId);
    }
}
