package test.application.node.domain;

import application.node.NodeLevel;
import application.node.Node;
import application.node.domain.NodeImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NodeImplTest {
    
    private NodeImpl poleUrgence = new NodeImpl();
    private NodeImpl poleChirurgie = new NodeImpl();

    private NodeImpl serviceUrgenceA = new NodeImpl();

    private NodeImpl serviceChirurgieA = new NodeImpl();
    private NodeImpl serviceChirurgieB = new NodeImpl();

    private NodeImpl hospUnitUrgenceAA = new NodeImpl();
    private NodeImpl hospUnitUrgenceAB = new NodeImpl();

    private NodeImpl hospUnitChirurgieAA = new NodeImpl();
    private NodeImpl hospUnitChirurgieAB = new NodeImpl();
    private NodeImpl hospUnitChirurgieBA = new NodeImpl();
    private NodeImpl hospUnitChirurgieBB = new NodeImpl();

    private NodeImpl healthCareUnitUrgenceAAA = new NodeImpl();
    private NodeImpl healthCareUnitUrgenceABA = new NodeImpl();

    private NodeImpl healthCareUnitChirurgieAAA = new NodeImpl();
    private NodeImpl healthCareUnitChirurgieAAB = new NodeImpl();
    private NodeImpl healthCareUnitChirurgieABA = new NodeImpl();
    private NodeImpl healthCareUnitChirurgieABB = new NodeImpl();
    private NodeImpl healthCareUnitChirurgieBAA = new NodeImpl();
    private NodeImpl healthCareUnitChirurgieBAB = new NodeImpl();
    private NodeImpl healthCareUnitChirurgieBBA = new NodeImpl();
    private NodeImpl healthCareUnitChirurgieBBB = new NodeImpl();

    private String chirurgy = "Chirurgy";
    private String urgence = "Urgence";

    private void initNode(NodeImpl node, String speciality, NodeLevel level){
        node.setLevel(level);
        node.setSpeciality(speciality);
    }

    private void initTestSet(){

        poleUrgence = new NodeImpl();
        poleChirurgie = new NodeImpl();

        serviceUrgenceA = new NodeImpl();

        serviceChirurgieA = new NodeImpl();
        serviceChirurgieB = new NodeImpl();

        hospUnitUrgenceAA = new NodeImpl();
        hospUnitUrgenceAB = new NodeImpl();

        hospUnitChirurgieAA = new NodeImpl();
        hospUnitChirurgieAB = new NodeImpl();
        hospUnitChirurgieBA = new NodeImpl();
        hospUnitChirurgieBB = new NodeImpl();

        healthCareUnitUrgenceAAA = new NodeImpl();
        healthCareUnitUrgenceABA = new NodeImpl();

        healthCareUnitChirurgieAAA = new NodeImpl();
        healthCareUnitChirurgieAAB = new NodeImpl();
        healthCareUnitChirurgieABA = new NodeImpl();
        healthCareUnitChirurgieABB = new NodeImpl();
        healthCareUnitChirurgieBAA = new NodeImpl();
        healthCareUnitChirurgieBAB = new NodeImpl();
        healthCareUnitChirurgieBBA = new NodeImpl();
        healthCareUnitChirurgieBBB = new NodeImpl();

        initNode(healthCareUnitChirurgieAAA, chirurgy, NodeLevel.healthCareUnit);
        initNode(healthCareUnitChirurgieAAB, chirurgy, NodeLevel.healthCareUnit);

        initNode(hospUnitChirurgieAA, chirurgy, NodeLevel.hospitalUnit);

        hospUnitChirurgieAA.addNode(healthCareUnitChirurgieAAA).addNode(healthCareUnitChirurgieAAB);

        initNode(healthCareUnitChirurgieABA, chirurgy, NodeLevel.healthCareUnit);
        initNode(healthCareUnitChirurgieABB, chirurgy, NodeLevel.healthCareUnit);

        initNode(hospUnitChirurgieAB, chirurgy, NodeLevel.hospitalUnit);

        hospUnitChirurgieAB.addNode(healthCareUnitChirurgieABA).addNode(healthCareUnitChirurgieABB);

        initNode(healthCareUnitChirurgieBAA, chirurgy, NodeLevel.healthCareUnit);
        initNode(healthCareUnitChirurgieBAB, chirurgy, NodeLevel.healthCareUnit);

        initNode(hospUnitChirurgieBA, chirurgy, NodeLevel.hospitalUnit);

        hospUnitChirurgieBA.addNode(healthCareUnitChirurgieBAA).addNode(healthCareUnitChirurgieBAB);

        initNode(healthCareUnitChirurgieBBA, chirurgy, NodeLevel.healthCareUnit);
        initNode(healthCareUnitChirurgieBBB, chirurgy, NodeLevel.healthCareUnit);

        initNode(hospUnitChirurgieBB, chirurgy, NodeLevel.hospitalUnit);

        hospUnitChirurgieBB.addNode(healthCareUnitChirurgieBBA).addNode(healthCareUnitChirurgieBBB);

        initNode(healthCareUnitUrgenceAAA, urgence, NodeLevel.healthCareUnit);

        initNode(hospUnitUrgenceAA, urgence, NodeLevel.hospitalUnit);

        hospUnitUrgenceAA.addNode(healthCareUnitUrgenceAAA);

        initNode(healthCareUnitUrgenceABA, urgence, NodeLevel.healthCareUnit);

        initNode(hospUnitUrgenceAB, urgence, NodeLevel.hospitalUnit);

        hospUnitUrgenceAB.addNode(healthCareUnitUrgenceABA);

        initNode(serviceChirurgieA, chirurgy, NodeLevel.service);
        serviceChirurgieA.addNode(hospUnitChirurgieAA).addNode(hospUnitChirurgieAB);
        initNode(serviceChirurgieB, chirurgy, NodeLevel.service);
        serviceChirurgieB.addNode(hospUnitChirurgieBA).addNode(hospUnitChirurgieBB);

        initNode(serviceUrgenceA, urgence, NodeLevel.service);
        serviceUrgenceA.addNode(hospUnitUrgenceAA).addNode(hospUnitUrgenceAB);

        initNode(poleChirurgie, chirurgy, NodeLevel.pole);
        poleChirurgie.addNode(serviceChirurgieA).addNode(serviceChirurgieB);

        initNode(poleUrgence, urgence, NodeLevel.pole);
        poleUrgence.addNode(serviceUrgenceA);

    }

    @Test
    public void testGetSubNodes() {
       initTestSet();
       String expectedSpeciality = chirurgy;
       int expectedLevel = poleChirurgie.getLevel().getHierarchyLevel()+1;
       for(Node node : poleChirurgie.getSubNodes()){
           assert(node.getSpeciality().equals(expectedSpeciality) && node.getLevel().getHierarchyLevel() == expectedLevel);
       }
    }

    @Test
    public void testGetSubNodesIsAnEmptyList() {
        NodeImpl node = new NodeImpl();
        List<Node> emptyList = new LinkedList<>();
        assert(node.getSubNodes().equals(emptyList) && node.getSubNodes().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNodeWithNodeNull() {
        NodeImpl node = new NodeImpl();
        node.addNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodeWithSameLevelNode(){
        initTestSet();
        poleChirurgie.addNode(poleUrgence);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodeWithAUpperLevelNode() {
        initTestSet();
        hospUnitChirurgieAA.addNode(poleChirurgie);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodeWithNodeWithTwoOrMoreUnderLevel() {
        initTestSet();
        poleChirurgie.addNode(hospUnitChirurgieAB);
    }

    @Test
    public void testAddNode() {
        NodeImpl nodeP1 =new NodeImpl();
        NodeImpl nodeS1 =new NodeImpl();
        NodeImpl nodeS2 =new NodeImpl();
        initNode(nodeP1, chirurgy, NodeLevel.pole);
        initNode(nodeS1, chirurgy, NodeLevel.service);
        initNode(nodeS2, chirurgy, NodeLevel.service);

        nodeP1.addNode(nodeS1).addNode(nodeS2);

        List<NodeImpl> expected = new ArrayList<>();
        expected.add(nodeS1);
        assert(!nodeP1.getSubNodes().equals(expected));
        expected.add(nodeS2);
        assert(nodeP1.getSubNodes().equals(expected));
    }
}
