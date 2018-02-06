package application.patient.rest;

import application.patient.domain.Patient;
import application.patient.repository.PatientRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

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
        URI fileUri = uriInfo.getBaseUriBuilder().path(Patient.class).path(file.getPatientId().toString()).build();
        return Response.created(fileUri).build();
    }

}
