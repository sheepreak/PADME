package application.hospital.domain;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HospitalTest{

    private Node poleUrgence = mock(Node.class);
    private Node poleChirurgie = mock(Node.class);

    private List<Node> serviceUrgenceList = new ArrayList<>();
    private Node serviceUrgenceA = mock(Node.class);

    private List<Node> serviceChirurgieList = new ArrayList<>();
    private Node serviceChirurgieA = mock(Node.class);
    private Node serviceChirurgieB = mock(Node.class);

    private List<Node> hospUnitUrgenceListA = new ArrayList<>();
    private Node hospUnitUrgenceAA = mock(Node.class);

    private List<Node> hospUnitChirurgieListA = new ArrayList<>();
    private Node hospUnitChirurgieAA = mock(Node.class);
    private Node hospUnitChirurgieAB = mock(Node.class);
    private List<Node> hospUnitChirurgieListB = new ArrayList<>();
    private Node hospUnitChirurgieBA = mock(Node.class);
    private Node hospUnitChirurgieBB = mock(Node.class);

    private List<Node> healthCareUnitUrgenceListAA = new ArrayList<>();
    private Node healthCareUnitUrgenceAAA = mock(Node.class);


    private List<Node> healthCareUnitChirurgieListAA = new ArrayList<>();
    private Node healthCareUnitChirurgieAAA = mock(Node.class);
    private Node healthCareUnitChirurgieAAB = mock(Node.class);
    private List<Node> healthCareUnitChirurgieListAB = new ArrayList<>();
    private Node healthCareUnitChirurgieABA = mock(Node.class);
    private Node healthCareUnitChirurgieABB = mock(Node.class);
    private List<Node> healthCareUnitChirurgieListBA = new ArrayList<>();
    private Node healthCareUnitChirurgieBAA = mock(Node.class);
    private Node healthCareUnitChirurgieBAB = mock(Node.class);
    private List<Node> healthCareUnitChirurgieListBB = new ArrayList<>();
    private Node healthCareUnitChirurgieBBA = mock(Node.class);
    private Node healthCareUnitChirurgieBBB = mock(Node.class);


    private Node simuleNode(Node node, List<Node> subNode, String speciality, boolean isNodePole, NodeLevel level){
        when(node.getSubNodes()).thenReturn(subNode);
        when(node.getSpeciality()).thenReturn(speciality);
        when(node.getLevel()).thenReturn(level);
        return node;
    }

    private void initDataTest(){
        String chirurgieLabel = "Chirurgie";
        String urgenceLabel = "Urgence";

        //chirurgie

        healthCareUnitChirurgieListAA
                .add(simuleNode(healthCareUnitChirurgieAAA, null, chirurgieLabel,false, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListAA
                .add(simuleNode(healthCareUnitChirurgieAAB, null, chirurgieLabel,false, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListAB
                .add(simuleNode(healthCareUnitChirurgieABA, null, chirurgieLabel,false, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListAB
                .add(simuleNode(healthCareUnitChirurgieABB, null, chirurgieLabel,false, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListBA
                .add(simuleNode(healthCareUnitChirurgieBAA, null, chirurgieLabel,false, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListBA
                .add(simuleNode(healthCareUnitChirurgieBAB, null, chirurgieLabel,false, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListBB
                .add(simuleNode(healthCareUnitChirurgieBBA, null, chirurgieLabel,false, NodeLevel.healthCareUnit));
        healthCareUnitChirurgieListBB
                .add(simuleNode(healthCareUnitChirurgieBBB, null, chirurgieLabel,false, NodeLevel.healthCareUnit));

        hospUnitChirurgieListA
                .add(simuleNode(hospUnitChirurgieAA, healthCareUnitChirurgieListAA, chirurgieLabel, false, NodeLevel.healthCareUnit));
        hospUnitChirurgieListA
                .add(simuleNode(hospUnitChirurgieAB, healthCareUnitChirurgieListAB, chirurgieLabel, false, NodeLevel.healthCareUnit));
        hospUnitChirurgieListB
                .add(simuleNode(hospUnitChirurgieBA, healthCareUnitChirurgieListBA, chirurgieLabel, false, NodeLevel.healthCareUnit));
        hospUnitChirurgieListB
                .add(simuleNode(hospUnitChirurgieBB, healthCareUnitChirurgieListBB, chirurgieLabel, false, NodeLevel.healthCareUnit));

        serviceChirurgieList
                .add(simuleNode(serviceChirurgieA, hospUnitChirurgieListA, chirurgieLabel, false, NodeLevel.hospitalUnit));
        serviceChirurgieList
                .add(simuleNode(serviceChirurgieB, hospUnitChirurgieListB, chirurgieLabel, false, NodeLevel.hospitalUnit));

        when(poleChirurgie.getSubNodes()).thenReturn(serviceChirurgieList);
        when(poleChirurgie.getSpeciality()).thenReturn(chirurgieLabel);
        when(poleChirurgie.getLevel()).thenReturn(NodeLevel.pole);

        //Urgence

        healthCareUnitChirurgieListAA
                .add(simuleNode(healthCareUnitChirurgieAAA, null, chirurgieLabel,false, NodeLevel.healthCareUnit));

        hospUnitChirurgieListA
                .add(simuleNode(hospUnitChirurgieAA, healthCareUnitChirurgieListAA, chirurgieLabel, false, NodeLevel.hospitalUnit));

        serviceChirurgieList
                .add(simuleNode(serviceChirurgieA, hospUnitChirurgieListA, chirurgieLabel, false, NodeLevel.service));

        when(poleUrgence.getSubNodes()).thenReturn(serviceUrgenceList);
        when(poleUrgence.getSpeciality()).thenReturn("Chirurgie");
        when(poleUrgence.getLevel()).thenReturn(NodeLevel.pole);
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
