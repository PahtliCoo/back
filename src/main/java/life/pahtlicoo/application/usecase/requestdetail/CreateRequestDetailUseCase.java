/**
 * Request Detail Create.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.usecase.requestdetail;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.requestdetail.CreateRequestDetailReqDTO;
import life.pahtlicoo.application.mapper.RequestDetailDomainMapper;
import life.pahtlicoo.application.service.RequestDetailService;
import life.pahtlicoo.domain.model.RequestDetail;

import java.util.List;
@ApplicationScoped
public class CreateRequestDetailUseCase {
    @Inject
    RequestDetailService requestDetailService;
    @Inject
    RequestDetailDomainMapper requestDetailDomainMapper;

    public void execute(CreateRequestDetailReqDTO createRequestDetailReqDTO) {
        List<RequestDetail> requestDetailList = requestDetailDomainMapper.createRequestDetailToDomain(createRequestDetailReqDTO);
        requestDetailService.createRequestDetail(requestDetailList);
    }
}
