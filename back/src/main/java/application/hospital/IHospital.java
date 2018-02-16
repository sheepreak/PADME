package application.hospital;

import application.node.domain.Node;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface IHospital {
    public boolean addNodePole(Node nodePole);
    public void initHierarchy(Path path) throws IOException;
    public Node getPosition(Node node);
    public Long getId();
    public String getName();
    public String getCountry();
    public String getAddress();
    public List<Node> getHierarchy();
}
