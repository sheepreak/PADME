package application.filters;

import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
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

        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        Staff staff = staffRepository.findByToken(token);

        boolean authorized = true;

        if (staff == null)
            authorized = false;

        switch(staff.getStatus()) {
            case DOCTOR :
                authorized = filterForDoctor(requestContext.getUriInfo().getPath(), requestContext.getMethod());
                break;
            case NURSE :
                authorized = filterForNurse(requestContext.getUriInfo().getPath(), requestContext.getMethod());
                break;
            case ADMIN :
                authorized = filterForAdmin(requestContext.getUriInfo().getPath(), requestContext.getMethod());
                break;
            case SECRETARY :
                authorized = filterForSecretary(requestContext.getUriInfo().getPath(), requestContext.getMethod());
                break;
        }

        if (!authorized)
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

    }

    private boolean filterForDoctor(String uri, String methode) {
        return uri.split("/")[1].contains("D");
    }

    private boolean filterForNurse(String uri, String methode) {

        return uri.split("/")[1].contains("N");

    }

    private boolean filterForAdmin(String uri, String methode) {

        return uri.split("/")[1].contains("A");

    }

    private boolean filterForSecretary(String uri, String methode) {

        return uri.split("/")[1].contains("S");

    }

}
