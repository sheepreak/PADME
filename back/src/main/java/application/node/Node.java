package application.node;

import application.node.domain.NodeImpl;

import java.util.List;

public interface Node {

    public void setSpeciality(String speciality);
    public void setLevel(NodeLevel level);
    public Node addNode(NodeImpl node);
    public List<NodeImpl> getSubNodes();
    public Long getId();
    public String getSpeciality();
    public NodeLevel getLevel();
}
