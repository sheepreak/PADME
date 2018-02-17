package test.application.hospital.domain;

import application.hospital.domain.Hospital;
import application.node.NodeLevel;
import application.node.domain.Node;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class HospitalIntegrationTest {

    private Node poleUrgence = new Node();
    private Node poleChirurgie = new Node();

    private List<Node> serviceUrgenceList = new ArrayList<>();
    private Node serviceUrgenceA = new Node();

    private List<Node> serviceChirurgieList = new ArrayList<>();
    private Node serviceChirurgieA = new Node();
    private Node serviceChirurgieB = new Node();

    private List<Node> hospUnitUrgenceListA = new ArrayList<>();
    private Node hospUnitUrgenceAA = new Node();

    private List<Node> hospUnitChirurgieListA = new ArrayList<>();
    private Node hospUnitChirurgieAA = new Node();
    private Node hospUnitChirurgieAB = new Node();
    private List<Node> hospUnitChirurgieListB = new ArrayList<>();
    private Node hospUnitChirurgieBA = new Node();
    private Node hospUnitChirurgieBB = new Node();

    private List<Node> healthCareUnitUrgenceListAA = new ArrayList<>();
    private Node healthCareUnitUrgenceAAA = new Node();


    private List<Node> healthCareUnitChirurgieListAA = new ArrayList<>();
    private Node healthCareUnitChirurgieAAA = new Node();
    private Node healthCareUnitChirurgieAAB = new Node();
    private List<Node> healthCareUnitChirurgieListAB = new ArrayList<>();
    private Node healthCareUnitChirurgieABA = new Node();
    private Node healthCareUnitChirurgieABB = new Node();
    private List<Node> healthCareUnitChirurgieListBA = new ArrayList<>();
    private Node healthCareUnitChirurgieBAA = new Node();
    private Node healthCareUnitChirurgieBAB = new Node();
    private List<Node> healthCareUnitChirurgieListBB = new ArrayList<>();
    private Node healthCareUnitChirurgieBBA = new Node();
    private Node healthCareUnitChirurgieBBB = new Node();


    private Node simuleNode(Node node, List<Node> subNode, String speciality, NodeLevel level){
        node.setSubNodes(subNode);
        node.setSpeciality(speciality);
        node.setLevel(level);
        return node;
    }

    private void initDataTest(){
        String chirurgieLabel = "Chirurgie";
        String urgenceLabel = "Urgence";

        //chirurgie

        healthCareUnitChirurgieListAA
                .add(simuleNode(healthCareUnitChirurgieAAA, null, chirurgieLabel, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListAA
                .add(simuleNode(healthCareUnitChirurgieAAB, null, chirurgieLabel, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListAB
                .add(simuleNode(healthCareUnitChirurgieABA, null, chirurgieLabel, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListAB
                .add(simuleNode(healthCareUnitChirurgieABB, null, chirurgieLabel, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListBA
                .add(simuleNode(healthCareUnitChirurgieBAA, null, chirurgieLabel, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListBA
                .add(simuleNode(healthCareUnitChirurgieBAB, null, chirurgieLabel, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListBB
                .add(simuleNode(healthCareUnitChirurgieBBA, null, chirurgieLabel, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListBB
                .add(simuleNode(healthCareUnitChirurgieBBB, null, chirurgieLabel, NodeLevel.healthCareUnit));

        hospUnitChirurgieListA
                .add(simuleNode(hospUnitChirurgieAA, healthCareUnitChirurgieListAA, chirurgieLabel,  NodeLevel.healthCareUnit));
        hospUnitChirurgieListA
                .add(simuleNode(hospUnitChirurgieAB, healthCareUnitChirurgieListAB, chirurgieLabel,  NodeLevel.healthCareUnit));
        hospUnitChirurgieListB
                .add(simuleNode(hospUnitChirurgieBA, healthCareUnitChirurgieListBA, chirurgieLabel,  NodeLevel.healthCareUnit));
        hospUnitChirurgieListB
                .add(simuleNode(hospUnitChirurgieBB, healthCareUnitChirurgieListBB, chirurgieLabel,  NodeLevel.healthCareUnit));

        serviceChirurgieList
                .add(simuleNode(serviceChirurgieA, hospUnitChirurgieListA, chirurgieLabel,  NodeLevel.hospitalUnit));
        serviceChirurgieList
                .add(simuleNode(serviceChirurgieB, hospUnitChirurgieListB, chirurgieLabel,  NodeLevel.hospitalUnit));

        poleChirurgie.setSubNodes(serviceChirurgieList);
        poleChirurgie.setSpeciality(chirurgieLabel);
        poleChirurgie.setLevel(NodeLevel.pole);

        //Urgence

        healthCareUnitChirurgieListAA
                .add(simuleNode(healthCareUnitChirurgieAAA, null, chirurgieLabel, NodeLevel.healthCareUnit));

        hospUnitChirurgieListA
                .add(simuleNode(hospUnitChirurgieAA, healthCareUnitChirurgieListAA, chirurgieLabel,  NodeLevel.hospitalUnit));

        serviceChirurgieList
                .add(simuleNode(serviceChirurgieA, hospUnitChirurgieListA, chirurgieLabel,  NodeLevel.service));

        poleUrgence.setSubNodes(serviceUrgenceList);
        poleUrgence.setSpeciality("Chirurgie");
        poleUrgence.setLevel(NodeLevel.pole);
    }

    @Test(expected = NullPointerException.class)
    public void testInitHierarchyWithPathNull() throws IOException {
        Hospital hospital = new Hospital("","","" );
        hospital.initHierarchy(null);
        assert(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitHierarchyWithDirectoryPathInsteadOfFilePath() throws IOException {
        Path path = Paths.get("..");
        Hospital hospital = new Hospital("","","" );
        hospital.initHierarchy(path);
        assert(false);
    }

    @Test(expected = IOException.class)
    public void testInitHierarchyWithWrongJsonFormatFilePath() throws IOException {
        Path path = Paths.get("src/test/ressources/wrongJsonFormat.json");
        Hospital hospital = new Hospital("","","" );
        hospital.initHierarchy(path);
        assert(false);
    }

    @Test(expected = JsonMappingException.class)
    public void testInitWithWrongJsonFormatFilePath() throws IOException {
        Path path = Paths.get("src/test/ressources/recettar.json");
        Hospital hospital = new Hospital("","","" );
        hospital.initHierarchy(path);
        assert(false);
    }

    @Test
    public void testInitHierarchy() throws IOException {
        Path path = Paths.get("src/test/ressources/hospitalALBERT-CHENEVIER.json");
        Hospital hospital = new Hospital("","","" );
        hospital.initHierarchy(path);
        assert(hospital.getHierarchy().get(0).getLevel().equals(NodeLevel.pole) && hospital.getHierarchy().get(0).getSpeciality().equals("hospitalALBERT-CHEVENIER_Spe1"));
    }

    @Test
    public void testInitHierarchyTwice() throws IOException {
        Path path = Paths.get("src/test/ressources/hospitalALBERT-CHENEVIER.json");
        Path path2 = Paths.get("src/test/ressources/hospitalAMBROISE-PARÉ.json");
        Hospital hospital = new Hospital("","","" );
        Hospital hospital2 = new Hospital("","","" );
        hospital2.initHierarchy(path);
        hospital.initHierarchy(path);
        hospital.initHierarchy(path2);
        System.out.println(hospital.getHierarchy().toString());
        System.out.println(hospital2.getHierarchy().toString());
        assert(hospital.getHierarchy().toString().equals(hospital2.getHierarchy().toString()));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHospitalWithNullName(){
        Hospital hospital = new Hospital(null,"","" );
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHospitalWithNullAddress(){
        Hospital hospital = new Hospital("","",null );
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHospitalWithNullCountry(){
        Hospital hospital = new Hospital("",null,"" );
    }

    @Test
    public void testCreateHospital(){
        String name = "Hopital ALBERT-CHENEVIER";
        String address = "40 rue du Mesly, 94000 Créteil";
        String country = "France";
        Hospital hospital = new Hospital(name,country,address);
        assertTrue(hospital.getName().equals(name) && hospital.getAddress().equals(address)
                && hospital.getCountry().equals(country));
    }

    @Test
    public void testHospitalGetHierarchy(){
        Hospital hospital = new Hospital("","","" );
        List<Node> nodeExpected = new ArrayList<>();
        initDataTest();
        hospital.addNodePole(poleUrgence);
        hospital.addNodePole(poleChirurgie);
        nodeExpected.add(poleUrgence);
        nodeExpected.add(poleChirurgie);
        assert(hospital.getHierarchy().equals(nodeExpected));
    }
    @Test
    public void testHospitalGetHierarchyIsAnEmptyListByDefault(){
        Hospital hospital = new Hospital("","","" );
        assertTrue(hospital.getHierarchy().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNodePoleWithNodeNull(){
        Hospital hospital = new Hospital("","","" );
        hospital.addNodePole(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodePoleWithNodeService(){
        Hospital hospital = new Hospital("","","" );
        initDataTest();
        hospital.addNodePole(serviceChirurgieA);
        assert(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodePoleWithNodeHospitalUnit(){
        Hospital hospital = new Hospital("","","" );
        initDataTest();
        hospital.addNodePole(hospUnitChirurgieAA);
        assert(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodePoleWithNodeHealthCareUnit(){
        Hospital hospital = new Hospital("","","" );
        initDataTest();
        hospital.addNodePole(healthCareUnitChirurgieAAA);
        assert(false);
    }

    @Test
    public void testAddNodePole(){
        Hospital hospital = new Hospital("","","" );
        initDataTest();
        List<Node> nodeExpected = new ArrayList<>();
        nodeExpected.add(poleChirurgie);
        hospital.addNodePole(poleChirurgie);
        assert(hospital.getHierarchy().equals(nodeExpected));
    }

    @Test
    public void testGetPosition(){
        Hospital hospital = new Hospital("","","" );
        initDataTest();
        hospital.addNodePole(poleChirurgie);
        assert(hospital.getPosition(healthCareUnitChirurgieAAA).equals(healthCareUnitChirurgieAAA));
    }

    @Test
    public void testGetPositionWithOtherNode(){
        Hospital hospital = new Hospital("","","" );
        initDataTest();
        hospital.addNodePole(poleChirurgie);
        assert(null == hospital.getPosition(healthCareUnitUrgenceAAA));
    }
}
