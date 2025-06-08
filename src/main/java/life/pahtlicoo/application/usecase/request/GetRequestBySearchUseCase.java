/**
 * Request By Search Use case
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-30
 */
package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.RequestResponseDTO;
import life.pahtlicoo.application.mapper.RequestResponseDomainMapper;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.domain.model.SysUser;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetRequestBySearchUseCase {
    @Inject
    RequestService requestService;
    @Inject
    SiteService siteService;
    @Inject
    SysUserService sysUserService;
    @Inject
    RequestResponseDomainMapper requestResponseDomainMapper;

    public List<RequestResponseDTO> execute(int page,String search) {
        if(search == null || search.isEmpty()) {
            return null;
        }
        List<Request> requestList = requestService.getAllRequestsBySearch(search,page);
        if(requestList == null) {
            return null;
        }
        // Make change from domain to RequestResponse
        List<RequestResponseDTO> requestResponseDTOList = new ArrayList<>();
        for (Request request : requestList) {
            //Get the correct site name
            SysUser sysUser = sysUserService.getSysUserByUserId(request.getSysUserId());
            Site siteTemp = siteService.findSite(sysUser.getSiteId());
            RequestResponseDTO requestResponseDTO = requestResponseDomainMapper.toRequestResponse(request, siteTemp);
            requestResponseDTOList.add(requestResponseDTO);
        }
        // Check that it does have data
        if(requestResponseDTOList.isEmpty()){
            return null;
        }
        // Return result
        return requestResponseDTOList;
    }
}
