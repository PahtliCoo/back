/*
Implementation of all the methods fore SysUser
@Autor: Santiago Moreno Lacalle Quintero
@Co-Author: Nicole Kapplemann Lepinn
 */
package life.pahtlicoo.infrastructure.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.domain.repository.SysUserRepository;
import life.pahtlicoo.infrastructure.entity.SysUserEntity;
import life.pahtlicoo.infrastructure.mapper.SysUserEntityMapper;


@ApplicationScoped
public class SysUserRepositoryImpl implements SysUserRepository, PanacheRepositoryBase<SysUserEntity,Integer> {

    @Override
    @Transactional
    public SysUser createSysUser(SysUser sysUser) {
        try {
            SysUserEntity sysUserEntity = SysUserEntityMapper.toEntity(sysUser);
            // 1. Persist the SysUser
            sysUserEntity.persist();

            // 2. Check if persistance worked
            if (!sysUserEntity.isPersistent()) {
                return null;
            }

            // 3. Return SysUser
            return SysUserEntityMapper.toDomain(sysUserEntity);

        } catch (Exception e) {
            // 4. Failed
            return null;
        }
    }

    @Override
    public SysUser getSysUser(int sysUserId) {
        SysUserEntity sysUserEntity = SysUserEntity.findById(sysUserId);
        if (sysUserEntity == null) {
            return null;
        }
        return SysUserEntityMapper.toDomain(sysUserEntity);
    }

    @Override
    public SysUser updateSysUserEmail(int sysUserId, String newEmail) {
        SysUserEntity sysUserEntity = SysUserEntity.findById(sysUserId);
        if (sysUserEntity == null) {
            return null;
        }
        sysUserEntity.setEmail(newEmail);
        return SysUserEntityMapper.toDomain(sysUserEntity);
    }

    @Transactional
    @Override
    public Boolean deleteSysUser(int sysUserId){
        return SysUserEntity.deleteById(sysUserId);
    }

    @Transactional
    @Override
    public SysUser createSysUserFirebase(SysUser user,  String password) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest().
                setEmail(user.getEmail()).
                setPassword(password);
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            //Add the UID from firebase to the user.
            user.setFirebaseId(userRecord.getUid());
            return user;

        }catch (Exception e) {
            // No se creo
            return null;
        }
    }

    @Override
    public Boolean deleteSysUserFirebase(String sysUserUid) {
        try {
            FirebaseAuth.getInstance().deleteUser(sysUserUid);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting sysUser from Firebase: " + e.getMessage(), e);
        }

    }
}
