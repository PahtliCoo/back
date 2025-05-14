package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.User;
import life.pahtlicoo.domain.repository.UserRepository;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.createUser(user);
    };

    public User createUserFirebase(User user, String password) {
        return userRepository.createUserFirebase(user, password);
    }
    public Boolean deleteUserFirebase(String userUid){
        return userRepository.deleteUserFirebase(userUid);
    }
}
