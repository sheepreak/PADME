package application.prescription.domain;

import application.medicalfile.domain.MedicalFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Prescription {


    @Id
    @NotNull
    @Column
    private Integer id;

    @NotNull
    @Column
    private String description;

    @NotNull
    @Column
    private Integer staffId;

    @ManyToOne(fetch= FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEDICALFILE_ID")
    private MedicalFile file;


    public Prescription() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
