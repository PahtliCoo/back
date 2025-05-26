package life.pahtlicoo.infrastructure.mapper;

import life.pahtlicoo.domain.model.User;
import life.pahtlicoo.infrastructure.entity.UserEntity;

public class SysUserMapper {
    public static SysUser toDomain(SysUserEntity sysUserEntity){
        SysUser sysUser = new SysUser();
        // userId
        user.setId(sysUserEntity.getId());
        // firebaseId
        user.setFirebaseId(sysUserEntity.getFirebaseId());
        // email
        user.setEmail(sysUserEntity.getEmail());
        // name
        user.setName(sysUserEntity.getName());
        //lastName
        user.setLastName(sysUserEntity.getLastName());
        // siteId
        user.setSiteId(sysUserEntity.getSiteId());
        //credentialId
        user.setCredentialId(sysUserEntity.getCredentialId());
        //createdAt
        user.setCreatedAt(sysUserEntity.getCreatedAt());
        //updatedAt
        user.setUpdatedAt(sysUserEntity.getUpdatedAt());
        return user;
    };

    public static SysUserEntity toEntity(SysUser sysUser){
        SysUserEntity sysUserEntity = new SysUserEntity();
        // userId
        sysUserEntity.setId(sysUser.getUserId());
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
