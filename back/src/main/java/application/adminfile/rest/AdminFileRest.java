package application.adminfile.rest;

import application.adminfile.domain.AdminFile;
import application.adminfile.repository.AdminFileRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("/adminfile")
public class AdminFileRest {

    @EJB
    private AdminFileRepository repository;
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@PathParam("id") Integer id) {
        AdminFile file = repository.find(id);
        if(file == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(file).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdminFile(AdminFile file) {
        repository.save(file);
        URI fileUri = uriInfo.getBaseUriBuilder().path(AdminFileRest.class).path(file.getId().toString()).build();
        return Response.created(fileUri).build();
    }
}
