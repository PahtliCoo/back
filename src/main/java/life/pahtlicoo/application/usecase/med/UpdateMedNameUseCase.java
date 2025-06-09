package life.pahtlicoo.application.usecase.med;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;

@ApplicationScoped
public class UpdateMedNameUseCase {
    @Inject
    MedService medService;

    @Transactional
    public Med execute(int medId, String medName) {
        try{
            Med med = medService.updateMedName(medId, medName);
            if (med == null) {
                return null;
            }
            return med;
        } catch (Exception e) {
            return null;
        }
    }
}
