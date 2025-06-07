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

import life.pahtlicoo.application.usecase.sysuser.CreateSysUserUseCase;
import life.pahtlicoo.application.usecase.sysuser.GetUserByFirebaseId;
import life.pahtlicoo.application.usecase.sysuser.UpdateUserEmailUseCase;
import life.pahtlicoo.application.usecase.sysuser.UpdateUserFirebaseEmailUseCase;
import life.pahtlicoo.application.usecase.sysuser.UpdateUserPasswordUseCase;
import life.pahtlicoo.application.usecase.sysuser.UpdateUserFirebasePasswordUseCase;

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
    UpdateUserFirebasePasswordUseCase updateUserFirebasePasswordUseCase;

    @Inject
    UserResponseMapper userResponseMapper;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @NoAuthRequired
    public Response createUser(@Valid CreateSysUserReqDTO createSysUserReqDTO) {
        try {
            SysUser sysUser = createUserUseCase.execute(createSysUserReqDTO);
            if (sysUser == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            return Response.status(Response.Status.CREATED).entity(userResponseMapper.toDTO(sysUser)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/getUser")
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
            UserResponseDTO updatedSysUserDTO = updateUserEmailUseCase.execute(
                    updateEmailReqDTO.getSysUserId(), updateEmailReqDTO.getNewEmail()
            );
            if (updatedSysUserDTO == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedSysUserDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/firebase/update-email")
    public Response updateFirebaseEmail(@Valid UpdateEmailRequestDTO updateFirebaseEmailReqDTO) {
        try {
            UserResponseDTO resultDTO = updateUserFirebaseEmailUseCase.execute(
                    updateFirebaseEmailReqDTO.getFirebaseId(), updateFirebaseEmailReqDTO.getNewEmail()
            );
            if (resultDTO == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(resultDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/firebase/update-password")
    public Response updateFirebasePassword(@Valid UpdatePasswordRequestDTO updatePasswordReqDTO) {
        try {
            UserResponseDTO resultDTO = updateUserFirebasePasswordUseCase.execute(
                    updatePasswordReqDTO.getFirebaseId(), updatePasswordReqDTO.getNewPassword()
            );
            if (resultDTO == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(resultDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/unified-update-email")
    public Response unifiedUpdateEmail(@Valid UpdateEmailUnifiedRequestDTO updateEmailUnifiedReqDTO) {
        try {
            UserResponseDTO firebaseUpdateResult = updateUserFirebaseEmailUseCase.execute(
                    updateEmailUnifiedReqDTO.getFirebaseId(),
                    updateEmailUnifiedReqDTO.getNewEmail()
            );
            if (firebaseUpdateResult == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Firebase email update failed.").build();
            }
            UserResponseDTO localUpdateResult = updateUserEmailUseCase.execute(
                    updateEmailUnifiedReqDTO.getSysUserId(),
                    updateEmailUnifiedReqDTO.getNewEmail()
            );
            if (localUpdateResult == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Local email update failed after Firebase update.").build();
            }
            return Response.ok(localUpdateResult).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}