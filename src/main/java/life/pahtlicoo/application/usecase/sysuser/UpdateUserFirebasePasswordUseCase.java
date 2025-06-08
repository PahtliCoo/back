package life.pahtlicoo.application.usecase.sysuser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.sysuser.UpdatePasswordRequestDTO;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.SysUser;

@ApplicationScoped
@Transactional
public class UpdateUserFirebasePasswordUseCase {

    @Inject
    SysUserService sysUserService;

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";


    public boolean execute(UpdatePasswordRequestDTO updatePasswordRequestDTO) {
        try{
            SysUser sysUser = sysUserService.getSysUserByUserId(updatePasswordRequestDTO.getSys_user_id());
            if (sysUser == null) {
                return false;
            }
            if (!updatePasswordRequestDTO.getNew_password().matches(PASSWORD_REGEX)) {
                return false;
            }
            return sysUserService.updateSysUserPasswordFirebase(sysUser.getFirebaseId(), updatePasswordRequestDTO.getNew_password());

        }catch (Exception e) {
             return false;
        }
    }
}
