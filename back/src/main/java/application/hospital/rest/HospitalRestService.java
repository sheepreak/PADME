package application.hospital.rest;

import application.filters.IJWTTokenNeeded;
import application.hospital.domain.Hospital;
import application.hospital.repository.HospitalRepository;
import application.node.domain.Node;

import javax.ejb.EJB;
import javax.ejb.NoSuchEntityException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/hospital")
public class HospitalRestService {

    @EJB
    private HospitalRepository repository;
    @Context
    private UriInfo uriInfo ;

    @POST
    @IJWTTokenNeeded
    @Path("A")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHospital(Hospital hospital){
        repository.save(hospital);
        URI hospitalUri = uriInfo
                .getBaseUriBuilder()
                .path(HospitalRestService.class)
                .path(hospital.getId().toString())
                .build();
        return Response.created(hospitalUri).build();
    }

    @DELETE
    @IJWTTokenNeeded
    @Path("A/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHospital(@PathParam("id") Long id) {
        try {
            repository.delete(id);
        } catch (NoSuchEntityException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @IJWTTokenNeeded
    @Path("ADNS")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHospitals() {
        List<Hospital> hospitals = repository.list();
        return Response.ok(hospitals).build();
    }

    @GET
    @IJWTTokenNeeded
    @Path("ADNS/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHospital(@PathParam("id") Long id) {
        Hospital hospital= repository.find(id);
        if (hospital == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(hospital).build();
    }

    @PUT
    @IJWTTokenNeeded
    @Path("A/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNodePole(@PathParam("id") Long id, Node nodePole){
        Hospital hospital = repository.find(id);
        if (hospital == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        hospital.addNodePole(nodePole);
        repository.update(hospital);
        return Response.ok(hospital).build();
    }
}
