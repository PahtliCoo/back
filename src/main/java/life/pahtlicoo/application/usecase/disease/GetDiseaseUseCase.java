package life.pahtlicoo.application.usecase.disease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.DiseaseService;
import life.pahtlicoo.domain.model.Disease;

@ApplicationScoped
public class GetDiseaseUseCase {
    @Inject
    DiseaseService diseaseService;

    public Disease execute(int diseaseId){
        return diseaseService.getDisease(diseaseId);
    }
}
