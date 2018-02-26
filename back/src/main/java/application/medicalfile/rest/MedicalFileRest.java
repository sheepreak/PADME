package application.medicalfile.rest;

import application.examen.domain.Examen;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Path("/medicalFile")
public class MedicalFileRest {

    @EJB
    private MedicalFileRepository repository;

    @Context
    private UriInfo uriInfo;

    private static java.nio.file.Path PATH = Paths.get("/img");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalFiles(){
        List<MedicalFile> files = repository.getFiles();
        GenericEntity<List<MedicalFile>> entities = new GenericEntity<List<MedicalFile>>(files){};
        return Response.ok(entities).build();
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putExam(@PathParam("id") Integer id, Examen examen){
        MedicalFile medicalFile = repository.getFile(id);
        medicalFile.addExamen(examen);
        repository.update(medicalFile);
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response putMedicalFiles(MedicalFile medicalFile){
        if(medicalFile == null)
            return Response.noContent().build();
        repository.save(medicalFile);
        return Response.ok().build();
    }

    @POST
    @Path("/image/{id}")
    @Consumes({"image/jpeg", "image/png"})
    public Response uploadImage(InputStream in, @HeaderParam("Content-Type") String fileType,
                                @HeaderParam("Content-Length") long fileSize, @PathParam("id") Integer id) throws IOException {

        // Make sure the file is not larger than the maximum allowed size.
        if (fileSize < 0) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Image is too small").build());
        }

        // Generate a random file name based on the current time.
        // This probably isn't 100% safe but works fine for this example.
        String fileName = String.valueOf(LocalDateTime.now());

        if (fileType.equals("image/jpeg")) {
            fileName += ".jpg";
        } else {
            fileName += ".png";
        }

        // Copy the file to its location.
        Files.copy(in, PATH, StandardCopyOption.REPLACE_EXISTING);

        // Return a 201 Created response with the appropriate Location header.
        URI path = uriInfo.getAbsolutePathBuilder().path(fileName).build();
        return Response.status(Response.Status.CREATED).location(path).build();
    }

    @GET
    @Path("{name}.jpg")
    @Produces("image/jpeg")
    public InputStream getJpegImage(@PathParam("name") String fileName) throws IOException {

        fileName += ".jpg";
        java.nio.file.Path dest = PATH.resolve(fileName);

        if (!Files.exists(dest)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Files.newInputStream(dest);
    }

}
