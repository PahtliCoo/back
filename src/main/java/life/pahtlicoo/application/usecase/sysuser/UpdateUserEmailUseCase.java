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
public class UpdateUserEmailUseCase {

    private static final Logger LOGGER = Logger.getLogger(UpdateUserEmailUseCase.class.getName());

    @Inject
    SysUserService sysUserService;

    @Inject
    UserResponseMapper userResponseMapper;

    public UserResponseDTO executeLocalEmailUpdate(int sysUserId, String newEmail) {
        if (newEmail == null || !newEmail.contains("@")) {
            throw new IllegalArgumentException("El email proporcionado es inválido.");
        }
        SysUser updatedUser = sysUserService.updateSysUserEmail(sysUserId, newEmail);
        if (updatedUser == null) {
            throw new IllegalArgumentException("No se pudo actualizar el email local. Usuario no encontrado o error en el servicio.");
        }
        return userResponseMapper.toDTO(updatedUser);
    }

    // Método para actualizar email en Firebase Authentication
    public UserResponseDTO executeFirebaseEmailUpdate(String firebaseId, String newEmail) {
        if (firebaseId == null || firebaseId.isEmpty() || newEmail == null || !newEmail.contains("@")) {
            throw new IllegalArgumentException("Firebase ID o email inválido.");
        }
        try {
            Boolean success = sysUserService.updateSysUserEmailFirebase(firebaseId, newEmail);
            if (!success) {
                throw new IllegalStateException("Fallo interno al actualizar el email en Firebase.");
            }

            // Usamos el método existente en SysUserService.
            SysUser updatedSysUser = sysUserService.getSysUserByFirebaseId(firebaseId);
            if (updatedSysUser == null) {
                UserResponseDTO dto = new UserResponseDTO();
                dto.setFirebaseId(firebaseId);
                dto.setEmail(newEmail);
                return dto;
            }
            updatedSysUser.setEmail(newEmail);
            return userResponseMapper.toDTO(updatedSysUser);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar el email en Firebase: " + e.getMessage());
        }
    }
}