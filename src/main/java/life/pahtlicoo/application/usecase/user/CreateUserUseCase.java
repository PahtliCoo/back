/*
Use case para crear usuario
@Author: Santiago Moreno Lacalle Quintero
@Since: 2025-05-13
 */
package life.pahtlicoo.application.usecase.user;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.user.CreateUserReqDTO;
import life.pahtlicoo.application.mapper.UserMapperReqDto;
import life.pahtlicoo.application.service.UserService;
import life.pahtlicoo.domain.model.User;

@ApplicationScoped
public class CreateUserUseCase {
    @Inject
    UserService userService;

    public User execute (CreateUserReqDTO createUserReqDTO) {
        // Create user with data of DTO.
        User user = UserMapperReqDto.toDomain(createUserReqDTO);
        try {
            // 1. Create Firebase user
            user = userService.createUserFirebase(user, createUserReqDTO.getPassword());

            try {
                // 2. Try to create user in the database
                user = userService.createUser(user);

            } catch (Exception dbEx) {
                // Database creation failed — rollback Firebase user
                if (user.getFirebaseId() != null) {
                    try {
                        //Delete User from Firebase
                        userService.deleteUserFirebase(user.getFirebaseId());

                    } catch (Exception rollbackEx) {
                        System.err.println("Error al revertir usuario en Firebase: " + rollbackEx.getMessage());
                    }
                }
                throw new RuntimeException("Error al crear usuario en la base de datos: " + dbEx.getMessage(), dbEx);
            }
            // Success
            return user;

        } catch (Exception e) {
            // Could be Firebase failure or something else
            throw new RuntimeException("Error general al crear usuario: " + e.getMessage(), e);
        }
    }
}
