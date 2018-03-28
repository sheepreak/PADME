package application.prescription.domain;

import application.medicalfile.domain.MedicalFile;
import application.posology.domain.Posology;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    private List<Posology> posologies;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEDICALFILE_ID")
    private MedicalFile file;


    public Prescription() {
        posologies = new ArrayList<>();
    }

    public Prescription(String treatment, List<Posology> posologys, String date, String startDate, String endDate, Integer staffId) {

        this.date = date;
        this.posologies = posologys;
        this.treatment = treatment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.staffId = staffId;

    }

    public List<Posology> getPosologys() {
        return posologies;
    }

    public void setPosologys(List<Posology> posologys) {
        this.posologies = posologys;
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
        posologies.add(posology);
    }
}
