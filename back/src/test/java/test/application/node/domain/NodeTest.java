package test.application.node.domain;

import application.node.NodeLevel;
import application.node.domain.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NodeTest {
    
    private Node poleUrgence = new Node();
    private Node poleChirurgie = new Node();

    private Node serviceUrgenceA = new Node();

    private Node serviceChirurgieA = new Node();
    private Node serviceChirurgieB = new Node();

    private Node hospUnitUrgenceAA = new Node();
    private Node hospUnitUrgenceAB = new Node();

    private Node hospUnitChirurgieAA = new Node();
    private Node hospUnitChirurgieAB = new Node();
    private Node hospUnitChirurgieBA = new Node();
    private Node hospUnitChirurgieBB = new Node();

    private Node healthCareUnitUrgenceAAA = new Node();
    private Node healthCareUnitUrgenceABA = new Node();

    private Node healthCareUnitChirurgieAAA = new Node();
    private Node healthCareUnitChirurgieAAB = new Node();
    private Node healthCareUnitChirurgieABA = new Node();
    private Node healthCareUnitChirurgieABB = new Node();
    private Node healthCareUnitChirurgieBAA = new Node();
    private Node healthCareUnitChirurgieBAB = new Node();
    private Node healthCareUnitChirurgieBBA = new Node();
    private Node healthCareUnitChirurgieBBB = new Node();

    private String chirurgy = "Chirurgy";
    private String urgence = "Urgence";

    private void initNode(Node node, String speciality, NodeLevel level){
        node.setLevel(level);
        node.setSpeciality(speciality);
    }

    private void initTestSet(){

        poleUrgence = new Node();
        poleChirurgie = new Node();

        serviceUrgenceA = new Node();

        serviceChirurgieA = new Node();
        serviceChirurgieB = new Node();

        hospUnitUrgenceAA = new Node();
        hospUnitUrgenceAB = new Node();

        hospUnitChirurgieAA = new Node();
        hospUnitChirurgieAB = new Node();
        hospUnitChirurgieBA = new Node();
        hospUnitChirurgieBB = new Node();

        healthCareUnitUrgenceAAA = new Node();
        healthCareUnitUrgenceABA = new Node();

        healthCareUnitChirurgieAAA = new Node();
        healthCareUnitChirurgieAAB = new Node();
        healthCareUnitChirurgieABA = new Node();
        healthCareUnitChirurgieABB = new Node();
        healthCareUnitChirurgieBAA = new Node();
        healthCareUnitChirurgieBAB = new Node();
        healthCareUnitChirurgieBBA = new Node();
        healthCareUnitChirurgieBBB = new Node();

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
        Node node = new Node();
        List<Node> emptyList = new LinkedList<>();
        assert(node.getSubNodes().equals(emptyList) && node.getSubNodes().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNodeWithNodeNull() {
        Node node = new Node();
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
        Node nodeP1 =new Node();
        Node nodeS1 =new Node();
        Node nodeS2 =new Node();
        initNode(nodeP1, chirurgy, NodeLevel.pole);
        initNode(nodeS1, chirurgy, NodeLevel.service);
        initNode(nodeS2, chirurgy, NodeLevel.service);

        nodeP1.addNode(nodeS1).addNode(nodeS2);

        List<Node> expected = new ArrayList<>();
        expected.add(nodeS1);
        assert(!nodeP1.getSubNodes().equals(expected));
        expected.add(nodeS2);
        assert(nodeP1.getSubNodes().equals(expected));
    }
}
