package life.pahtlicoo.application.usecase.meddisease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedDiseaseService;
import life.pahtlicoo.domain.model.MedDisease;

@ApplicationScoped
public class GetMedDiseaseByMedIdUseCase {
    @Inject
    MedDiseaseService medDiseaseService;

    public MedDisease execute(int medId) {
        try {
            MedDisease medDisease = medDiseaseService.getMedDiseaseByMedId(medId);
            if(medDisease == null) {
                return null;
            }
            return medDisease;

        } catch (Exception e) {
            return null;
        }

    }
}
