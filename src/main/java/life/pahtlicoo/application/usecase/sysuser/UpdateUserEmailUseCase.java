package life.pahtlicoo.application.usecase.sysuser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.sysuser.UpdateEmailRequestDTO;
import life.pahtlicoo.application.dto.sysuser.UserRequestResponseDTO;
import life.pahtlicoo.application.mapper.SysUserResponseDomainMapper;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.application.mapper.UserResponseMapper;
import life.pahtlicoo.application.dto.sysuser.UserResponseDTO;
import life.pahtlicoo.domain.model.SysUser;

@ApplicationScoped
@Transactional
public class UpdateUserEmailUseCase {

    @Inject
    SysUserService sysUserService;
    @Inject
    SysUserResponseDomainMapper sysUserResponseDomainMapper;
    public UserRequestResponseDTO execute(UpdateEmailRequestDTO updateEmailRequestDTO) {
        try {
            if(!updateEmailRequestDTO.getNewEmail().contains("@")){
                return null;
            }

            SysUser sysUser = sysUserService.getSysUserByUserId(updateEmailRequestDTO.getSysUserId());
            if(sysUser == null){
                return null;
            }

            if(!sysUser.getEmail().equals(updateEmailRequestDTO.getOldEmail())){
                return null;
            }

             if(!sysUserService.updateSysUserEmailFirebase(sysUser,updateEmailRequestDTO.getNewEmail())){
                 return null;
             }

             sysUser = sysUserService.updateSysUserEmail(sysUser);
             if(sysUser == null){
                 return null;
             }
            return sysUserResponseDomainMapper.toUserRequestResponseDTO(sysUser);
        } catch (Exception e) {
            return null;
        }
    }
}