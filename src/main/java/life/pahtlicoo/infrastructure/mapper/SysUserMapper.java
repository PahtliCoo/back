package life.pahtlicoo.infrastructure.mapper;


import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.infrastructure.entity.SysUserEntity;

public class SysUserMapper {
    public static SysUser toDomain(SysUserEntity sysUserEntity){
        SysUser sysUser = new SysUser();
        // userId
        sysUser.setSysUserId(sysUserEntity.getSysUserid());
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

    public static SysUserEntity toEntity(SysUser sysUser){
        SysUserEntity sysUserEntity = new SysUserEntity();
        // userId
        sysUserEntity.setSysUserid(sysUser.getSysUserId());
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
