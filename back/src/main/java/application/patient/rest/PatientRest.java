package application.patient.rest;

import application.patient.domain.Patient;
import application.patient.repository.PatientRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/patient")
public class PatientRest {

    @EJB
    private PatientRepository repository;
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") Integer id) {
        Patient file = repository.find(id);
        if(file == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(file).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient file) {
        repository.save(file);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(file.getPatientId().toString()).build();
        return Response.created(fileUri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients(){
        List<Patient> files = repository.getFiles();
        GenericEntity<List<Patient>> entities = new GenericEntity<List<Patient>>(files){};
        return Response.ok(entities).build();
    }
}
