package life.pahtlicoo.application.usecase.med;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.med.MedResponseDTO;
import life.pahtlicoo.application.mapper.MedResponseDomainMapper;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;

@ApplicationScoped
public class GetMedByNameUseCase {
    @Inject
    MedService medService;
    @Inject
    MedResponseDomainMapper medResponseDomainMapper;

    public MedResponseDTO execute(String searchName) {
        try{
            Med med = medService.getMedByName(searchName);
            if (med != null) {
                return medResponseDomainMapper.medDomainToResponseDTO(med);
            }

            return null;
        }catch (Exception e){
            return null;
        }
    }
}
