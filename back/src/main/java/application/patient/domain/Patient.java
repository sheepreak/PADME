package application.patient.domain;

import application.adminfile.domain.AdminFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @NotNull
    @GeneratedValue
    @Column
    private Integer patientId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="medicalFile")
    private List<MedicalFile> medicalFileList;

    @OneToOne
    private AdminFile adminFile;

    @OneToOne
    private MedicalInfo medicalInfo;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @OneToMany(mappedBy = "patient")
    private Set<Patient> familyBackground;

    public Integer getPatientId() {
        return patientId;
    }
}
