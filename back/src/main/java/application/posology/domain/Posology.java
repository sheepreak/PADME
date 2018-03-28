package application.posology.domain;

import application.prescription.domain.Prescription;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Posology {

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Integer id;

    @Column
    private String date;

    @NotNull
    @Column
    private String observation;

    @NotNull
    @Column
    private boolean taken;

    @NotNull
    @Column
    private String nurseName;

    @NotNull
    @Column
    private String nurseSurname;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "PRESCRIPTION_ID")
    private Prescription prescription;

    public Posology() {

        this.date = String.valueOf(LocalDateTime.now());

    }

    public Posology(String date, String observation, String nurseName, String nurseSurname, boolean taken) {

        this.date = date;
        this.observation = observation;
        this.nurseName = nurseName;
        this.nurseSurname = nurseSurname;
        this.taken = taken;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getNurseSurname() {
        return nurseSurname;
    }

    public void setNurseSurname(String nurseSurname) {
        this.nurseSurname = nurseSurname;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

}
