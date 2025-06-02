package life.pahtlicoo.application.usecase.meddisease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedDiseaseService;

@ApplicationScoped
public class UpdateMedDiseaseByDiseaseIdUseCase {
    @Inject
    MedDiseaseService medDiseaseService;

    public boolean execute(int oldDiseaseId, int newDiseaseId) {
        try{
            if(medDiseaseService.updateMedDiseaseByDiseaseId(oldDiseaseId,newDiseaseId)){
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
}
