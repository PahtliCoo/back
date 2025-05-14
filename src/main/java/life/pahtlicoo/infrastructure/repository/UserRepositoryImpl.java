/*
Implementation of all the methods fore User
@Autor: Santiago Moreno Lacalle Quintero
@CoAuthor
 */
package life.pahtlicoo.infrastructure.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.User;
import life.pahtlicoo.domain.repository.UserRepository;
import life.pahtlicoo.infrastructure.entity.UserEntity;
import life.pahtlicoo.infrastructure.mapper.UserMapper;


@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity,Integer> {

    @Override
    @Transactional
    public User createUser(User user) {
        try {
            UserEntity userEntity = UserMapper.toEntity(user);
            System.out.println("FirebaseId Entity" + userEntity.getFirebaseId());
            // 1. Persist the User
            userEntity.persist();

            // 2. Check if persistance worked
            if (!userEntity.isPersistent()) {
                System.out.println("User entity was not persisted.");
                return null;
            }

            // 3. Return User
            return UserMapper.toDomain(userEntity);

        } catch (Exception e) {
            // 4. Failed
            System.err.println("Error al guardar el user " + e.getMessage());
            return null;
        }
    }

    @Override
    public User getUser(int userId){
        UserEntity userEntity = UserEntity.findById(userId);

        if (userEntity == null) {
            return null;
        }

        return UserMapper.toDomain(userEntity);
    }

    @Override
    public void updateUserEmail(int userId, String newEmail){

    }

    @Override
    public void deleteUser(int userId){

    }
    @Transactional
    @Override
    public User createUserFirebase(User user,  String password) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest().
                                                setEmail(user.getEmail()).
                                                setPassword(password);
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            // Checking data.
            System.out.println(userRecord);
            System.out.println("Email que se agrega: " + userRecord.getEmail());
            System.out.println("Firebase que se agrega" + userRecord.getUid());

            //Add the UID from firebase to the user.
            user.setFirebaseId(userRecord.getUid());

            return user;

        }catch (Exception e) {
            System.out.println("No se creo el usuario en firebase");
            return null;
        }
    }

    @Override
    public Boolean deleteUserFirebase(String userUid) {
        try {
            FirebaseAuth.getInstance().deleteUser(userUid);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user from Firebase: " + e.getMessage(), e);
        }

    }
}
