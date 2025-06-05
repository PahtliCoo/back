/**
 * Get all user requests and filter by name search
 * DEPRECATED
 * @Author Adolfo Hernandez Fernandez
 * @since 2025-06-04
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
public class SearchUserRequestsByNameUseCase {

    @Inject
    RequestService requestService;

    @Inject
    SysUserService sysUserService;

    @Inject
    SiteService siteService;

    @Inject
    RequestResponseDomainMapper requestResponseDomainMapper;

    public List<RequestResponseDTO> execute(int sys_user_id, int page, String name) {
        List<Request> requestList = requestService.searchUserRequestsByName(sys_user_id, name, page);

        if (requestList == null || requestList.isEmpty()) {
            return new ArrayList<>();
        }

        // Make change from domain to RequestResponse
        List<RequestResponseDTO> requestResponseDTOList = new ArrayList<>();
        for (Request request : requestList) {
            //Get the correct site name
            SysUser sysUser = sysUserService.getSysUserByUid(request.getSysUserId());
            Site siteTemp = siteService.findSite(sysUser.getSiteId());
            RequestResponseDTO requestResponseDTO = requestResponseDomainMapper.toRequestResponse(request, siteTemp);
            requestResponseDTOList.add(requestResponseDTO);
        }
        // Return result
        return requestResponseDTOList;
    }
}
