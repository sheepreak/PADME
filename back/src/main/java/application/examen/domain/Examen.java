package application.examen.domain;

import application.medicalfile.domain.MedicalFile;
import com.sun.istack.NotNull;

import javax.persistence.*;

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
    private String description;

    @Column
    private String imgPath;

    @NotNull
    @Column
    private String observation;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEDICALFILE_ID")
    private MedicalFile file;

    public Examen() {
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
}
