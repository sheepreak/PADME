package application.hospital.rest;

import application.hospital.Hospital;
import application.hospital.repository.HospitalImplRepository;
import application.node.Node;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/hospital")
public class HospitalImplRestService {

    @EJB
    private HospitalImplRepository repository;
    @Context
    private UriInfo uriInfo ;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHospital(Hospital hospital){
        return null;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHospital(@PathParam("id") Long id) {
        return null;
    }

    @GET
    public Response getHospitals() {
        return null;
    }

    @GET
    @Path("{id}")
    public Response getHospital(@PathParam("id") Long id) {
        return null;
    }

    @POST
    @Path("{id}")
    public Response addNodePole(@PathParam("id") Long id, Node nodePole){
        return null;
    }

}
