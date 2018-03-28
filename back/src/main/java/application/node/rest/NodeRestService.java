package application.node.rest;

import application.filters.IJWTTokenNeeded;
import application.node.domain.Node;
import application.node.repository.NodeRepository;

import javax.ejb.EJB;
import javax.ejb.NoSuchEntityException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/node")
public class NodeRestService {

    @EJB
    private NodeRepository repository;
    @Context
    private UriInfo uriInfo ;

    @POST
    @IJWTTokenNeeded
    @Path("A")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNode(Node node){
        repository.save(node);
        URI nodeUri = uriInfo
                .getBaseUriBuilder()
                .path(NodeRestService.class)
                .path(node.getId().toString())
                .build();
        return Response.created(nodeUri).build();
    }

    @DELETE
    @IJWTTokenNeeded
    @Path("A/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNode(@PathParam("id") Integer id) {
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
    public Response getNodes() {
        List<Node> nodes = repository.list();
        return Response.ok(nodes).build();
    }

    @GET
    @IJWTTokenNeeded
    @Path("ADNS/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNode(@PathParam("id") Integer id) {
        Node node= repository.find(id);
        if (node == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(node).build();
    }

    @PUT
    @IJWTTokenNeeded
    @Path("A/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNode(@PathParam("id") Integer id, Node node){
        Node nodeImpl = repository.find(id);
        if (nodeImpl == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        nodeImpl.addNode(node);
        repository.update(nodeImpl);
        return Response.ok(nodeImpl).build();
    }


    @PUT
    @IJWTTokenNeeded
    @Path("A")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNode(Node node){
        Node newNode = repository.find(node.getId());
        newNode.setLevel(node.getLevel());
        newNode.setSpeciality(node.getSpeciality());
        repository.update(newNode);
        return Response.ok().build();
    }
}