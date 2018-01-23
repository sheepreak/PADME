package application.node;

import java.util.List;

public interface Node {
    public void addNode(Node node);
    public List<Node> getSubNodes();
    public Long getId();
    public String getSpeciality();
    public Enum getLevel();
    public boolean isNodePole();
}
