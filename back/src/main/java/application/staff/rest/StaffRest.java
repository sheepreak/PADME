package application.staff.rest;

import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.node.domain.Node;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Path("/staff")
public class StaffRest {

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
            List<MedicalFile> medicalFiles = medicalFileRepository.findFilesByNode(node);
            for(MedicalFile medicalFile : medicalFiles) {
                maps.add(medicalFile.getPatientInformations());
            }
        }
        return Response.ok(maps).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPatients(Staff staff) {
        staffRepository.save(staff);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPatients(@PathParam("id") Integer id) {
        Staff staff = staffRepository.find(id);
        return Response.ok(staff).build();
    }

}
