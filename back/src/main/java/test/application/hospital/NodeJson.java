package test.application.hospital;

import java.util.ArrayList;
import java.util.List;

public class NodeJson {

    private String speciality;

    private String level;

    private List<NodeJson> subNodes = new ArrayList<>();

    public NodeJson(){
    }
    public void setSpeciality(String speciality){
        this.speciality = speciality;
    }
    public void setLevel(String level){
        this.level = level;
    }
    public void setSubNodes(List<NodeJson> subNodes){
        this.subNodes = subNodes;
    }
    public List<NodeJson> getSubNodes(){
        return subNodes;
    }
    public String getSpeciality(){
        return speciality;
    }
    public String getLevel(){
        return level;
    }
}
