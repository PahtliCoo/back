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

    public SysUser getSysUserByUid(int userId) {
        return sysUserRepository.getSysUser(userId);
    }

    // Método para obtener SysUser por Firebase ID
    public SysUser getSysUserByFirebaseId(String firebaseId){
        return sysUserRepository.getSysUserByFirebaseId(firebaseId);
    }

    public SysUser updateSysUserEmail(int sysUserId, String newEmail) {
        SysUser updatedUser = sysUserRepository.updateSysUserEmail(sysUserId, newEmail);
        return updatedUser;
    }

    public SysUser updateSysUserPassword(int sysUserId, String newPassword) {
        SysUser updatedUser = sysUserRepository.updateSysUserPassword(sysUserId, newPassword);
        return updatedUser;
    }

    public Boolean updateSysUserEmailFirebase(String firebaseId, String newEmail) {
        Boolean success = sysUserRepository.updateSysUserEmailFirebase(firebaseId, newEmail);
        return success;
    }

    public Boolean updateSysUserPasswordFirebase(String firebaseId, String newPassword) {
        Boolean success = sysUserRepository.updateSysUserPasswordFirebase(firebaseId, newPassword);
        return success;
    }
}
