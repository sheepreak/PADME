package application.filters;

import application.staff.Status;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;
import com.sun.tools.javac.util.List;

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

        if(staff == null)
            authorized = false;

        else if(staff.getStatus() == Status.DOCTOR)
            authorized = filterForDoctor(requestContext.getUriInfo().getPath(), requestContext.getMethod());

        else if(staff.getStatus() == Status.NURSE)
            authorized = filterForNurse(requestContext.getUriInfo().getPath(), requestContext.getMethod());

        else if(staff.getStatus() == Status.ADMIN)
            authorized = filterForAdmin(requestContext.getUriInfo().getPath(), requestContext.getMethod());

        else if(staff.getStatus() == Status.SECRETARY)
            authorized = filterForSecretary(requestContext.getUriInfo().getPath(), requestContext.getMethod());

        if(authorized)
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

    }

    private boolean filterForDoctor(String uri, String methode) {

        if(methode.equals("GET")) {
            return uri.contains("staff/patients/") || uri.split("/")[0].contains("medicalFile");
        }
        if(methode.equals("PUT")) {
            return uri.split("/")[0].contains("medicalFile");
        }
        if(methode.equals("POST")) {
            return uri.equals("patient");
        }
        return false;

    }

    private boolean filterForNurse(String uri, String methode) {

        if(methode.equals("GET")) {
            return uri.contains("staff/patients/") || uri.split("/")[0].contains("medicalFile");
        }
        if(methode.equals("POST")) {
            return uri.equals("patient");
        }
        return false;

    }

    private boolean filterForAdmin(String uri, String methode) {

        if(methode.equals("GET")) {
            return !uri.contains("staff/patient");
        }
        if(methode.equals("PUT")) {
            return uri.contains("staff/update");
        }
        if(methode.equals("POST")) {
            return uri.equals("staff");
        }
        return false;

    }

    private boolean filterForSecretary(String uri, String methode) {

        if(methode.equals("GET")) {
            return uri.equals("patient") || uri.split("/")[1].equals("adminfile");
        }
        if(methode.equals("PUT")) {
            return uri.split("/")[0].contains("medicalFile") || uri.split("/")[1].equals("adminfile");
        }
        if(methode.equals("POST")) {
            return uri.equals("patient");
        }
        return false;

    }

}
