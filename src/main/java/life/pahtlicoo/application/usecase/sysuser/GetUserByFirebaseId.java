package life.pahtlicoo.application.usecase.sysuser;

import com.google.firebase.remoteconfig.internal.TemplateResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.sysuser.UserFirebaseContentDTO;
import life.pahtlicoo.application.dto.sysuser.UserRequestResponseDTO;
import life.pahtlicoo.application.mapper.SysUserResponseDomainMapper;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.SysUser;

@ApplicationScoped
public class GetUserByFirebaseId {
    @Inject
    SysUserService sysUserService;
    @Inject
    SysUserResponseDomainMapper sysUserResponseDomainMapper;

    public UserRequestResponseDTO execute(UserFirebaseContentDTO userFirebaseContentDTO) {
        try{
            SysUser sysUser = sysUserService.getSysUserByFirebaseId(userFirebaseContentDTO.getUid());
            System.out.println(sysUser.getName());
            return sysUserResponseDomainMapper.toUserRequestResponseDTO(sysUser);
        }catch (Exception e){
            return null;
        }
    }
}
