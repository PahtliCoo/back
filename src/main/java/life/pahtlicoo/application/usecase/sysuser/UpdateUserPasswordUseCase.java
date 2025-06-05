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

    // Método para actualizar contraseña en la base de datos local

    public UserResponseDTO executePasswordUpdate(int sysUserId, String newPassword) {
        if (newPassword == null || newPassword.length() < 8 || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Contraseña inválida. Debe tener al menos 8 caracteres, una mayúscula y un número.");
        }
        SysUser updatedUser = sysUserService.updateSysUserPassword(sysUserId, newPassword);
        if (updatedUser == null) {
            throw new IllegalArgumentException("No se pudo actualizar la contraseña local. Usuario no encontrado o error en el servicio.");
        }
        return userResponseMapper.toDTO(updatedUser);
    }

    // Método para actualizar contraseña en Firebase Authentication
    public UserResponseDTO executeFirebasePasswordUpdate(String firebaseId, String newPassword) {
        if (firebaseId == null || firebaseId.isEmpty() || newPassword == null || newPassword.length() < 8) {
            throw new IllegalArgumentException("Firebase ID o contraseña inválida (mínimo 8 caracteres).");
        }
        try {
            Boolean success = sysUserService.updateSysUserPasswordFirebase(firebaseId, newPassword);
            if (!success) {
                throw new IllegalStateException("Fallo interno al actualizar la contraseña directamente en Firebase.");
            }

            SysUser updatedSysUser = sysUserService.getSysUserByFirebaseId(firebaseId);
            if (updatedSysUser == null) {
                UserResponseDTO dto = new UserResponseDTO();
                dto.setFirebaseId(firebaseId);
                return dto;
            }
            return userResponseMapper.toDTO(updatedSysUser);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar la contraseña en Firebase: " + e.getMessage());
        }
    }
}