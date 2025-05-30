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
    };

    public SysUser createUserFirebase(SysUser user, String password) {
        return sysUserRepository.createSysUserFirebase(user, password);
    }
    public Boolean deleteUserFirebase(String userUid){
        return sysUserRepository.deleteSysUserFirebase(userUid);
    }

    public SysUser getSysUserByUid(int userId) {
        return sysUserRepository.getSysUser(userId);
    }
}
