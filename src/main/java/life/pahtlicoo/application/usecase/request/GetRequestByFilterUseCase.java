package life.pahtlicoo.application.usecase.request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.request.GetRequestFilterReqDTO;
import life.pahtlicoo.application.service.CredentialService;
import life.pahtlicoo.application.service.RequestService;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.Credential;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.SysUser;

import java.util.List;

@ApplicationScoped
public class GetRequestByFilterUseCase {
    @Inject
    RequestService requestService;
    @Inject
    SysUserService sysUserService;
    @Inject
    CredentialService credentialService;

    public List<Request> execute(int page, GetRequestFilterReqDTO getRequestFilterReqDTO) {
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

            // State check
            if(state >= 5 || state <=0){
                return null;
            }

            // Day check
            if(day >= 32 || day <= 0){
                return null;
            }

            // Month check
            if(month >= 13 || month <= 0){
                return null;
            }

            // Case for logistics admin filter
            if(isLogisticsAdmin) {
                // 1. No filter is selected
                if (state == null && year == null && month == null && day == null) {
                    return requestService.getAllRequest(page);
                }

                // 2. Date filter selected
                if (state == null) {
                    return requestService.getAllRequestsByDate(year, month, day, page);
                }

                // 3. State filter selected
                if (month == null || year == null || day == null) {
                    return requestService.getAllRequestsByState(state, page);
                }

                // 5. ALl filter selected
                return requestService.getAllRequestsByDateByState( state, year, month, day, page);
            }

            //Any other user

            // 1. No filter is selected
            if (state == null && year == null && month == null && day == null) {
                return requestService.getAllRequestsByUserId(userId);
            }

            // 2. Date filter selected
            if (state == null) {
                return requestService.getAllRequestsByUserIdByDate(userId, year, month, day, page);
            }

            // 3. State filter selected
            if (month == null || year == null || day == null) {
                return requestService.getAllRequestsByUserIdByState(userId, state, page);
            }

            // 4. Al filter Selected
            return requestService.getAllRequestsByUserIdByStateAndDate(userId, state, year, month, day, page);

        } catch (Exception e ){
            // 1. Data not valid.
            return null;
        }

    }

}
