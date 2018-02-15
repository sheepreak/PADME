package application.node;

import application.node.domain.Node;

import java.util.List;

public interface INode {

    public void setSpeciality(String speciality);
    public void setLevel(NodeLevel level);
    public Node addNode(Node node);
    public List<Node> getSubNodes();
    public Long getId();
    public String getSpeciality();
    public NodeLevel getLevel();
}
