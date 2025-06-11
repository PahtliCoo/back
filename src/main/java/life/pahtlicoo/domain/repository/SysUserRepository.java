/**
 * Med Site class.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-11
 */
package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.SysUser;

public interface SysUserRepository {
    public SysUser createSysUser(SysUser user);
    public SysUser getSysUser(int userId);
    public SysUser updateSysUserEmail(SysUser sysUser);
    public Boolean updateSysUserEmailFirebase(SysUser sysUser, String newEmail);
    public Boolean updateSysUserPasswordFirebase(String firebaseId, String newPassword);
    public void deleteSysUser(int userId);
    public SysUser createSysUserFirebase(SysUser sysUser, String password);
    public Boolean deleteSysUserFirebase(String userUid);
    public SysUser getSysUserByFirebaseId(String firebaseId);
    public int getSysUserIdByCredentialId(int credentialId);
}
