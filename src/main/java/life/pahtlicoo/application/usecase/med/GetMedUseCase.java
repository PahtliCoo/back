package life.pahtlicoo.application.usecase.med;

import jakarta.inject.Inject;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;

public class GetMedUseCase {
    @Inject
    MedService medService;

    public Med execute(int medId) {
        try{
            Med med = medService.getMed(medId);
            if(med == null) {
                return null;
            }
            return med;
        }catch(Exception e){
            return null;
        }
    }
}
