package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.request.CreateRequestReqDTO;
import life.pahtlicoo.application.dto.requestdetail.CreateRequestDetailReqDTO;
import life.pahtlicoo.application.mapper.RequestDetailDomainMapper;
import life.pahtlicoo.application.usecase.requestdetail.CreateRequestDetailUseCase;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.application.mapper.RequestDomainMapper;
import life.pahtlicoo.application.service.RequestService;

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

    @Transactional
    public boolean execute(CreateRequestReqDTO createRequestReqDTO) {
       try{
           // Chek that list is not empty
           if(createRequestReqDTO.getRequest_detail_list().isEmpty()){
               return false;
           }
           // Check state is not largar than 5 or lower than 0
           if(createRequestReqDTO.getState() >= 5 || createRequestReqDTO.getState() <= 0){
               return false;
           }

           // 1. Create the main request file
           Request request = requestDomainMapper.createRequestToDomain(createRequestReqDTO);
           if(requestService.createRequest(request)){
               //2. Create the request detail DTO
               CreateRequestDetailReqDTO createRequestDetailReqDTO = requestDetailDomainMapper.createRequestToRequestDetailReqDTO(request,createRequestReqDTO);
               // 3. Create the request detail
               return createRequestDetailUseCase.execute(createRequestDetailReqDTO);
           }
           return false;
       }catch (Exception e){
           // 4. Data failed to create
           return false;
       }
    }
}
