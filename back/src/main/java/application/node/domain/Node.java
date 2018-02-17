package application.node.domain;

import application.node.INode;
import application.node.NodeLevel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static application.node.domain.Node.FIND_ALL;
@Table(name="Node")
@Entity
@NamedQuery(name=FIND_ALL, query="SELECT n FROM Node n ORDER BY n.level ASC")
public class Node implements INode {

    public static final String FIND_ALL = "Node.findAllNodes";

    public void setSubNodes(List<Node> subNodes) {
        this.subNodes = subNodes;
    }
    @Id
    @GeneratedValue
    private Long id;

    @Size(min=2, max=50)
    @NotNull
    @Column(nullable=false)
    private String speciality;

    @NotNull
    @Column(nullable=false)
    private NodeLevel level;

    @OneToMany
    private List<Node> subNodes = new ArrayList<>();

    public Node(){
    }
    @Override
    public void setSpeciality(String speciality){
        this.speciality = Objects.requireNonNull(speciality);
    }
    @Override
    public void setLevel(NodeLevel level){
        this.level = Objects.requireNonNull(level);
    }
    @Override
    public Node addNode(Node node){
        NodeLevel nodeLevel = Objects.requireNonNull(node).getLevel();
        if(nodeLevel.getHierarchyLevel()-1 != level.getHierarchyLevel())
            throw new IllegalArgumentException("The node:"+node+" isn't a direct hierarchy child level of "+ this);
        if(!node.getSpeciality().equals(speciality))
            throw new IllegalArgumentException("The node:"+node+" haven't the same speciality of "+ this);
        subNodes.add(node);
        return this;
    }
    @Override
    public List<Node> getSubNodes(){
        return subNodes;
    }
    @Override
    public Long getId(){
        return id;
    }
    @Override
    public String getSpeciality(){
        return speciality;
    }
    @Override
    public NodeLevel getLevel(){
        return level;
    }
    @Override
    public String toString(){
        return "Node("
                +"id:"+id
                +"\tSpeciality:"+speciality
                +"\tLevel:"+level
                +"\tsubNode:"+subNodes+")";
    }
}
