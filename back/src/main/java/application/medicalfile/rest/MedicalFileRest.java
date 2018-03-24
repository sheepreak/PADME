package application.medicalfile.rest;

import application.examen.domain.Examen;
import application.examen.repository.ExamenRepository;
import application.filters.IJWTTokenNeeded;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.posology.domain.Posology;
import application.prescription.domain.Prescription;
import application.prescription.repository.PrescriptionRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Path("/medicalFile")
public class MedicalFileRest {

    @EJB
    private MedicalFileRepository medicalFileRepository;

    @EJB
    private ExamenRepository examRepository;

    @EJB
    private PrescriptionRepository prescriptionRepository;

    @Context
    private UriInfo uriInfo;

    private static java.nio.file.Path PATH = Paths.get("./img/");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalFiles(){
        List<MedicalFile> files = medicalFileRepository.getFiles();
        GenericEntity<List<MedicalFile>> entities = new GenericEntity<List<MedicalFile>>(files){};
        return Response.ok(entities).build();
    }

    @GET
    @IJWTTokenNeeded
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalFile(@PathParam("id") Integer id){
        MedicalFile file = medicalFileRepository.getFile(id);
        return Response.ok(file).build();
    }


    @PUT
    @IJWTTokenNeeded
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putExam(@PathParam("id") Integer id, Examen examen){
        MedicalFile medicalFile = medicalFileRepository.getFile(id);
        medicalFile.addExamen(examen);
        medicalFileRepository.update(medicalFile);
        return Response.ok().build();
    }

    @POST
    @IJWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public Response putMedicalFiles(MedicalFile medicalFile){
        if(medicalFile == null)
            return Response.noContent().build();
        medicalFileRepository.save(medicalFile);
        return Response.ok().build();
    }

    @POST
    @IJWTTokenNeeded
    @Path("/image/{id}")
    @Consumes({"image/jpeg", "image/png"})
    public Response uploadImage(InputStream in, @HeaderParam("Content-Type") String fileType,
                                @HeaderParam("Content-Length") long fileSize, @PathParam("id") Integer examId) throws IOException {

        if(!Files.exists(PATH))
            PATH.toFile().mkdir();

        if (fileSize < 0) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Image is too small").build());
        }

        Examen examen = examRepository.find(examId);
        if(examen == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String fileName = String.valueOf(UUID.randomUUID());

        if (fileType.equals("image/jpeg")) {
            fileName += ".jpg";
        } else {
            fileName += ".png";
        }

        // Copy the file to its location.
        Files.copy(in, PATH.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        examen.addImg(fileName);
        examRepository.update(examen);

        // Return a 201 Created response with the appropriate Location header.
        URI path = uriInfo.getAbsolutePathBuilder().path(fileName).build();
        return Response.status(Response.Status.CREATED).location(path).build();
    }

    @GET
    @IJWTTokenNeeded
    @Path("image/{name}")
    @Produces({"image/jpeg", "image/png"})
    public InputStream getImage(@PathParam("name") String fileName) throws IOException {

        java.nio.file.Path dest = PATH.resolve(fileName);

        if (!Files.exists(dest)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Files.newInputStream(dest);
    }

    @PUT
    @IJWTTokenNeeded
    @Path("{prescriptionId}/posology")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPosology(@PathParam("prescriptionId") int prescriptionId, Posology posology) {

        Prescription prescription = prescriptionRepository.find(prescriptionId);
        if(prescription == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        prescription.addPosology(posology);
        prescriptionRepository.update(prescription);

        return Response.status(Response.Status.CREATED).build();

    }


}
