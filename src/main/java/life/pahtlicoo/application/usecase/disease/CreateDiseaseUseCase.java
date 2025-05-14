package life.pahtlicoo.application.usecase.disease;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.disease.CreateDiseaseReqDTO;
import life.pahtlicoo.application.mapper.DiseaseDomainMapper;
import life.pahtlicoo.application.service.DiseaseService;
import life.pahtlicoo.domain.model.Disease;

import java.time.OffsetDateTime;

@ApplicationScoped
public class CreateDiseaseUseCase {
    @Inject
    DiseaseService diseaseService;

    @Inject
    DiseaseDomainMapper diseaseDomainMapper; //Por que marca un error si le pongo Inject?

    public void execute(CreateDiseaseReqDTO createDiseaseRequestDTO) {
        Disease disease = diseaseDomainMapper.createRequestToDomain(createDiseaseRequestDTO);
        disease.setCreatedAt(OffsetDateTime.now());
        disease.setUpdatedAt(OffsetDateTime.now()); //Esto está considerando UTC?
        diseaseService.createDisease(disease);
    }
}
