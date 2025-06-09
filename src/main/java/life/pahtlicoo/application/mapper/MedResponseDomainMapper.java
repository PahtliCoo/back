package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.med.MedResponseDTO;
import life.pahtlicoo.domain.model.Med;

@ApplicationScoped
public class MedResponseDomainMapper {
    public MedResponseDTO medDomainToResponseDTO(Med med) {
        return  new MedResponseDTO(med.getMedId(),med.getName());
    }

}
