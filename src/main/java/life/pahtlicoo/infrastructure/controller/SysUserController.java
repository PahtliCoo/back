/**
 * System User Controller
 * @author Fernando Emiliano Tavera Moreno
 * @co-author Santiago Moreno Lacalle Quintero (a01663197@tec.mx)
 * @since 2025-06-08
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import life.pahtlicoo.application.dto.sysuser.UserFirebaseContentDTO;
import life.pahtlicoo.application.dto.sysuser.UserRequestResponseDTO;
import life.pahtlicoo.application.dto.sysuser.UpdateEmailRequestDTO;
import life.pahtlicoo.application.dto.sysuser.UpdatePasswordRequestDTO;

import life.pahtlicoo.application.usecase.sysuser.GetUserByFirebaseId;
import life.pahtlicoo.application.usecase.sysuser.UpdateUserEmailUseCase;
import life.pahtlicoo.application.usecase.sysuser.UpdateUserFirebasePasswordUseCase;

@Path("/sys-user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SysUserController {
    @Inject
    GetUserByFirebaseId getUserByFirebaseId;
    @Inject
    UpdateUserEmailUseCase updateUserEmailUseCase;
    @Inject
    UpdateUserFirebasePasswordUseCase updateUserFirebasePasswordUseCase;

    @POST
    @Path("/get-user")
    public Response getUser(UserFirebaseContentDTO userFirebaseContentDTO) {
        try {
            UserRequestResponseDTO userRequestResponseDTO = getUserByFirebaseId.execute(userFirebaseContentDTO);
            if (userRequestResponseDTO == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(userRequestResponseDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/update-email")
    public Response updateEmail(@Valid UpdateEmailRequestDTO updateEmailReqDTO) {
        try {
            UserRequestResponseDTO updatedSysUserDTO = updateUserEmailUseCase.execute(updateEmailReqDTO);
            if (updatedSysUserDTO == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedSysUserDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/update-password")
    public Response updateFirebasePassword(@Valid UpdatePasswordRequestDTO updatePasswordReqDTO) {
        try {
            if(updateUserFirebasePasswordUseCase.execute(updatePasswordReqDTO)){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}