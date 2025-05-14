package life.pahtlicoo.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import life.pahtlicoo.application.dto.user.CreateUserReqDTO;
import life.pahtlicoo.application.usecase.user.CreateUserUseCase;
import life.pahtlicoo.domain.model.User;

@Path("/user")
public class UserController {
    @Inject
    CreateUserUseCase createUserUseCase;

    @POST
    @Path("/createUser")
    public Response createUser(CreateUserReqDTO createUserReqDTO) {
        try {
            User user = createUserUseCase.execute(createUserReqDTO);
            return Response.ok(user).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
