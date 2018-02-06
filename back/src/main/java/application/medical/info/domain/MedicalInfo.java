package application.medical.info.domain;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class MedicalInfo {

    @Id
    @GeneratedValue
    @Column
    Integer id;

    @Column
    Map<String, List> informations;

    public MedicalInfo() {
        informations = new HashMap<String, List>();
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setInformations(Map<String, List> informations) {
        this.informations = informations;
    }

    public Map<String, List> getInformations() {
        return informations;
    }

    public void addInformations(Map<String, List> map) {
        informations.putAll(map);
    }

}
