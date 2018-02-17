package application.node;

public enum NodeLevel {
    pole(1),
    service(2),
    hospitalUnit(3),
    healthCareUnit(4);

    private final int hierarchyLevel;

    private NodeLevel(int level){
        this.hierarchyLevel = level;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }
}
