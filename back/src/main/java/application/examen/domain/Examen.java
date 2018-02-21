package application.examen.domain;

import application.medicalfile.domain.MedicalFile;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

import static application.examen.domain.Examen.FIND_ALL;

@Entity
@NamedQuery(name = FIND_ALL, query = "SELECT m FROM Examen m ORDER BY m.id DESC")
public class Examen {

    public static final String FIND_ALL = "Examen.findAllFiles";

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Integer id;

    @NotNull
    @Column
    private String motive;

    @NotNull
    @Column
    private String description;

    @Column
    private String imgPath;

    @NotNull
    @Column
    private String observation;

    @NotNull
    @Column
    private String date;

    @NotNull
    @Column
    private Integer StaffId;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEDICALFILE_ID")
    private MedicalFile file;

    public Examen() {
        date = LocalDateTime.now().toString();
    }

    public Examen(String motive, String description, String imgPath, String observation, String date, Integer staffId) {
        this.motive = motive;
        this.description = description;
        this.imgPath = imgPath;
        this.observation = observation;
        this.date = date;
        StaffId = staffId;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStaffId() {
        return StaffId;
    }

    public void setStaffId(Integer staffId) {
        StaffId = staffId;
    }
}
