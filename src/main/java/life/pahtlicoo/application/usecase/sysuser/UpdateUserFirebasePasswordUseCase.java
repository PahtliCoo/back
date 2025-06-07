package life.pahtlicoo.application.usecase.sysuser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.sysuser.UpdatePasswordRequestDTO;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.application.mapper.UserResponseMapper;
import life.pahtlicoo.application.dto.sysuser.UserResponseDTO;
import life.pahtlicoo.domain.model.SysUser;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class UpdateUserFirebasePasswordUseCase {

    @Inject
    SysUserService sysUserService;

    @Inject
    UserResponseMapper userResponseMapper;

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";


    public boolean execute(UpdatePasswordRequestDTO updatePasswordRequestDTO) {
        try{
            SysUser sysUser = sysUserService.getSysUserByUserId(updatePasswordRequestDTO.getSysUserId());
            if (sysUser == null) {
                return false;
            }
            if (!updatePasswordRequestDTO.getNewPassword().matches(PASSWORD_REGEX)) {
                return false;
            }
            return sysUserService.updateSysUserPasswordFirebase(sysUser.getFirebaseId(), updatePasswordRequestDTO.getNewPassword());

        }catch (Exception e) {
             return false;
        }
    }
}
