package application.staff.rest;

import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.node.domain.Node;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.messaging.jmq.io.Status;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Path("/staff")
public class StaffRest {

    public static final String KEY = "Code secret trop lol";

    @EJB
    private StaffRepository staffRepository;

    @EJB
    private MedicalFileRepository medicalFileRepository;

    @GET
    @Path("/patients/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients(@PathParam("id") Integer id) {

        Staff staff = staffRepository.find(id);
        List<Map<String,String>> maps = new ArrayList<>();
        List<Node> leafs = staff.getLeaves();
        for(Node node : leafs) {
            List<MedicalFile> medicalFiles = medicalFileRepository.findFilesByNode(node.getId());
            for(MedicalFile medicalFile : medicalFiles) {
                maps.add(medicalFile.getPatientInformations());
            }
        }

        return Response.ok(maps).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response putStaff(Staff staff) {
        if(staff == null)
            Response.status(Response.Status.BAD_REQUEST).build();
        staffRepository.save(staff);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") Integer id) {
        Staff staff = staffRepository.find(id);
        if(staff == null)
            return Response.status(Status.BAD_REQUEST).build();
        return Response.ok(staff).build();
    }

    @POST
    @Path("/connect")
    @Produces(MediaType.APPLICATION_JSON)
    public Response connect(String identification) {

        Staff staff;

        try {

            JSONObject jsonObject = new JSONObject(identification);

            if((staff = staffRepository.tryConnection(jsonObject.getString("password"), jsonObject.getString("login"))) == null)
                return Response.status(Response.Status.NOT_FOUND).build();


        } catch (JSONException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {

            Algorithm algorithm = Algorithm.HMAC256(KEY);
            String token = JWT.create().withIssuer("auth0").sign(algorithm);
            staff.setToken(token);
            return Response.ok("{\"token\" : \"" + token + "\"}").build();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

}
