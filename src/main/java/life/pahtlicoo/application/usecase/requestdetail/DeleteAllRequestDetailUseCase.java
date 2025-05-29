/**
 * Delete all instances of request Detail Use case.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.usecase.requestdetail;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RequestDetailService;
import life.pahtlicoo.domain.repository.RequestDetailRepository;

@ApplicationScoped
public class DeleteAllRequestDetailUseCase {
    @Inject
    RequestDetailService requestDetailService;
    public void execute(int requestId) {
        requestDetailService.deleteRequestDetail(requestId);
    }
}
