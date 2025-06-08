package life.pahtlicoo.application.usecase.med;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;

@ApplicationScoped
public class GetMedByIdUseCase {

    @Inject
    MedService medService;

    public String execute(int medId) {
        Med med = medService.getMed(medId);
        if (med == null) {
            throw new RuntimeException("No se encontró el medicamento con ID: " + medId);
        }
        return med.getName();
    }
}
