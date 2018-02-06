package application.examen.rest;

import application.examen.domain.Examen;
import application.examen.repository.ExamenRepository;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/examen")
public class ExamenRest {

    @EJB
    private ExamenRepository repository;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExamen(){
        List<Examen> files = repository.getExams();
        GenericEntity<List<Examen>> entities = new GenericEntity<List<Examen>>(files){};
        return Response.ok(entities).build();
    }

}
