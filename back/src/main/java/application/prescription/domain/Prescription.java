package application.prescription.domain;

import application.medicalfile.domain.MedicalFile;
import application.posology.domain.Posology;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.CascadeType.ALL;

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

    @NotNull
    @Column
    private String date;

    @NotNull
    @Column
    private String startDate;

    @NotNull
    @Column
    private String endDate;

    @NotNull
    @Column
    private Integer staffId;

    @OneToMany(cascade=ALL, mappedBy="prescription")
    private List<Posology> posologys;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEDICALFILE_ID")
    private MedicalFile file;


    public Prescription() {
        posologys = new ArrayList<>();
    }

    public Prescription(String treatment, List<Posology> posologys, String date, String startDate, String endDate, Integer staffId) {

        this.date = date;
        this.posologys = posologys;
        this.treatment = treatment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.staffId = staffId;

    }

    public List<Posology> getPosologys() {
        return posologys;
    }

    public void setPosologys(List<Posology> posologys) {
        this.posologys = posologys;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addPosology(Posology posology) {
        posologys.add(posology);
    }
}
