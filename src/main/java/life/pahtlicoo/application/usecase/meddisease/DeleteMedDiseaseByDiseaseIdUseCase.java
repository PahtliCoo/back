package life.pahtlicoo.application.usecase.meddisease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedDiseaseService;

@ApplicationScoped
public class DeleteMedDiseaseByDiseaseIdUseCase {
    @Inject
    MedDiseaseService medDiseaseService;

    public boolean execute(int diseaseId){
        try{
            if(medDiseaseService.deleteMedDiseaseByDiseaseId(diseaseId)){
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }

}
