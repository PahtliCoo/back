package life.pahtlicoo.application.usecase.disease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.DiseaseService;

@ApplicationScoped
public class DeleteDiseaseUseCase {
    @Inject
    DiseaseService diseaseService;

    public void execute(int diseaseId) {
        diseaseService.deleteDisease(diseaseId);
    }
}
