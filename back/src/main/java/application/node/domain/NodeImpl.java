package application.node.domain;

import application.node.Node;
import application.node.NodeLevel;

import java.util.ArrayList;
import java.util.List;

public class NodeImpl implements Node {

    private Long id;
    private String speciality;
    private NodeLevel level;
    private List<Node> subNodes = new ArrayList<>();

    public NodeImpl(){

    }
    public void addNode(Node node){
        subNodes.add(node);
    }
    public List<Node> getSubNodes(){
        return subNodes;
    }
    public Long getId(){
        return id;
    }
    public String getSpeciality(){
        return speciality;
    }
    public Enum<NodeLevel> getLevel(){

    }
    public boolean isNodePole(){

    }
}
