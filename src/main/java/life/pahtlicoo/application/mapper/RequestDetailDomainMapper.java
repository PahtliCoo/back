

package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.request.CreateRequestReqDTO;
import life.pahtlicoo.application.dto.requestdetail.CreateRequestDetailReqDTO;
import life.pahtlicoo.application.dto.requestdetail.MedicineQuantityReqDTO;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.RequestDetail;

import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class RequestDetailDomainMapper {
    public List<RequestDetail> createRequestDetailToDomain(CreateRequestDetailReqDTO createRequestDetailReqDTO){
        List<RequestDetail> requestDetailList = new ArrayList<>();
        for (MedicineQuantityReqDTO medicineQuantityReqDTO : createRequestDetailReqDTO.getRequestDetailsList()) {
            RequestDetail detail = new RequestDetail();
            detail.setRequestId(createRequestDetailReqDTO.getRequestId());
            if(medicineQuantityReqDTO.getQuantity() <= 0){
                return null;
            }
            detail.setQuantity(medicineQuantityReqDTO.getQuantity());
            if(medicineQuantityReqDTO.getMedId() <= 0 ){
                return null;
            }
            detail.setMedId(medicineQuantityReqDTO.getMedId());
            requestDetailList.add(detail);
        }
        return requestDetailList;
    }

    public CreateRequestDetailReqDTO createRequestToRequestDetailReqDTO(Request request, CreateRequestReqDTO createRequestReqDTO){
        CreateRequestDetailReqDTO requestDetailReqDTO = new CreateRequestDetailReqDTO();
        requestDetailReqDTO.setRequestId(request.getRequestId());
        requestDetailReqDTO.setRequestDetailsList(createRequestReqDTO.getRequestDetailList());
        return requestDetailReqDTO;
    }
}
