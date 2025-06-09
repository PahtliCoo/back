/**
 User Service
 @Author: Santiago Moreno Lacalle Quintero
 @Since: 2025-05-13
 */
package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.domain.repository.SysUserRepository;

@ApplicationScoped
public class SysUserService {
    @Inject
    SysUserRepository sysUserRepository;

    public SysUser createUser(SysUser sysUser) {
        return sysUserRepository.createSysUser(sysUser);
    }

    public SysUser createUserFirebase(SysUser user, String password) {
        return sysUserRepository.createSysUserFirebase(user, password);
    }

    public Boolean deleteUserFirebase(String userUid){
        return sysUserRepository.deleteSysUserFirebase(userUid);
    }

    public SysUser getSysUserByUserId(int userId) {
        return sysUserRepository.getSysUser(userId);
    }

    public SysUser getSysUserByFirebaseId(String firebaseId){
        return sysUserRepository.getSysUserByFirebaseId(firebaseId);
    }

    public SysUser updateSysUserEmail(SysUser sysUser) {
        return sysUserRepository.updateSysUserEmail(sysUser);
    }

    public Boolean updateSysUserEmailFirebase(SysUser sysUser, String newEmail) {
        return sysUserRepository.updateSysUserEmailFirebase(sysUser, newEmail);
    }

    public Boolean updateSysUserPasswordFirebase(String firebaseId, String newPassword) {
        return sysUserRepository.updateSysUserPasswordFirebase(firebaseId, newPassword);
    }
}
