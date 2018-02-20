package application.patient.rest;

import application.examen.domain.Examen;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.observation.domain.Observation;
import application.patient.domain.Patient;
import application.patient.repository.PatientRepository;
import application.prescription.domain.Prescription;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/patient")
public class PatientRest {

    @EJB
    private PatientRepository repository;
    @EJB
    private MedicalFileRepository medicalFileRepository;
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") Integer id) {
        Patient file = repository.find(id);
        if(file == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(file).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient file) {
        repository.save(file);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(file.getPatientId().toString()).build();
        return Response.created(fileUri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients(){
        List<Patient> files = repository.getFiles();
        GenericEntity<List<Patient>> entities = new GenericEntity<List<Patient>>(files){};
        return Response.ok(entities).build();
    }

    @GET
    @Path("{id}/adminfile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminFile(@PathParam("id") Integer patientId) {
        Patient patient = repository.find(patientId);
        return Response.ok(patient.getAdminFile()).build();
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicalFile(@PathParam("id") Integer id, MedicalFile file) {
        Patient patient = repository.find(id);
        medicalFileRepository.save(file);
        patient.addMedicalFile(file);
        repository.update(patient);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(patient.getPatientId().toString()).build();
        return Response.created(fileUri).build();
    }

    @POST
    @Path("{id}/addobservation/{medid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addObservation(@PathParam("id") Integer id, @PathParam("medid") Integer fileId, Observation observation) {
        Patient patient = repository.find(id);
        patient.addObservation(fileId, observation);
        repository.update(patient);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(patient.getPatientId().toString()).build();
        return Response.created(fileUri).build();
    }

    @POST
    @Path("{id}/addprescription/{medid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrescription(@PathParam("id") Integer id, @PathParam("medid") Integer fileId, Prescription prescription) {
        Patient patient = repository.find(id);
        patient.addPrescription(fileId, prescription);
        repository.update(patient);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(patient.getPatientId().toString()).build();
        return Response.created(fileUri).build();
    }

    @POST
    @Path("{id}/addexam/{medid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addExam(@PathParam("id") Integer id, @PathParam("medid") Integer fileId, Examen examen) {
        Patient patient = repository.find(id);
        patient.addExam(fileId, examen);
        repository.update(patient);
        URI fileUri = uriInfo.getBaseUriBuilder().path(PatientRest.class).path(patient.getPatientId().toString()).build();
        return Response.created(fileUri).build();
    }


}
