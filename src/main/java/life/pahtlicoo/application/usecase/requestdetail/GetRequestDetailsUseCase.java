/**
 * Get all request detail Use case.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664142@tec.mx)
 * @since 2025-06-05
 */

package life.pahtlicoo.application.usecase.requestdetail;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.requestdetail.GetRequestDetailResDTO;
import life.pahtlicoo.application.service.MedService;
import life.pahtlicoo.application.service.RequestDetailService;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.domain.model.RequestDetail;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetRequestDetailsUseCase {
    @Inject
    RequestDetailService requestDetailService;
    @Inject
    MedService medService;

        public List<GetRequestDetailResDTO> execute(int requestId){
            List<RequestDetail> requestDetailList = requestDetailService.getRequestDetailList(requestId);

            if (requestDetailList == null || requestDetailList.isEmpty()) {
                return new ArrayList<>();
            }

            List<GetRequestDetailResDTO> requestDetailResDTOList = new ArrayList<>();
            for (RequestDetail requestDetail : requestDetailList) {
                //Get correct made name
                Med med = medService.getMed(requestDetail.getMedId());
                GetRequestDetailResDTO getRequestDetailResDTO = new GetRequestDetailResDTO(requestDetail.getQuantity(), med.getName());
                requestDetailResDTOList.add(getRequestDetailResDTO);
            }
            return requestDetailResDTOList;
    }
}
