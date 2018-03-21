package application.medicalfile.domain;

import application.adminfile.domain.AdminFile;
import application.examen.domain.Examen;
import application.medicalinfo.domain.MedicalInfo;
import application.observation.domain.Observation;
import application.patient.domain.Patient;
import application.prescription.domain.Prescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.GenericEntity;
import java.util.*;

import static application.medicalfile.domain.MedicalFile.FIND_ALL;
import static application.medicalfile.domain.MedicalFile.FIND_ALL_BY_NODE;
import static javax.persistence.CascadeType.ALL;


@NamedQueries({
        @NamedQuery(name = FIND_ALL, query = "SELECT m FROM MedicalFile m"),
        @NamedQuery(name = FIND_ALL_BY_NODE, query = "SELECT m FROM MedicalFile m WHERE m.nodeId = :custNode")
})
@Entity
public class MedicalFile {

    public static final String FIND_ALL = "MedicalFile.findAllFiles";
    public static final String FIND_ALL_BY_NODE = "MedicalFile.findAllFilesByNode";

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Integer id;

    @OneToMany(cascade=ALL, mappedBy="file")
    private List<Examen> exams;

    @OneToMany(cascade=ALL, mappedBy="file")
    private List<Prescription> prescriptions;

    @OneToMany(cascade=ALL, mappedBy="file")
    private List<Observation> observations;

    @NotNull
    @Column
    private Boolean status;

    @NotNull
    private Integer nodeId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "fk_patient")
    private Patient patient;


    public MedicalFile(){
        observations = new ArrayList<Observation>();
        prescriptions = new ArrayList<Prescription>();
        exams = new ArrayList<Examen>();
    }

    public MedicalFile(Boolean status){
        this.status = status;
        observations = new ArrayList<Observation>();
        prescriptions = new ArrayList<Prescription>();
        exams = new ArrayList<Examen>();
    }

    public MedicalFile(Boolean status, Integer nodeId) {
        this.status = status;
        this.nodeId = nodeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addPrescription(Prescription prescription){
        prescriptions.add(prescription);
    }

    public void addExamen(Examen examen){
        exams.add(examen);
    }

    public void addObservation(Observation observation){
        observations.add(observation);
    }

    public void setNode(Integer node){
        this.nodeId = node;
    }

    public Integer getNode(){
        return this.nodeId;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public List<Examen> getExams() {
        return exams;
    }

    public void setExams(List<Examen> exams) {
        this.exams = exams;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public MedicalInfo getMedicalInfo() {
        return this.patient.getMedicalInfo();
    }

    public Map<String, Object> patientInformations() {

        if(patient == null)
            return Collections.EMPTY_MAP;

        Map<String, Object> map = new HashMap<>();
        AdminFile adminFile = patient.getAdminFile();
        map.put("firstName", adminFile.getFirstName());
        map.put("lastName", adminFile.getLastName());
        map.put("gender", adminFile.getGender());
        map.put("country", adminFile.getCountry());
        map.put("birthDate", adminFile.getBirthDate());
        map.put("id", String.valueOf(patient.getId()));
        map.put("idMedicalFile", String.valueOf(id));

        return map;

    }


}
