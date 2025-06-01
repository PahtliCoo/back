/**
 * SysUserController.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.sysuser.CreateSysUserReqDTO;
import life.pahtlicoo.application.usecase.sysuser.CreateSysUserUseCase;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.shared.annotation.NoAuthRequired;

@Path("/sysUser")
public class SysUserController {
    @Inject
    CreateSysUserUseCase createUserUseCase;
    //agregar otros métodos de user

    @POST
    @Path("/createUser")
    @NoAuthRequired//Al ser nueva, no tiene forma de verificar
    public Response createUser(CreateSysUserReqDTO createUserReqDTO) {
        try {
            SysUser sysUser = createUserUseCase.execute(createUserReqDTO);
            if (sysUser != null) {
                return Response.status(Response.Status.CREATED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
