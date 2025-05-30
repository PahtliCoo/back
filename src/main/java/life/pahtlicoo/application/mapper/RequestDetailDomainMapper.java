

package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.requestdetail.CreateRequestDetailReqDTO;
import life.pahtlicoo.application.dto.requestdetail.MedicineQuantityReqDTO;
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
            detail.setQuantity(medicineQuantityReqDTO.getQuantity());
            detail.setMedId(medicineQuantityReqDTO.getMedId());
            requestDetailList.add(detail);
        }
        return requestDetailList;
    }
}
