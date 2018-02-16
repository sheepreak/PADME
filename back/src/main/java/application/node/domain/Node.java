package application.node.domain;

import application.node.INode;
import application.node.NodeLevel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Node implements INode {


    @Id
    @GeneratedValue
    private Long id;

    private String speciality;
    private NodeLevel level;
    @OneToMany
    private List<Node> subNodes;

    public Node(){
    }
    @Override
    public void setSpeciality(String speciality){this.speciality=speciality;}
    @Override
    public void setLevel(NodeLevel level){this.level=level;}
    public void setSubNodes(List<Node> subNodes){this.subNodes=subNodes;}
    @Override
    public Node addNode(Node node){
        return null;
    }
    @Override
    public List<Node> getSubNodes(){return subNodes;}
    @Override
    public Long getId(){return id;}
    @Override
    public String getSpeciality(){return speciality;}
    @Override
    public NodeLevel getLevel(){return level;}
    @Override
    public String toString(){
        return "Node("
                +"id:"+id
                +"\tSpeciality:"+speciality
                +"\tLevel:"+level
                +"\tsubNode:"+subNodes+")";
    }
}
