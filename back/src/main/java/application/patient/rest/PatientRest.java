package application.patient.rest;

import application.adminfile.domain.AdminFile;
import application.examen.domain.Examen;
import application.examen.repository.ExamenRepository;
import application.filters.IJWTTokenNeeded;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.observation.domain.Observation;
import application.patient.domain.Patient;
import application.patient.domain.PatientListing;
import application.patient.repository.PatientRepository;
import application.prescription.domain.Prescription;

import javax.ejb.EJB;
import javax.resource.spi.AuthenticationMechanism;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/patient")
public class PatientRest {

    @EJB
    private PatientRepository repository;
    @EJB
    private MedicalFileRepository medicalFileRepository;
    @EJB
    private ExamenRepository examenRepository;
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") Integer id) {
        Patient file = repository.find(id);
        if(file == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(file).build();
    }

    @POST
    @IJWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient file) {
        repository.save(file);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(file.getId().toString()).build();
        return Response.created(fileUri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients(){
        List<Patient> files = repository.getFiles();
        List<PatientListing> l =files.stream().map(s-> {
            AdminFile a =s.getAdminFile();
            return new PatientListing(
                    s.getId(), a.getFirstName(), a.getLastName(),
                    a.getGender(), a.getBirthDate(), a.getCountry());
        }).collect(Collectors.toList());
        GenericEntity<List<PatientListing>> entities = new GenericEntity<List<PatientListing>>(l){};
        return Response.ok(entities).build();
    }


    @GET
    @IJWTTokenNeeded
    @Path("{id}/adminfile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminFile(@PathParam("id") Integer patientId) {
        Patient patient = repository.find(patientId);
        return Response.ok(patient.getAdminFile()).build();
    }

    @PUT
    @IJWTTokenNeeded
    @Path("{id}/adminfile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAdminFile(@PathParam("id") Integer patientId, AdminFile adminFile) {
        Patient patient = repository.find(patientId);
        patient.setAdminFile(adminFile);
        try {
            repository.update(patient);
        } catch(Exception e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.status(Status.ACCEPTED).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicalFile(@PathParam("id") Integer id, MedicalFile file) {
        Patient patient = repository.find(id);
        medicalFileRepository.save(file);
        patient.addMedicalFile(file);
        repository.update(patient);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(patient.getId().toString()).build();
        return Response.created(fileUri).build();
    }

    @PUT
    @Path("addobservation/{medid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addObservation(@PathParam("medid") Integer fileId, Observation observation) {
        MedicalFile medicalFile = medicalFileRepository.getFile(fileId);
        medicalFile.addObservation(observation);
        medicalFileRepository.update(medicalFile);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(medicalFile.getId().toString()).build();
        return Response.created(fileUri).build();
    }

    @PUT
    @Path("addprescription/{medid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrescription(@PathParam("medid") Integer fileId, Prescription prescription) {
        MedicalFile medicalFile = medicalFileRepository.getFile(fileId);
        medicalFile.addPrescription(prescription);
        medicalFileRepository.update(medicalFile);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(medicalFile.getId().toString()).build();
        return Response.created(fileUri).build();
    }

    @PUT
    @Path("addexam/{medid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addExam(@PathParam("medid") Integer fileId, Examen examen) {
        MedicalFile medicalFile = medicalFileRepository.getFile(fileId);
        int idExamen = examenRepository.save(examen);
        medicalFile.addExamen(examen);
        medicalFileRepository.update(medicalFile);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(medicalFile.getId().toString()).build();
        return Response.ok("{\n \"idExamen\":"+idExamen+"\n}").build();
    }
}
