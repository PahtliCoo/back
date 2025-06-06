package life.pahtlicoo.application.usecase.sysuser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.application.mapper.UserResponseMapper;
import life.pahtlicoo.application.dto.sysuser.UserResponseDTO;
import life.pahtlicoo.domain.model.SysUser;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class UpdateUserPasswordUseCase {

    private static final Logger LOGGER = Logger.getLogger(UpdateUserPasswordUseCase.class.getName());

    @Inject
    SysUserService sysUserService;

    @Inject
    UserResponseMapper userResponseMapper;

    public UserResponseDTO execute(int sysUserId, String newPassword) {
        if (newPassword == null || newPassword.length() < 8 || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*")) {
            LOGGER.log(Level.WARNING, "Contraseña inválida para sysUserId: " + sysUserId + ". Debe tener al menos 8 caracteres, una mayúscula y un número.");
            throw new IllegalArgumentException("Contraseña inválida. Debe tener al menos 8 caracteres, una mayúscula y un número.");
        }

        SysUser updatedUser = sysUserService.updateSysUserPassword(sysUserId, newPassword);

        if (updatedUser == null) {
            LOGGER.log(Level.SEVERE, "Fallo al actualizar la contraseña del usuario con ID: " + sysUserId);
            throw new IllegalArgumentException("No se pudo actualizar la contraseña local. Usuario no encontrado o error en el servicio.");
        }
        return userResponseMapper.toDTO(updatedUser);
    }
}