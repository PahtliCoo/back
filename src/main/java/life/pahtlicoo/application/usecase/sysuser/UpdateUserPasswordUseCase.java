package life.pahtlicoo.application.usecase.sysuser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.application.mapper.UserResponseMapper;
import life.pahtlicoo.application.dto.sysuser.UserResponseDTO;
import life.pahtlicoo.domain.model.SysUser;

@ApplicationScoped
public class UpdateUserPasswordUseCase {

    @Inject
    SysUserService sysUserService;

    @Inject
    UserResponseMapper userResponseMapper;

    public UserResponseDTO execute(int sysUserId, String newPassword) {
        try {
            if (newPassword == null || newPassword.length() < 8 || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*")) {
                throw new IllegalArgumentException("Contraseña inválida. Debe tener al menos 8 caracteres, una mayúscula y un número.");
            }
            SysUser updatedUser = sysUserService.updateSysUserPassword(sysUserId, newPassword);
            if (updatedUser == null) {
                throw new IllegalArgumentException("No se pudo actualizar la contraseña local. Usuario no encontrado o error en el servicio.");
            }
            return userResponseMapper.toDTO(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el Use Case al actualizar la contraseña local: " + e.getMessage());
        }
    }
}