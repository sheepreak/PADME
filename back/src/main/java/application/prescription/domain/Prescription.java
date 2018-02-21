package application.prescription.domain;

import application.medicalfile.domain.MedicalFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Prescription {


    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Integer id;

    @NotNull
    @Column
    private String treatment;

    @Column
    private String posology;

    @NotNull
    @Column
    private String startDate;

    @NotNull
    @Column
    private String endDate;

    @NotNull
    @Column
    private String prescriptionDate;

    @NotNull
    @Column
    private Integer staffId;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEDICALFILE_ID")
    private MedicalFile file;


    public Prescription() {
        prescriptionDate = LocalDateTime.now().toString();
    }

    public Prescription(String treatment, String posology, String startDate, String endDate, Integer staffId) {
        this.treatment = treatment;
        this.posology = posology;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prescriptionDate = LocalDateTime.now().toString();
        this.staffId = staffId;
    }

    public String getPosology() {
        return posology;
    }

    public void setPosology(String posology) {
        this.posology = posology;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
