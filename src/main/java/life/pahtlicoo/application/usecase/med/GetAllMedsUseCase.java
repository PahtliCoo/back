package life.pahtlicoo.application.usecase.med;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.med.MedResponseDTO;
import life.pahtlicoo.application.mapper.med.MedResponseDomainMapper;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetAllMedsUseCase {
    @Inject
    MedService medService;
    @Inject
    MedResponseDomainMapper medResponseDomainMapper;

    public List<MedResponseDTO> execute() {
        try{
            List<Med> medList = medService.getAllMeds();
            if (medList == null){
                return null;
            }
            List<MedResponseDTO> medResponseDTOList = new ArrayList<>();
            for (Med med : medList) {
                MedResponseDTO medResponseDTO = medResponseDomainMapper.medDomainToResponseDTO(med);
                medResponseDTOList.add(medResponseDTO);
            }
            return medResponseDTOList;

        }catch(Exception e){
            return null;
        }

    }
}
