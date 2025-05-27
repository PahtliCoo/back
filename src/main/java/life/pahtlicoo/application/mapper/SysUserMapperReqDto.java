/**
 User requirment DTO MAPPER to domain
 @Author: Santiago Moreno Lacalle Quintero
 @Since: 2025-05-13
 */
package life.pahtlicoo.application.mapper;

import life.pahtlicoo.application.dto.sysuser.CreateSysUserReqDTO;
import life.pahtlicoo.domain.model.SysUser;

public class SysUserMapperReqDto {
    public static SysUser toDomain(CreateSysUserReqDTO createUserReqDTO){
        SysUser user = new SysUser();
        user.setName(createUserReqDTO.getName());
        user.setEmail(createUserReqDTO.getEmail());
        user.setLastName(createUserReqDTO.getLastName());
        user.setSiteId(createUserReqDTO.getSiteId());
        user.setCredentialId(createUserReqDTO.getCredentialId());
        return user;
    }
}
