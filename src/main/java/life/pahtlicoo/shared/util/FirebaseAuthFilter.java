package life.pahtlicoo.shared.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.container.ResourceInfo;
import life.pahtlicoo.shared.annotation.NoAuthRequired;

import jakarta.annotation.Priority;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class FirebaseAuthFilter implements ContainerRequestFilter {

    @Context
    ResourceInfo resourceInfo; //Permite inspeccionar la clase y metodo objetivo

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Verifica si tiene @NoAuthRequired
        if (resourceInfo.getResourceMethod().isAnnotationPresent(NoAuthRequired.class) ||
                resourceInfo.getResourceClass().isAnnotationPresent(NoAuthRequired.class)) {
            return; //No requiere autenticación
        }

        String authHeader= requestContext.getHeaderString("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authorization header must be provided")
                    .build());
            return;
        }
        String token= authHeader.substring("Bearer".length()).trim();
        try{
            //DEV ENVIRONMENT ONLY
            if(token.equals("testtoken")){
                requestContext.setProperty("userId", "testuser");
                System.out.println("testuser");
                return;
            }
            FirebaseToken decodedToken= FirebaseAuth.getInstance().verifyIdToken(token);
            requestContext.setProperty("userId", decodedToken.getUid());
            System.out.println(decodedToken.getUid());
        } catch (FirebaseAuthException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token")
                    .build());
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid firebase token")
                    .build());
        }
    }
}
