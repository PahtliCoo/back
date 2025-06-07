package life.pahtlicoo.application.usecase.sysuser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.SysUserService;
import life.pahtlicoo.application.mapper.UserResponseMapper;
import life.pahtlicoo.application.dto.sysuser.UserResponseDTO;
import life.pahtlicoo.domain.model.SysUser;

@ApplicationScoped
public class UpdateUserEmailUseCase {

    @Inject
    SysUserService sysUserService;

    @Inject
    UserResponseMapper userResponseMapper;
    public UserResponseDTO execute(int sysUserId, String newEmail) {
        try {
            if (newEmail == null || !newEmail.contains("@")) {
                throw new IllegalArgumentException("El email proporcionado es inválido.");
            }
            SysUser updatedUser = sysUserService.updateSysUserEmail(sysUserId, newEmail);
            if (updatedUser == null) {
                throw new IllegalArgumentException("No se pudo actualizar el email local. Usuario no encontrado o error en el servicio.");
            }
            return userResponseMapper.toDTO(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el Use Case al actualizar el email local: " + e.getMessage());
        }
    }
}