package life.pahtlicoo.application.usecase.med;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.med.MedResponseDTO;
import life.pahtlicoo.application.mapper.MedResponseDomainMapper;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.domain.model.Med;

import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class GetMedsBySearchNameUseCase {
    @Inject
    MedService medService;
    @Inject
    MedResponseDomainMapper medResponseDomainMapper;

    public List<MedResponseDTO> execute(String searchName) {
       try{
           List<Med> medList = medService.getMedsBySearchName(searchName);
           if (medList.isEmpty()) {
               return null;
           }
           List<MedResponseDTO> medResponseList = new ArrayList<>();
           for (Med med : medList) {
               MedResponseDTO medResponseDTO = medResponseDomainMapper.medDomainToResponseDTO(med);
               medResponseList.add(medResponseDTO);
           }

           return medResponseList;
       }catch (Exception e){
           return null;
       }
    }
}
