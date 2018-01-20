package application.hospital.rest;

import application.hospital.Hospital;
import application.hospital.domain.HospitalImpl;
import application.hospital.repository.HospitalImplRepository;
import application.node.Node;

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
public class HospitalImplRestService {

    @EJB
    private HospitalImplRepository repository;
    @Context
    private UriInfo uriInfo ;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHospital(HospitalImpl hospital){
        repository.save(hospital);
        URI hospitalUri = uriInfo
                .getBaseUriBuilder()
                .path(HospitalImplRestService.class)
                .path(hospital.getId().toString())
                .build();
        return Response.created(hospitalUri).build();
    }

    @DELETE
    @Path("{id}")
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
    public Response getHospitals() {
        List<HospitalImpl> hospitals = repository.list();
        return Response.ok(hospitals).build();
    }

    @GET
    @Path("{id}")
    public Response getHospital(@PathParam("id") Long id) {
        Hospital hospital= repository.find(id);
        if (hospital == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(hospital).build();
    }

    @POST
    @Path("{id}")
    public Response addNodePole(@PathParam("id") Long id, Node nodePole){
        HospitalImpl hospital = repository.find(id);
        if (hospital == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        hospital.addNodePole(nodePole);
        repository.update(hospital);
        return Response.ok(hospital).build();
    }
}
