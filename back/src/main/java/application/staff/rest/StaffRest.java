package application.staff.rest;

import application.Node;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/staff")
public class StaffRest {

    @EJB
    private StaffRepository staffRepository;

    @EJB
    private MedicalFileRepository medicalFileRepository;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients(@PathParam("id") Integer id) {

        Staff staff = staffRepository.find(id);
        List<Map<String,String>> maps = new ArrayList<>();
        List<Node> leafs = staff.getLeafs();
        for(Node node : leafs) {
            List<MedicalFile> medicalFiles = medicalFileRepository.findFilesByNode(node);
            for(MedicalFile medicalFile : medicalFiles) {
                maps.add(medicalFile.getPatientInformations());
            }
        }
        return Response.ok(maps).build();

    }

}
