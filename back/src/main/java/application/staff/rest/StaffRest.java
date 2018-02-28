package application.staff.rest;

import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.node.domain.Node;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.messaging.jmq.io.Status;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
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
        List<Node> leafs = staff.leaves();
        for(Node node : leafs) {
            List<MedicalFile> medicalFiles = medicalFileRepository.findFilesByNode(node.getId());
            for(MedicalFile medicalFile : medicalFiles) {
                maps.add(medicalFile.patientInformations());
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
        return Response.ok("{\"id\" : \"" + staff.getId() + "\"}").build();

    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStaff(Staff staff) {

        if(staff == null)
            Response.status(Response.Status.BAD_REQUEST).build();

        staffRepository.update(staff);
        return Response.ok(Response.Status.OK).build();

    }


    @PUT
    @Path("/updatesocio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStaffSocio(Staff staff) {

        if(staff == null)
            Response.status(Response.Status.BAD_REQUEST).build();

        staffRepository.updateDataStaff(staff);
        return Response.ok(Response.Status.OK).build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStaff(@PathParam("id") Integer id) {
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
                return Response.status(Response.Status.UNAUTHORIZED).build();


        } catch (JSONException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {

            Algorithm algorithm = Algorithm.HMAC256(KEY);
            String token = JWT.create().withIssuer("auth0").sign(algorithm);
            staff.setToken(token);
            return Response.ok(staff.staffConnectionInfo()).build();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStaffs() {
        List<Staff> staffs = staffRepository.getStaffs();
        return Response.ok(getStaffInfo(staffs)).build();
    }


    private String getStaffInfo(List<Staff> staffs) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Staff s : staffs) {
            if(sb.toString().length() > 1)
                sb.append(",");
            sb.append("{ ")
                    .append("\"lastName\" : \"").append(s.getLastName()).append("\",")
                    .append("\"firstName\" : \"").append(s.getFirstName()).append("\",")
                    .append("\"node\" :").append(s.getNode())
                    .append("}");
        }

        sb.append("]");
        return sb.toString();
    }

}
