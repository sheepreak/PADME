package application.observation.domain;

import application.medicalfile.domain.MedicalFile;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Observation {

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Integer id;

    @NotNull
    @Column
    private Integer staffId;

    @NotNull
    @Column
    private String comment;

    @NotNull
    @Column
    private final Date date;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEDICALFILE_ID")
    private MedicalFile file;

    public Observation(int staffId, String comment){

        this.staffId = staffId;
        this.comment = comment;
        this.date = Date.valueOf(LocalDate.now());

    }


    public Observation() {
        this.date = Date.valueOf(LocalDate.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

}
