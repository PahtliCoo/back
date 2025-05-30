/**
 * Delete Request Use Case
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-30
 */
package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.usecase.requestdetail.DeleteAllRequestDetailUseCase;

@ApplicationScoped
public class DeleteRequestUseCase {
    @Inject
    RequestService requestService;

    @Inject
    DeleteAllRequestDetailUseCase deleteAllRequestDetailUseCase;

    @Transactional
    public Boolean execute(int requestId) {
        try{
            // 1. Eliminar Request Detail
            deleteAllRequestDetailUseCase.execute(requestId);
            // 2. Eliminar el Request
            return requestService.deleteRequest(requestId);

        }catch (Exception e) {
            //Hubo error al borrar
            return false;

        }

    }
}
