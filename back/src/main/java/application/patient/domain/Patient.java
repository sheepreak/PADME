package application.patient.domain;

import application.adminfile.domain.AdminFile;
import application.examen.domain.Examen;
import application.medicalfile.domain.MedicalFile;
import application.medicalinfo.domain.MedicalInfo;
import application.observation.domain.Observation;
import application.prescription.domain.Prescription;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static application.patient.domain.Patient.FIND_ALL;

@Entity
@NamedQuery(name = FIND_ALL, query = "SELECT p FROM Patient p ORDER BY p.id DESC")
public class Patient {

    public static final String FIND_ALL = "Patient.findAllPatients";

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @OneToMany(mappedBy ="patient")
    private List<MedicalFile> medicalFileList;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private AdminFile adminFile;

    @OneToOne(cascade = CascadeType.ALL)
    private MedicalInfo medicalInfo;

    @ElementCollection
    private Set<Integer> familyBackground;

    public Patient() {
        medicalFileList = new ArrayList<>();
        familyBackground = new HashSet<>();
    }

    public Patient(AdminFile adminFile, MedicalInfo medicalInfo) {
        this.adminFile = adminFile;
        this.medicalInfo = medicalInfo;
        medicalFileList = new ArrayList<>();
        familyBackground = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public AdminFile getAdminFile() {
        return adminFile;
    }

    public List<MedicalFile> getMedicalFileList() {
        return medicalFileList;
    }

    public void setId(Integer patientId) {
        this.id = patientId;
    }

    public void setMedicalFileList(List<MedicalFile> medicalFileList) {
        this.medicalFileList = medicalFileList;
    }

    public void setAdminFile(AdminFile adminFile) {
        this.adminFile = adminFile;
    }

    public MedicalInfo getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(MedicalInfo medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public Set<Integer> getFamilyBackground() {
        return familyBackground;
    }

    public void setFamilyBackground(Set<Integer> familyBackground) {
        this.familyBackground = familyBackground;
    }

    public void addMedicalFile(MedicalFile medicalFile) {

        if(!getMedicalFileList().contains(medicalFile)) {
            medicalFile.setPatient(this);
            medicalFileList.add(medicalFile);
        }

    }

    public void addObservation(Integer medicalFileId, Observation observation) {

        for (MedicalFile medicalFile: medicalFileList)
            if(medicalFile.getId().equals(medicalFileId))
                medicalFile.addObservation(observation);

    }

    public void addPrescription(Integer medicalFileId, Prescription prescription) {

        for (MedicalFile medicalFile: medicalFileList)
            if(medicalFile.getId().equals(medicalFileId))
                medicalFile.addPrescription(prescription);

    }

    public void addExam(Integer medicalFileId, Examen examen) {

        for (MedicalFile medicalFile: medicalFileList)
            if(medicalFile.getId().equals(medicalFileId))
                medicalFile.addExamen(examen);

    }

}
