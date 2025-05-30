package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.GetRequestFilterReqDTO;
import life.pahtlicoo.application.dto.request.RequestResponseDTO;
import life.pahtlicoo.application.mapper.RequestResponseDomainMapper;
import life.pahtlicoo.application.service.CredentialService;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.service.SiteService;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.Credential;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.Site;
import life.pahtlicoo.domain.model.SysUser;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetRequestByFilterUseCase {
    @Inject
    RequestService requestService;
    @Inject
    SysUserService sysUserService;
    @Inject
    CredentialService credentialService;
    @Inject
    SiteService siteService;
    @Inject
    RequestResponseDomainMapper requestResponseDomainMapper;

    public List<RequestResponseDTO> execute(int page, GetRequestFilterReqDTO getRequestFilterReqDTO) {
        try {
            int userId = getRequestFilterReqDTO.getUserId();
            Integer state = getRequestFilterReqDTO.getState();
            Integer year = getRequestFilterReqDTO.getYear();
            Integer month = getRequestFilterReqDTO.getMonth();
            Integer day = getRequestFilterReqDTO.getDay();

            // 1. Filter / validate data
            SysUser user = sysUserService.getSysUserByUid(userId);
            Credential credential = credentialService.getRole(2); // ROLE LOGISTICS ADMIN
            boolean isLogisticsAdmin = (user.getCredentialId() == credential.getCredentialId());


            List<Request> requestList = new ArrayList<>();

            // Case for logistics admin filter
            if (isLogisticsAdmin) {
                if (state == null && year == null && month == null && day == null) {
                    // Case 1: No filters selected
                    requestList = requestService.getAllRequest(page);
                } else if (state == null) {
                    // Case 2: Only date filter selected
                    requestList = requestService.getAllRequestsByDate(year, month, day, page);
                } else if (year == null || month == null || day == null) {
                    // Case 3: Only state filter selected
                    requestList = requestService.getAllRequestsByState(state, page);
                } else {
                    // Case 4: All filters selected
                    requestList = requestService.getAllRequestsByDateByState(state, year, month, day, page);
                }
            } else {
                if (state == null && year == null && month == null && day == null) {
                    // Case 1: No filters selected
                    requestList = requestService.getAllRequestsByUserId(userId);
                } else if (state == null) {
                    // Case 2: Only date filter selected
                    requestList = requestService.getAllRequestsByUserIdByDate(userId, year, month, day, page);
                } else if (year == null || month == null || day == null) {
                    // Case 3: Only state filter selected
                    requestList = requestService.getAllRequestsByUserIdByState(userId, state, page);
                } else {
                    // Case 4: All filters selected
                    requestList = requestService.getAllRequestsByUserIdByStateAndDate(userId, state, year, month, day, page);
                }
            }

            // Validate that we have the correct data
            if (requestList == null) {
                return null;
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
            // Check that it does have data
            if(requestResponseDTOList.isEmpty()){
                return null;
            }
            // Return result
            return requestResponseDTOList;

        } catch (Exception e ){
            // 1. Data not valid.
            return null;
        }

    }

}
