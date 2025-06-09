package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.meddisease.CreateMedDiseaseReqDTO;
import life.pahtlicoo.domain.model.MedDisease;

@ApplicationScoped
public class MedDiseaseDomainMapper {
    public MedDisease createMedDiseaseToDomain(CreateMedDiseaseReqDTO createMedDiseaseReqDTO){
      MedDisease medDisease = new MedDisease();
      medDisease.setDiseaseId(createMedDiseaseReqDTO.getDiseaseId());
      medDisease.setMedId(createMedDiseaseReqDTO.getMedId());
      return medDisease;
    }
}
