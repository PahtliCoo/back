package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import life.pahtlicoo.application.dto.sysuser.CreateSysUserReqDTO;
import life.pahtlicoo.application.dto.sysuser.UserFirebaseContentDTO;
import life.pahtlicoo.application.dto.sysuser.UserRequestResponseDTO;
import life.pahtlicoo.application.dto.sysuser.UpdateEmailRequestDTO;
import life.pahtlicoo.application.dto.sysuser.UpdatePasswordRequestDTO;
import life.pahtlicoo.application.dto.sysuser.UserResponseDTO;
import life.pahtlicoo.application.dto.sysuser.UpdateEmailUnifiedRequestDTO;

import life.pahtlicoo.application.usecase.sysuser.*;

import life.pahtlicoo.application.mapper.UserResponseMapper;

import life.pahtlicoo.domain.model.SysUser;
import life.pahtlicoo.shared.annotation.NoAuthRequired;

@Path("/sys-user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SysUserController {

    @Inject
    CreateSysUserUseCase createUserUseCase;
    @Inject
    GetUserByFirebaseId getUserByFirebaseId;

    @Inject
    UpdateUserEmailUseCase updateUserEmailUseCase;
    @Inject
    UpdateUserFirebaseEmailUseCase updateUserFirebaseEmailUseCase;
    @Inject
    UpdateUserPasswordUseCase updateUserPasswordUseCase;

    @Inject
    UpdateUserFirebasePasswordUseCase updateUserFirebasePasswordUseCas;

    @Inject
    UserResponseMapper userResponseMapper;


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

    @POST
    @Path("/update-email")
    public Response updateEmail(@Valid UpdateEmailRequestDTO requestDTO) {
        try {
            UserResponseDTO updatedSysUserDTO = updateUserEmailUseCase.execute(
                    requestDTO.getSysUserId(), requestDTO.getNewEmail()
            );
            return Response.ok(updatedSysUserDTO).build();
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Error al actualizar el email local: " + e.getMessage())
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error interno al actualizar el email local: " + e.getMessage())
                        .build();
            }
        }
    }


    @POST
    @Path("/firebase/update-email")
    public Response updateFirebaseEmail(@Valid UpdateEmailRequestDTO requestDTO) {
        try {
            UserResponseDTO resultDTO = updateUserFirebaseEmailUseCase.execute(
                    requestDTO.getFirebaseId(), requestDTO.getNewEmail()
            );
            return Response.ok(resultDTO).build();
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Error al actualizar el email en Firebase: " + e.getMessage())
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error interno al actualizar el email en Firebase: " + e.getMessage())
                        .build();
            }
        }
    }

    @POST
    @Path("/firebase/update-password")
    public Response updateFirebasePassword(@Valid UpdatePasswordRequestDTO requestDTO) {
        try {
            UserResponseDTO resultDTO = updateUserFirebasePasswordUseCas.execute(
                    requestDTO.getFirebaseId(), requestDTO.getNewPassword()
            );
            return Response.ok(resultDTO).build();
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Error al actualizar la contraseña en Firebase: " + e.getMessage())
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error interno al actualizar la contraseña en Firebase: " + e.getMessage())
                        .build();
            }
        }
    }

    @POST
    @Path("/unified-update-email")
    public Response unifiedUpdateEmail(@Valid UpdateEmailUnifiedRequestDTO requestDTO) {
        try {
            updateUserFirebaseEmailUseCase.execute(
                    requestDTO.getFirebaseId(),
                    requestDTO.getNewEmail()
            );

            UserResponseDTO localUpdateResult = updateUserEmailUseCase.execute(
                    requestDTO.getSysUserId(),
                    requestDTO.getNewEmail()
            );

            return Response.ok(localUpdateResult).build();

        } catch (Exception e) {
            if (e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Error unificado al actualizar el email: " + e.getMessage())
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error interno del servidor al actualizar el email de forma unificada: " + e.getMessage())
                        .build();
            }
        }
    }
}