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
public class UpdateUserFirebasePasswordUseCase {

    private static final Logger LOGGER = Logger.getLogger(UpdateUserFirebasePasswordUseCase.class.getName());

    @Inject
    SysUserService sysUserService;

    @Inject
    UserResponseMapper userResponseMapper;


    public UserResponseDTO execute(String firebaseId, String newPassword) {
        if (firebaseId == null || firebaseId.isEmpty() || newPassword == null || newPassword.length() < 8) {
            LOGGER.log(Level.WARNING, "Firebase ID o contraseña inválida para Firebase ID: " + firebaseId + ". Contraseña debe tener al menos 8 caracteres.");
            throw new IllegalArgumentException("Firebase ID o contraseña inválida (mínimo 8 caracteres).");
        }
        try {
            Boolean success = sysUserService.updateSysUserPasswordFirebase(firebaseId, newPassword);
            if (!success) {
                LOGGER.log(Level.SEVERE, "Fallo interno al actualizar la contraseña directamente en Firebase para ID: " + firebaseId);
                throw new IllegalStateException("Fallo interno al actualizar la contraseña directamente en Firebase.");
            }

            SysUser sysUserFromDb = sysUserService.getSysUserByFirebaseId(firebaseId);
            if (sysUserFromDb == null) {
                LOGGER.warning("UpdateUserFirebasePasswordUseCase: No se encontró SysUser local después de actualizar contraseña en Firebase para ID: " + firebaseId + ". Devolviendo DTO parcial.");
                UserResponseDTO dto = new UserResponseDTO();
                dto.setFirebaseId(firebaseId);
                return dto;
            }
            return userResponseMapper.toDTO(sysUserFromDb);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en UpdateUserFirebasePasswordUseCase al actualizar contraseña en Firebase para ID: " + firebaseId + ". Causa: " + e.getMessage(), e);
            throw new IllegalArgumentException("Error al actualizar la contraseña en Firebase: " + e.getMessage());
        }
    }
}
