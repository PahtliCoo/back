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
public class UpdateUserFirebaseEmailUseCase {

    private static final Logger LOGGER = Logger.getLogger(UpdateUserFirebaseEmailUseCase.class.getName());

    @Inject
    SysUserService sysUserService;

    @Inject
    UserResponseMapper userResponseMapper;

    public UserResponseDTO execute(String firebaseId, String newEmail) {
        if (firebaseId == null || firebaseId.isEmpty() || newEmail == null || !newEmail.contains("@")) {
            throw new IllegalArgumentException("Firebase ID o email inválido.");
        }
        try {
            Boolean success = sysUserService.updateSysUserEmailFirebase(firebaseId, newEmail);
            if (!success) {
                LOGGER.log(Level.SEVERE, "Fallo interno al actualizar el email en Firebase para ID: " + firebaseId);
                throw new IllegalStateException("Fallo interno al actualizar el email en Firebase.");
            }

            SysUser sysUserFromDb = sysUserService.getSysUserByFirebaseId(firebaseId);
            if (sysUserFromDb != null) {
                UserResponseDTO dto = userResponseMapper.toDTO(sysUserFromDb);
                dto.setEmail(newEmail);
                return dto;
            } else {
                LOGGER.warning("UpdateUserFirebaseEmailUseCase: No se encontró SysUser local después de actualizar en Firebase para ID: " + firebaseId + ". Devolviendo DTO parcial.");
                UserResponseDTO dto = new UserResponseDTO();
                dto.setFirebaseId(firebaseId);
                dto.setEmail(newEmail);
                return dto;
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en UpdateUserFirebaseEmailUseCase al actualizar email en Firebase para ID: " + firebaseId + ". Causa: " + e.getMessage(), e);
            throw new IllegalArgumentException("Error al actualizar el email en Firebase: " + e.getMessage());
        }
    }
}