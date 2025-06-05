/**
 * Use case para crear usuario
 * @Author: Santiago Moreno Lacalle Quintero
 * @Since: 2025-05-13
 */
package life.pahtlicoo.application.usecase.sysuser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.sysuser.CreateSysUserReqDTO;
import life.pahtlicoo.application.mapper.SysUserMapperReqDto;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.domain.model.SysUser;

@ApplicationScoped
public class CreateSysUserUseCase {

    @Inject
    SysUserService sysUserService;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";


    @Transactional
    public SysUser execute(CreateSysUserReqDTO createUserReqDTO) {
        try {
            if (!createUserReqDTO.getEmail().matches(EMAIL_REGEX)) {
                return null;
            }

            // Validar contraseña
            if (!createUserReqDTO.getPassword().matches(PASSWORD_REGEX)) {
                return null;
            }

            // 1. Convertir DTO a Modelo de Dominio
            SysUser user = SysUserMapperReqDto.toDomain(createUserReqDTO);
            if (user == null) {
                return null;
            }

            // 2. Crear usuario en Firebase Authentication
            SysUser firebaseUser = sysUserService.createUserFirebase(user, createUserReqDTO.getPassword());

            if (firebaseUser == null) {
                return null;
            }

            // 3. Intentar crear el usuario en la base de datos principal
            SysUser createdDbUser = sysUserService.createUser(firebaseUser);

            if (createdDbUser == null) {
                try {
                    sysUserService.deleteUserFirebase(firebaseUser.getFirebaseId());
                    return null; // significa que no se creo el user
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                    return null;
                }
            }
            return createdDbUser;

        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
        }
    }
}