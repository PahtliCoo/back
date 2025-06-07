package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.application.dto.sysuser.UserResponseDTO;


@ApplicationScoped
public class UserResponseMapper {

    public UserResponseDTO toDTO(SysUser sysUser) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setSysUserId(sysUser.getSysUserId());
        userResponseDTO.setName(sysUser.getName());
        userResponseDTO.setLastName(sysUser.getLastName());
        userResponseDTO.setEmail(sysUser.getEmail());
        userResponseDTO.setFirebaseId(sysUser.getFirebaseId());
        return userResponseDTO;
    }
}