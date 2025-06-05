/*
Implementation of all the methods fore SysUser
@Autor: Santiago Moreno Lacalle Quintero
@Co-Author: Nicole Kapplemann Lepinn
 */
package life.pahtlicoo.infrastructure.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.domain.repository.SysUserRepository;
import life.pahtlicoo.infrastructure.entity.SysUserEntity;
import life.pahtlicoo.infrastructure.mapper.SysUserEntityMapper;


@ApplicationScoped
public class SysUserRepositoryImpl implements SysUserRepository, PanacheRepositoryBase<SysUserEntity,Integer> {
    @Inject
    SysUserEntityMapper sysUserEntityMapper;

    @Override
    @Transactional
    public SysUser createSysUser(SysUser sysUser) {
        try {
            SysUserEntity sysUserEntity = sysUserEntityMapper.toEntity(sysUser);
            System.out.println("FirebaseId Entity" + sysUserEntity.getFirebaseId());
            // 1. Persist the SysUser
            sysUserEntity.persist();

            // 2. Check if persistance worked
            if (!sysUserEntity.isPersistent()) {
                System.out.println("SysUser entity was not persisted.");
                return null;
            }

            // 3. Return SysUser
            return sysUserEntityMapper.toDomain(sysUserEntity);

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

        return sysUserEntityMapper.toDomain(sysUserEntity);
    }

    @Override
    @Transactional
    public SysUser updateSysUserEmail(int sysUserId, String newEmail){
        SysUserEntity sysUserEntity = SysUserEntity.findById(sysUserId);
        if (sysUserEntity == null) {
            return null;
        }
        sysUserEntity.setEmail(newEmail);
        return sysUserEntityMapper.toDomain(sysUserEntity);
    }

    @Override
    @Transactional
    public SysUser updateSysUserPassword(int sysUserId, String newPassword){
        SysUserEntity sysUserEntity = SysUserEntity.findById(sysUserId);
        if (sysUserEntity == null) {
            return null;
        }

        String firebaseId = sysUserEntity.getFirebaseId();
        if (firebaseId == null || firebaseId.isEmpty()) {
            return null;
        }
        Boolean firebaseUpdateSuccess = updateSysUserPasswordFirebase(firebaseId, newPassword);

        if (firebaseUpdateSuccess) {
            return sysUserEntityMapper.toDomain(sysUserEntity);
        } else {
            return null;
        }
    }

    @Override
    public Boolean updateSysUserEmailFirebase(String firebaseId, String newEmail) {
        try {
            UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(firebaseId)
                    .setEmail(newEmail);
            FirebaseAuth.getInstance().updateUser(request);
            return true;
        } catch (FirebaseAuthException e) {
            return false;
        }
    }

    @Override
    public Boolean updateSysUserPasswordFirebase(String firebaseId, String newPassword) {
        try {
            UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(firebaseId)
                    .setPassword(newPassword);
            FirebaseAuth.getInstance().updateUser(request);
            return true;
        } catch (FirebaseAuthException e) {
            return false;
        }
    }

    @Override
    public void deleteSysUser(int sysUserId){


    }
    @Transactional
    @Override
    public SysUser createSysUserFirebase(SysUser user,  String password) {

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setPassword(password)
                .setEmailVerified(false)
                .setDisabled(false);
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            //Add the UID from firebase to the user.
            user.setFirebaseId(userRecord.getUid());

            return user;

        }catch (Exception e) {
            System.out.println("No se creo el usuario en firebase");
            e.printStackTrace(); //print full stack trace
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

    @Override
    public SysUser getSysUserByFirebaseId(String firebaseId) {
        SysUserEntity sysUserEntity = find("firebaseId", firebaseId).firstResult();
        if (sysUserEntity == null) {
            return null;
        }
        return sysUserEntityMapper.toDomain(sysUserEntity);
    }

}
