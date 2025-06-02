/**
Use case para crear usuario
@Author: Santiago Moreno Lacalle Quintero
@Since: 2025-05-13
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
@Transactional
public class CreateSysUserUseCase {
    @Inject
    SysUserService sysUserService;

    public SysUser execute (CreateSysUserReqDTO createUserReqDTO) {
        try{
            // Create user with data of DTO.
            SysUser user = SysUserMapperReqDto.toDomain(createUserReqDTO);
            // 1. Create Firebase user
            user = sysUserService.createUserFirebase(user, createUserReqDTO.getPassword());
            // 2. Try to create user in the database
            user = sysUserService.createUser(user);
            // Database creation failed — rollback Firebase user
            if (user.getFirebaseId() == null) {
                try {
                    //Delete User from Firebase
                    sysUserService.deleteUserFirebase(user.getFirebaseId());
                } catch (Exception rollbackEx) {
                    return null;
                }
            }
            // Success
            return user;
        }catch (Exception e) {
            return null;
        }
    }
}
