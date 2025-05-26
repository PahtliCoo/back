/*
Implementation of all the methods fore SysUser
@Autor: Santiago Moreno Lacalle Quintero
@CoAuthor
 */
package life.pahtlicoo.infrastructure.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SysUserRecord;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.domain.repository.SysUserRepository;
import life.pahtlicoo.infrastructure.entity.SysUserEntity;
import life.pahtlicoo.infrastructure.mapper.SysUserMapper;


@ApplicationScoped
public class SysUserRepositoryImpl implements SysUserRepository, PanacheRepositoryBase<SysUserEntity,Integer> {

    @Override
    @Transactional
    public SysUser createSysUser(SysUser sysUser) {
        try {
            SysUserEntity sysUserEntity = SysUserMapper.toEntity(sysUser);
            System.out.println("FirebaseId Entity" + sysUserEntity.getFirebaseId());
            // 1. Persist the SysUser
            sysUserEntity.persist();

            // 2. Check if persistance worked
            if (!sysUserEntity.isPersistent()) {
                System.out.println("SysUser entity was not persisted.");
                return null;
            }

            // 3. Return SysUser
            return SysUserMapper.toDomain(sysUserEntity);

        } catch (Exception e) {
            // 4. Failed
            System.err.println("Error al guardar el sysUser " + e.getMessage());
            return null;
        }
    }

    @Override
    public SysUser getSysUser(int sysUserId){
        SysUserEntity sysUserEntity = SysUserEntity.findById(sysUserId);

        if (sysUserEntity == null) {
            return null;
        }

        return SysUserMapper.toDomain(sysUserEntity);
    }

    @Override
    public void updateSysUserEmail(int sysUserId, String newEmail){

    }

    @Override
    public void deleteSysUser(int sysUserId){

    }
    @Transactional
    @Override
    public SysUser createSysUserFirebase(SysUser sysUser,  String password) {
        SysUserRecord.CreateRequest request = new SysUserRecord.CreateRequest().
                                                setEmail(sysUser.getEmail()).
                                                setPassword(password);
        try {
            SysUserRecord sysUserRecord = FirebaseAuth.getInstance().createSysUser(request);
            // Checking data.
            System.out.println(sysUserRecord);
            System.out.println("Email que se agrega: " + sysUserRecord.getEmail());
            System.out.println("Firebase que se agrega" + sysUserRecord.getUid());

            //Add the UID from firebase to the sysUser.
            sysUser.setFirebaseId(sysUserRecord.getUid());

            return sysUser;

        }catch (Exception e) {
            System.out.println("No se creo el usuario en firebase");
            return null;
        }
    }

    @Override
    public Boolean deleteSysUserFirebase(String sysUserUid) {
        try {
            FirebaseAuth.getInstance().deleteSysUser(sysUserUid);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting sysUser from Firebase: " + e.getMessage(), e);
        }

    }
}
