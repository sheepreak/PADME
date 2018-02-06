package application.node;

import java.util.List;

public interface Node {

    public void addNode(Node node);
    public List<Node> getSubNodes();
    public Long getId();
    public String getSpeciality();
    public Enum<NodeLevel> getLevel();
    public boolean isNodePole();
}
