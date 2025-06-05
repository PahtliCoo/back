package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.application.dto.sysuser.UserResponseDTO;


@ApplicationScoped
public class UserResponseMapper {

    public UserResponseDTO toDTO(SysUser sysUser) {
        if (sysUser == null) {
            return null;
        }
        UserResponseDTO dto = new UserResponseDTO();
        dto.setSysUserId(sysUser.getSysUserId());
        dto.setName(sysUser.getName());
        dto.setLastName(sysUser.getLastName());
        dto.setEmail(sysUser.getEmail());
        return dto;
    }
}