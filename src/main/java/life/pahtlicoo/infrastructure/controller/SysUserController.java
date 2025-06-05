package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import life.pahtlicoo.application.dto.sysuser.CreateSysUserReqDTO;
import life.pahtlicoo.application.dto.sysuser.UserFirebaseContentDTO;
import life.pahtlicoo.application.dto.sysuser.UserRequestResponseDTO;
import life.pahtlicoo.application.usecase.sysuser.CreateSysUserUseCase;
import life.pahtlicoo.application.usecase.sysuser.GetUserByFirebaseId;
import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.shared.annotation.NoAuthRequired;

@Path("/sys-user")
public class SysUserController {
    @Inject
    CreateSysUserUseCase createUserUseCase;
    @Inject
    GetUserByFirebaseId getUserByFirebaseId;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @NoAuthRequired
    public Response createUser(@Valid CreateSysUserReqDTO createUserReqDTO) {
        try {
            SysUser sysUser = createUserUseCase.execute(createUserReqDTO);
            if (sysUser == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            return Response.ok(sysUser).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/getUser")
    public Response getUser(UserFirebaseContentDTO userFirebaseContentDTO) {
        try {
            UserRequestResponseDTO userRequestResponseDTO = getUserByFirebaseId.execute(userFirebaseContentDTO);
            return Response.ok(userRequestResponseDTO).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

}
