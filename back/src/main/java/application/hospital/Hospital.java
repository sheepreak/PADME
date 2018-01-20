package application.hospital;

import application.node.Node;

import java.nio.file.Path;

public interface Hospital {
    public boolean addNodePole(Node nodePole);

    public void initHierarchy(Path path);

    public Node getPosition(Node node);
}
