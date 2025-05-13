package life.pahtlicoo.application.usecase.disease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.disease.UpdateDiseaseNameReqDTO;
import life.pahtlicoo.application.service.DiseaseService;

@ApplicationScoped
public class UpdateDiseaseNameUseCase {
    @Inject
    DiseaseService diseaseService;

    public void execute(int diseaseId, UpdateDiseaseNameReqDTO updateDiseaseNameReqDTO) {
        diseaseService.updateDiseaseName(diseaseId, updateDiseaseNameReqDTO.getName());
    }
}
