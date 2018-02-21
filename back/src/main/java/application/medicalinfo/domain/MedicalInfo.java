package application.medicalinfo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
class InfoAttributes {

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Integer id;

    @ElementCollection
    private List<String> attributes;

    public InfoAttributes(String ... values) {
        attributes = new ArrayList<>();
        attributes.addAll(Arrays.asList(values));
    }

    public InfoAttributes() {}

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
}

@Entity
public class MedicalInfo {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @OneToMany(cascade = CascadeType.PERSIST)
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

    public void addInformations(String key, String ... values) {
        informations.put(key, new InfoAttributes(values));
    }
}
