package application.medicalfile.domain;


import application.examen.domain.Examen;
import application.observation.domain.Observation;
import application.patient.domain.Patient;
import application.prescription.domain.Prescription;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static application.medicalfile.domain.MedicalFile.FIND_ALL;
import static javax.persistence.CascadeType.ALL;

@Entity
@NamedQuery(name = FIND_ALL, query = "SELECT m FROM MedicalFile m ORDER BY m.id DESC")
public class MedicalFile {

    public static final String FIND_ALL = "MedicalFile.findAllFiles";

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

    /*@NotNull
    @Column
    @ManyToOne()
    @JoinColumn(name = "id")
    private Node node;*/

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "PATIENT_ID")
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

    /*public void setNode(Node node){
        this.node = node;
    }*/


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
}
