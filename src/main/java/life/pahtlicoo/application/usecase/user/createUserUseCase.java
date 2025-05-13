/*
Use case para crear usuario
@Author: Santiago Moreno Lacalle Quintero
@Since: 2025-05-13
 */
package life.pahtlicoo.application.usecase.user;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.user.CreateUserReqDTO;
import life.pahtlicoo.application.mapper.UserMapper;
import life.pahtlicoo.application.service.UserService;
import life.pahtlicoo.domain.model.User;

public class createUserUseCase {
    @Inject
    UserService userService;
    @Inject
    UserMapper userMapper;

    public User execute (CreateUserReqDTO createUserReqDTO){
        //todo: check DTO data to user.
        User user = userMapper.toDomain(createUserReqDTO);

        user = userService.createUserFirebase(user, createUserReqDTO.getPassword());
        if (user == null) {
            return null;
        }

        user = userService.createUser(user);
        return user;
    }
}
