package life.pahtlicoo.application.usecase.meddisease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedDiseaseService;

@ApplicationScoped
public class UpdateMedDiseaseByMedIdUseCase {
    @Inject
    MedDiseaseService medDiseaseService;

    public boolean execute(int oldMedId, int newMedId) {
        try {
            if(medDiseaseService.updateMedDiseaseByMedId(oldMedId, newMedId)) {
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
}
