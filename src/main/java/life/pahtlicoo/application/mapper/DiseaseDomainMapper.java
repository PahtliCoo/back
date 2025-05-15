package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.disease.CreateDiseaseReqDTO;
import life.pahtlicoo.domain.model.Disease;

@ApplicationScoped
public class DiseaseDomainMapper {
    public Disease createRequestToDomain(CreateDiseaseReqDTO diseaseRequestDTO) {
        Disease disease = new Disease();
        disease.setName(diseaseRequestDTO.getName());
        return disease;
    }
}
