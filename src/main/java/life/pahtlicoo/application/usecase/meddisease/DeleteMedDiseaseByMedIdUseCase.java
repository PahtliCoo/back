package life.pahtlicoo.application.usecase.meddisease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedDiseaseService;

@ApplicationScoped
public class DeleteMedDiseaseByMedIdUseCase {
    @Inject
    MedDiseaseService medDiseaseService;

    public boolean execute(int medId) {
        try {
            if(medDiseaseService.deleteMedDiseaseByMedId(medId)) {
                return true;
            }
            return false;

        }catch (Exception e) {
            return false;
        }
    }
}
