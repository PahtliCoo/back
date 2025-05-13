package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.User;

public interface UserRepository {
    public void createUser(User user);
    public User getUser(int userId);
    public void updateUserEmail(int userId, String newEmail);
    public void deleteUser(int userId);
}
