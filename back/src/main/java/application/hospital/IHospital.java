package application.hospital;

import application.node.Node;
import java.nio.file.Path;
import java.util.List;

public interface IHospital {
    public boolean addNodePole(Node nodePole);
    public void initHierarchy(Path path);
    public Node getPosition(Node node);
    public Long getId();
    public String getName();
    public String getCountry();
    public String getAddress();
    public List<Node> getHierarchy();
}
