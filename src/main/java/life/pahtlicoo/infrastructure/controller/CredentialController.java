/**
 * DEV ENVIRONMENT ONLY
 * Credential Controller
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.credential.UpdateCredentialNameReqDTO;
import life.pahtlicoo.application.usecase.credential.*;
import life.pahtlicoo.domain.model.Credential;
import life.pahtlicoo.application.dto.credential.CreateCredentialReqDTO;

import java.util.List;

@Path("/credential")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CredentialController {
    @Inject
    CreateCredentialUseCase createCredentialUseCase;
    @Inject
    GetCredentialUseCase getCredentialUseCase;
    @Inject
    GetAllCredentialsUseCase getAllCredentialsUseCase;
    @Inject
    UpdateCredentialNameUseCase updateCredentialNameUseCase;
    @Inject
    DeleteCredentialUseCase deleteCredentialUseCase;

    @POST
    @Path("/create")
    public Response createCredential(CreateCredentialReqDTO createCredentialReqDTO) {
        try{
            createCredentialUseCase.execute(createCredentialReqDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{credential_id}")
    public Response getCredential(@PathParam("credential_id") int credentialId) {
        Credential credential = getCredentialUseCase.execute(credentialId);
        if (credential == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(credential).build();
    }

    @GET
    @Path("/all")
    public Response getAllCredentials() {
        List<Credential> credentials = getAllCredentialsUseCase.execute();
        if(credentials == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(credentials).build();
    }

    @PATCH
    @Path("/{credential_id}")
    public Response updateCredentialName(@PathParam("credential_id") int credentialId, UpdateCredentialNameReqDTO updateCredentialNameReqDTO) {
        updateCredentialNameUseCase.execute(credentialId, updateCredentialNameReqDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{credential_id}")
    public Response deleteCredential(@PathParam("credential_id") int credentialId) {
        deleteCredentialUseCase.execute(credentialId);
        return Response.ok().build();
    }
}
