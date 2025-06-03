package life.pahtlicoo.application.usecase.med;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.service.MedService;

@ApplicationScoped
public class DeleteMedUseCase {
    @Inject
    MedService medService;

    @Transactional
    public boolean execute(int medId) {
        try{
            return medService.deleteMed(medId);
        } catch (Exception e) {
            return false;
        }
    }
}
