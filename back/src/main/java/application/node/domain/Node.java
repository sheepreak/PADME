package application.node.domain;

import application.hospital.domain.Hospital;
import application.node.NodeLevel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static application.node.domain.Node.FIND_ALL;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@NamedQuery(name=FIND_ALL, query="SELECT n FROM Node n ORDER BY n.level ASC")
public class Node{

    public static final String FIND_ALL = "Node.findAllNodes";

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, max=50)
    @NotNull
    @Column(nullable=false)
    private String speciality;

    @NotNull
    @Column(nullable=false)
    private NodeLevel level;

    @OneToMany(cascade=PERSIST)
    private List<Node> subNodes;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

    public Node(String speciality, NodeLevel level) {
        subNodes = new ArrayList<>();
        this.speciality = speciality;
        this.level = level;
    }

    public Node(){
        subNodes = new ArrayList<>();
    }

    public void setSpeciality(String speciality){
        this.speciality = Objects.requireNonNull(speciality);
    }

    public void setLevel(NodeLevel level){
        this.level = Objects.requireNonNull(level);
    }

    public Integer getId(){
        return id;
    }

    public String getSpeciality(){
        return speciality;
    }

    public NodeLevel getLevel(){
        return level;
    }

    public Node addNode(Node node){
        NodeLevel nodeLevel = Objects.requireNonNull(node).getLevel();
        if(nodeLevel.getHierarchyLevel()-1 != level.getHierarchyLevel())
            throw new IllegalArgumentException("The node:" + node + " isn't a direct hierarchy child level of "+ this);
        subNodes.add(node);
        return this;
    }

    public void setSubNodes(List<Node> subNodes) {
        this.subNodes = subNodes;
    }

    public List<Node> getSubNodes() {
        return this.subNodes;
    }

    public String toString(){
        return "{\"id\":"+id
                +",\"speciality\":\""+ speciality + "\""
                +",\"level\":\"" + level + "\"}";
    }

}
