package application.node.domain;

import application.node.INode;
import application.node.NodeLevel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Node implements INode {


    @Id
    @GeneratedValue
    private Long id;


    public Node(){
    }
    @Override
    public void setSpeciality(String speciality){}
    @Override
    public void setLevel(NodeLevel level){}
    @Override
    public Node addNode(Node node){
        return null;
    }
    @Override
    public List<Node> getSubNodes(){return null;}
    @Override
    public Long getId(){return null;}
    @Override
    public String getSpeciality(){return null;}
    @Override
    public NodeLevel getLevel(){return null;}
}
