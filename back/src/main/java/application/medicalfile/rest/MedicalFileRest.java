package application.medicalfile.rest;

import application.examen.domain.Examen;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/medicalFile")
public class MedicalFileRest {

    @EJB
    private MedicalFileRepository repository;

    @Context
    private UriInfo uriInfo;

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


}
