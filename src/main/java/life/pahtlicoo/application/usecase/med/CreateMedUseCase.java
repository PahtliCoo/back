/**
 * Create Med Use case
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-1
 */

package life.pahtlicoo.application.usecase.med;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;

@ApplicationScoped
public class CreateMedUseCase {
    @Inject
    MedService medService;

    @Transactional
    public boolean execute(String name) {
        try {
            if(name.isBlank()) {
                return false;
            }
            // 1. Create the med model
            Med med = new Med();
            med.setName(name);
            // 2. Returns boolean if created or not
            return medService.createMed(med);

        } catch (Exception e) {
            return false;
        }

    }

}
