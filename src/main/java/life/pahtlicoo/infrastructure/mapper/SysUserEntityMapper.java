/**
 * System User Entity Mapper.
 * @author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.infrastructure.entity.SysUserEntity;

@ApplicationScoped
public class SysUserEntityMapper {
    public SysUser toDomain(SysUserEntity sysUserEntity){
        SysUser sysUser = new SysUser();
        // userId
        sysUser.setSysUserId(sysUserEntity.getSysUserId());
        // firebaseId
        sysUser.setFirebaseId(sysUserEntity.getFirebaseId());
        // email
        sysUser.setEmail(sysUserEntity.getEmail());
        // name
        sysUser.setName(sysUserEntity.getName());
        //lastName
        sysUser.setLastName(sysUserEntity.getLastName());
        // siteId
        sysUser.setSiteId(sysUserEntity.getSiteId());
        //credentialId
        sysUser.setCredentialId(sysUserEntity.getCredentialId());
        //createdAt
        sysUser.setCreatedAt(sysUserEntity.getCreatedAt());
        //updatedAt
        sysUser.setUpdatedAt(sysUserEntity.getUpdatedAt());
        return sysUser;
    };

    public SysUserEntity toEntity(SysUser sysUser){
        SysUserEntity sysUserEntity = new SysUserEntity();
        // userId
        sysUserEntity.setSysUserId(sysUser.getSysUserId());
        // firebaseId
        sysUserEntity.setFirebaseId(sysUser.getFirebaseId());
        // email
        sysUserEntity.setEmail(sysUser.getEmail());
        // name
        sysUserEntity.setName(sysUser.getName());
        //lastName
        sysUserEntity.setLastName(sysUser.getLastName());
        // siteId
        sysUserEntity.setSiteId(sysUser.getSiteId());
        //credentialId
        sysUserEntity.setCredentialId(sysUser.getCredentialId());
        //createdAt
        sysUserEntity.setCreatedAt(sysUser.getCreatedAt());
        //updatedAt
        sysUserEntity.setUpdatedAt(sysUser.getUpdatedAt());
        return sysUserEntity;
    };
}
