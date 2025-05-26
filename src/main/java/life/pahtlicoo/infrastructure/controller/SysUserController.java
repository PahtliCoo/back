package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.sysUser.CreateUserReqDTO;
import life.pahtlicoo.application.usecase.sysUser.CreateUserUseCase;
import life.pahtlicoo.domain.model.SysUser;

@Path("/sysUser")
public class UserController {
    @Inject
    CreateUserUseCase createUserUseCase;

    @POST
    @Path("/createUser")
    public Response createUser(CreateUserReqDTO createUserReqDTO) {
        try {
            SysUser sysUser = createUserUseCase.execute(createUserReqDTO);
            return Response.ok(sysUser).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
