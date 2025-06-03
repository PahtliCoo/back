package life.pahtlicoo.application.usecase.meddisease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedDiseaseService;
import life.pahtlicoo.domain.model.MedDisease;

@ApplicationScoped
public class GetMedDiseaseByDiseaseIdUseCase {
    @Inject
    MedDiseaseService medDiseaseService;

    public MedDisease execute(int diseaseId){
        try{
            MedDisease medDisease = medDiseaseService.getMedDiseaseByDiseaseId(diseaseId);
            if(medDisease == null){
                return null;
            }
            return medDisease;
        } catch (Exception e) {
            return null;
        }
    }
}
