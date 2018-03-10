package application.observation.domain;

import application.medicalfile.domain.MedicalFile;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private final String date;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEDICALFILE_ID")
    private MedicalFile file;

    public Observation(int staffId, String comment){

        this.staffId = staffId;
        this.comment = comment;
        this.date = LocalDateTime.now().toString();

    }

    public Observation(int staffId, String comment, String date){

        this.staffId = staffId;
        this.comment = comment;
        this.date = date;

    }


    public Observation() {
        this.date = LocalDateTime.now().toString();
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

    public String getDate() {
        return date;
    }

}
