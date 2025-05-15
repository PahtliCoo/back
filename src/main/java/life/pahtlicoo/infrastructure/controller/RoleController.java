package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.role.CreateRoleReqDTO;
import life.pahtlicoo.application.dto.role.UpdateRoleNameReqDTO;
import life.pahtlicoo.application.usecase.role.*;
import life.pahtlicoo.domain.model.Role;

import java.util.List;

@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleController {
    @Inject
    CreateRoleUseCase createRoleUseCase;
    @Inject
    GetRoleUseCase getRoleUseCase;
    @Inject
    GetAllRolesUseCase getAllRolesUseCase;
    @Inject
    UpdateRoleNameUseCase updateRoleNameUseCase;
    @Inject
    DeleteRoleUseCase deleteRoleUseCase;

    @POST
    @Path("/create")
    public Response createRole(CreateRoleReqDTO createRoleReqDTO) {
        try{
            createRoleUseCase.execute(createRoleReqDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{role_id}")
    public Response getRole(@PathParam("role_id") int roleId) {
        Role role = getRoleUseCase.execute(roleId);
        if (role == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(role).build();
    }

    @GET
    @Path("/all")
    public Response getAllRoles() {
        List<Role> roles = getAllRolesUseCase.execute();
        if(roles == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(roles).build();
    }

    @PATCH
    @Path("/{role_id}")
    public Response updateRoleName(@PathParam("role_id") int roleId, UpdateRoleNameReqDTO updateRoleNameReqDTO) {
        updateRoleNameUseCase.execute(roleId, updateRoleNameReqDTO.getName());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{role_id}")
    public Response deleteRole(@PathParam("role_id") int roleId) {
        deleteRoleUseCase.execute(roleId);
        return Response.ok().build();
    }
}
