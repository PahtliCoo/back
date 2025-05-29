package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.usecase.requestdetail.DeleteAllRequestDetailUseCase;

@ApplicationScoped
public class DeleteRequestUseCase {
    @Inject
    RequestService requestService;

    @Inject
    DeleteAllRequestDetailUseCase deleteAllRequestDetailUseCase;

    //TODO: Hacer caso de checar que se hayan eliminado correctamente las cosas
    public void execute(int requestId) {
        try{
            // 1. Eliminar Request Detail
            deleteAllRequestDetailUseCase.execute(requestId);
            // 2. Eliminar el Request
            requestService.deleteRequest(requestId);

        }catch (Exception e) {
            //Hubo error al borrar

        }

    }
}
