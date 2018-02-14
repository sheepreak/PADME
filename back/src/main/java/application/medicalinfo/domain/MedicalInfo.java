package application.medicalinfo.domain;

import java.util.HashMap;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Entity
class InfoAttributes {

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Integer id;

    @ElementCollection
    private List<String> attributes;
}

@Entity
public class MedicalInfo {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @OneToMany
    private Map<String, InfoAttributes> informations;

    public MedicalInfo() {
        informations = new HashMap<>();
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setInformations(Map<String, InfoAttributes> informations) {
        this.informations = informations;
    }

    public Map<String, InfoAttributes> getInformations() {
        return informations;
    }

    public void addInformations(Map<String, InfoAttributes> map) {
        informations.putAll(map);
    }

}
