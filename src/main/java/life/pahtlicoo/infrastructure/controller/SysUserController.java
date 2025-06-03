/**
 * SysUserController.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (A01664412@tec.mx)
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

@Path("/sys-user")
public class SysUserController {
    @Inject
    CreateSysUserUseCase createUserUseCase;

    @POST
    @Path("/create")
    public Response createUser(CreateSysUserReqDTO createUserReqDTO) {
        try {
            SysUser sysUser = createUserUseCase.execute(createUserReqDTO);
            return Response.ok(sysUser).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
