package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.request.CreateRequestReqDTO;
import life.pahtlicoo.application.dto.request.RequestResponseDTO;
import life.pahtlicoo.application.dto.requestdetail.CreateRequestDetailReqDTO;
import life.pahtlicoo.application.mapper.RequestDetailDomainMapper;
import life.pahtlicoo.application.mapper.request.RequestResponseMapper;
import life.pahtlicoo.application.usecase.requestdetail.CreateRequestDetailUseCase;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.application.mapper.RequestDomainMapper;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.domain.model.RequestDetail;

import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class CreateRequestUseCase {
    @Inject
    RequestService requestService;

    @Inject
    RequestDomainMapper requestDomainMapper;

    @Inject
    CreateRequestDetailUseCase createRequestDetailUseCase;

    @Inject
    RequestDetailDomainMapper requestDetailDomainMapper;
    //TODO: Create the delete in case of error.
    public Boolean execute(CreateRequestReqDTO createRequestReqDTO) {
       try{
           // Checar que la lista no este vacia
           if(createRequestReqDTO.getRequestDetailList().isEmpty()){
               return false;
           }
           // 1. Create the main request file
           Request request = requestDomainMapper.createRequestToDomain(createRequestReqDTO);
           requestService.createRequest(request);
           //2. Create the request detail DTO
           CreateRequestDetailReqDTO createRequestDetailReqDTO = requestDetailDomainMapper.createRequestToRequestDetailReqDTO(request,createRequestReqDTO);
           // 3. Create the request detail
           createRequestDetailUseCase.execute(createRequestDetailReqDTO);
           return true;
       }catch (Exception e){
           // 4. Fallo algo, eliminar todos los casos generados

           return false;
       }
    }
}
