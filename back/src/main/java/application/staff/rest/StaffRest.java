package application.staff.rest;

import application.filters.IJWTTokenNeeded;
import application.hospital.domain.Hospital;
import application.hospital.repository.HospitalRepository;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.node.domain.Node;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.messaging.jmq.io.Status;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Path("/staff")
public class StaffRest {

    public static final Random RANDOM = new Random();

    @EJB
    private StaffRepository staffRepository;

    @EJB
    private HospitalRepository hospitalRepository;

    @EJB
    private MedicalFileRepository medicalFileRepository;

    @GET
    @IJWTTokenNeeded
    @Path("DN/patients/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients(@PathParam("id") Integer id) throws JsonProcessingException {

        Staff staff = staffRepository.find(id);
        List<Map<String, Object>> maps = new ArrayList<>();
        List<Node> leafs = staff.leaves();

        for (Node node : leafs) {
            List<MedicalFile> medicalFiles = medicalFileRepository.findFilesByNode(node.getId());
            for (MedicalFile medicalFile : medicalFiles) {
                maps.add(medicalFile.patientInformations());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        return Response.ok(mapper.writeValueAsString(maps)).build();

    }

    @POST
    @Path("A")
    @IJWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStaff(Staff staff) {

        if (staff == null)
            Response.status(Response.Status.BAD_REQUEST).build();

        staffRepository.save(staff);
        return Response.ok("{\"id\" : \"" + staff.getId() + "\"}").build();

    }

    @PUT
    @IJWTTokenNeeded
    @Path("ADNS/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStaff(Staff staff) {

        if (staff == null)
            Response.status(Response.Status.BAD_REQUEST).build();

        staffRepository.update(staff);
        return Response.ok(Response.Status.OK).build();

    }


    @PUT
    @IJWTTokenNeeded
    @Path("ADNS/updatesocio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStaffSocio(Staff staff) {
        Staff oldStaff=staffRepository.find(staff.getId());

        if (staff == null)
            Response.status(Response.Status.BAD_REQUEST).build();

        staff.setNode(oldStaff.getNode());
        staff.setLogin(oldStaff.getLogin());
        staff.setPasswordWithoutEncode(oldStaff.getPassword());
        staffRepository.update(staff);

        return Response.ok(Response.Status.OK).build();

    }

    @GET
    @IJWTTokenNeeded
    @Path("A/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStaff(@PathParam("id") Integer id) {
        Staff staff = staffRepository.find(id);
        if (staff == null)
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

            if ((staff = staffRepository.tryConnection(jsonObject.getString("password"), jsonObject.getString("login"))) == null)
                return Response.status(Response.Status.UNAUTHORIZED).build();


        } catch (JSONException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {

            Algorithm algorithm = Algorithm.HMAC256(String.valueOf(RANDOM.doubles()));
            String token = JWT.create().withIssuer("auth0").sign(algorithm);
            staff.setToken(token);
            staffRepository.update(staff);
            return Response.ok(staff.staffConnectionInfo()).build();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @GET
    @IJWTTokenNeeded
    @Path("A")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStaffs(@Context UriInfo uri) {
        int page = -1;
        int size = -1;
        for (Map.Entry<String, List<String>> e : uri.getQueryParameters().entrySet()) {
            for (String value : e.getValue()) {
                if (e.getKey().equals("page")) {
                    page = Integer.parseInt(value);
                } else if (e.getKey().equals("size")) {
                    size = Integer.parseInt(value);
                }
            }
        }

        List<Staff> staffs;
        if (page > 0 && size > 0) {
            staffs = staffRepository.getStaffsWithPagination(page,size);
        } else {
            staffs = staffRepository.getStaffs();
        }
        return Response.ok(getStaffInfo(staffs)).build();
    }

    @PUT
    @Path("A/{id}/node")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNode(@PathParam("id") Integer id, Node node) {

        Staff staff = staffRepository.find(id);
        staff.setNode(node);
        staffRepository.update(staff);

        return Response.status(Status.ACCEPTED).build();

    }


    //------------- Private Helpers --------------

    private String getStaffInfo(List<Staff> staffs) {
        List<Hospital> hospitals = hospitalRepository.list();
        StringBuilder json = new StringBuilder();
        Map<Integer, List<Staff>> map = new HashMap<>();
        Integer key;
        List<Staff> tmpListStaff;

        for (Staff s : staffs) {

            key = s.getNode().getId();

            if (!map.containsKey(key))
                tmpListStaff = new ArrayList();
            else
                tmpListStaff = map.get(key);

            tmpListStaff.add(s);
            map.put(key, tmpListStaff);

        }

        json.append("[");

        for (Hospital h : hospitals) {
            List<Node> nodes = h.getHierarchy();
            for (Node n : nodes) {
                StringBuilder stringBuilder = new StringBuilder();
                json.append(parcourTree(n, stringBuilder, map));
            }
        }

        json.deleteCharAt(json.length() - 1);
        json.append("]");
        return json.toString();

    }

    private String parcourTree(Node node, StringBuilder hierarchy, Map<Integer, List<Staff>> map) {

        List<Staff> tmpListStaff;
        StringBuilder sb = new StringBuilder();
        StringBuilder tmpHierarchy = new StringBuilder(hierarchy.toString());

        tmpHierarchy.append(node.getId());

        if ((tmpListStaff = map.getOrDefault(node.getId(), Collections.EMPTY_LIST)) != Collections.EMPTY_LIST)
            for (Staff s : tmpListStaff)
                sb.append(initJson(s, tmpHierarchy.toString()));

        tmpHierarchy.append(",");

        for (Node n : node.getSubNodes())
            sb.append(parcourTree(n, tmpHierarchy, map));

        return sb.toString();

    }

    private String initJson(Staff s, String hierarchie) {

        StringBuilder sb = new StringBuilder();

        sb.append("{ ")
                .append("\"id\" : ").append(s.getId()).append(",")
                .append("\"lastName\" : \"").append(s.getLastName()).append("\",")
                .append("\"firstName\" : \"").append(s.getFirstName()).append("\",")
                .append("\"status\" : \"").append(s.getStatus()).append("\",")
                .append("\"hierarchy\" : [").append(hierarchie).append("],")
                .append("\"node\" :").append(s.getNode())
                .append("},");

        return sb.toString();

    }


}
