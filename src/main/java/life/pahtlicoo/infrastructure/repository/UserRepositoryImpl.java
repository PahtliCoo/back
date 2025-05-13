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
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.User;
import life.pahtlicoo.domain.repository.UserRepository;
import life.pahtlicoo.infrastructure.entity.UserEntity;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity,Integer> {

    @Transactional
    @Override
    public User createUser(User user) {
        //TODO: Add mapper entity to domain domain to entity
        UserEntity userEntity = new UserEntity(); // Add mapper to entity
        //Add the entity to the database
        persist(userEntity);

        if (userEntity.isPersistent()){
            return null; //Returns the user domain
        }

        //The entity did not create in the database
        return null;
    }

    @Override
    public User getUser(int userId){
        UserEntity userEntity = UserEntity.findById(userId);
        if (userEntity == null) {
            return null;
        }
        //TODO: ADD mapper to domain
        return null; // This will be domain
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
            System.out.println(userRecord.getEmail());
            System.out.println(userRecord.getUid());

            //Add the UID from firebase to the user.
            user.setFirebaseId(userRecord.getUid());
            return user;

        }catch (Exception e) {
            System.out.println("No se creo el usuario en firebase");
            return null;
        }
    }
}
