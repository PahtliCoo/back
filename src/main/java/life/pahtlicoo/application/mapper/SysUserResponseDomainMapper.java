package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.sysuser.UserRequestResponseDTO;
import life.pahtlicoo.domain.model.SysUser;

@ApplicationScoped
public class SysUserResponseDomainMapper {
    public UserRequestResponseDTO toUserRequestResponseDTO(SysUser sysUser) {
        return new UserRequestResponseDTO(sysUser.getSysUserId(),sysUser.getCredentialId(),sysUser.getName());
    }
}
