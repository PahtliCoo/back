package life.pahtlicoo.application.usecase.meddisease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.meddisease.CreateMedDiseaseReqDTO;
import life.pahtlicoo.application.mapper.meddisease.MedDiseaseDomainMapper;
import life.pahtlicoo.application.service.MedDiseaseService;
import life.pahtlicoo.domain.model.MedDisease;

@ApplicationScoped
public class CreateMedDiseaseUseCase {
    @Inject
    MedDiseaseService medDiseaseService;
    @Inject
    MedDiseaseDomainMapper medDiseaseDomainMapper;

    public boolean execute(CreateMedDiseaseReqDTO createMedDiseaseReqDTO){
        try{
            MedDisease medDisease = medDiseaseDomainMapper.createMedDiseaseToDomain(createMedDiseaseReqDTO);
            if(medDiseaseService.createMedDisease(medDisease)){
                System.out.println("Creando correctamente");
                return true;
            }
            System.out.println("No se porque digo que es false");
            return false;

        } catch (Exception e) {
            return false;
        }
    }
}
