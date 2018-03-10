package application.filters;

import application.staff.repository.StaffRepository;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@IJWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeeded implements ContainerRequestFilter {

    @EJB
    private StaffRepository staffRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Extract the token from the HTTP Authorization header
        String token = requestContext.getHeaders().getFirst("Bearer");

        try {

        } catch (Exception e) {

        }
    }
}
