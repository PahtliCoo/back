package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.User;

public interface SysUserRepository {
    public SysUser createUser(SysUser user);
    public SysUser getUser(int userId);
    public void updateUserEmail(int userId, String newEmail);
    public void deleteUser(int userId);
    public SysUser createUserFirebase(SysUser sysUser, String password);
    public Boolean deleteUserFirebase(String userUid);
}
