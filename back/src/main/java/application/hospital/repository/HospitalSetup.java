package application.hospital.repository;

import application.adminfile.domain.AdminFile;
import application.adminfile.repository.AdminFileRepository;
import application.examen.domain.Examen;
import application.examen.repository.ExamenRepository;
import application.hospital.domain.Hospital;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.medicalinfo.domain.MedicalInfo;
import application.medicalinfo.repository.MedicalInfoRepository;
import application.node.NodeLevel;
import application.node.domain.Node;
import application.node.repository.NodeRepository;
import application.observation.domain.Observation;
import application.observation.repository.ObservationRepository;
import application.patient.domain.Patient;
import application.patient.repository.PatientRepository;
import application.posology.domain.Posology;
import application.posology.repository.PosologyRepository;
import application.prescription.domain.Prescription;
import application.prescription.repository.PrescriptionRepository;
import application.staff.Status;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;
import utils.Address;
import utils.InseeRef;
import utils.Parse;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Startup
@Singleton
public class HospitalSetup {

    @EJB
    private PatientRepository patientRepository;
    @EJB
    private ObservationRepository observationRepository;
    @EJB
    private PrescriptionRepository prescriptionRepository;
    @EJB
    private ExamenRepository examenRepository;
    @EJB
    private MedicalInfoRepository medicalInfoRepository;
    @EJB
    private MedicalFileRepository medicalFileRepository;
    @EJB
    private AdminFileRepository adminFileRepository;
    @EJB
    private HospitalRepository hospitalRepository;
    @EJB
    private NodeRepository nodeRepository;
    @EJB
    private StaffRepository staffRepository;
    @EJB
    private PosologyRepository posologyRepository;

    private List<Patient> list = new ArrayList<>();
    private Random rand = new Random();
    private int nbAutoGeneratePatient = 100;
    private List<String> jobsM = new ArrayList<>();
    private List<String> jobsF = new ArrayList<>();
    private List<String> jobs = new ArrayList<>();
    private List<Node> services = new ArrayList<>();
    private List<String> dataPrenom = new ArrayList<>();
    private HashMap<String, Boolean> prenoms = new HashMap<>();
    private List<String> dataInseeRefs = new ArrayList<>();
    private List<InseeRef> listInseeRefs = new ArrayList<>();
    private List<String> dataAddress = new ArrayList<>();
    private List<Address> addressSamples = new ArrayList<>();
    private List<Address> addressSamplesOnParis = new ArrayList<>();
    private List<String> firstNames = new ArrayList<>();
    private List<String> emailBox = new ArrayList<>();
    private List<Staff> staffs = new ArrayList<>();

    @PostConstruct
    private void initialize() {
//        try {
        createData();
//        } catch (Exception e) {
//            System.err.println(e);
//        }
    }

    private void initDataParsed() {
        try {
            dataPrenom = Parse.parseFileToString(Paths.get(this.getClass().getClassLoader().getResource("dataForSetup/Prenoms.csv").getPath()));
            prenoms = Parse.parseFirstname(dataPrenom);
            dataInseeRefs = Parse.parseFileToString(Paths.get(this.getClass().getClassLoader().getResource("dataForSetup/laposte_hexasmal.csv").getPath()));
            listInseeRefs = Parse.parseInseeRef(dataInseeRefs);
            dataAddress = Parse.parseFileToString(Paths.get(this.getClass().getClassLoader().getResource("dataForSetup/les_bureaux_de_poste_et_agences_postales_en_idf.csv").getPath()));
            addressSamples = Parse.parseSampleAddress(dataAddress, listInseeRefs);
            firstNames = new ArrayList<>(prenoms.keySet());
            addressSamplesOnParis = addressSamples.stream().filter(address -> address.getCity().toLowerCase().contains("paris")).collect(Collectors.toList());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void initRandomBoxMail() {
        emailBox.add("@yahoo.fr");
        emailBox.add("@gmail.com");
        emailBox.add("@free.fr");
        emailBox.add("@hotmail.fr");
        emailBox.add("@hotmail.com");
        emailBox.add("@live.fr");
        emailBox.add("@sfr.fr");
        emailBox.add("@orange.fr");
        emailBox.add("@yopmail.com");

    }

    private void initJobsList() {
        jobs.add("Coursier");
        jobs.add("Vendeur Immobilier");
        jobs.add("Gestionnaire de fonds");
        jobs.add("Sans-emploi");
        jobs.add("Peintre");
        jobs.add("Vendeur");
        jobs.add("Courtier");
        jobs.add("Professeur");
        jobs.add("Architecte");
        jobs.add("Gardien");
        jobs.add("Vigile");
        jobs.add("Responsable des ressources humaines");
    }

    private void initAllGenerateList() {
        initDataParsed();
        initJobsList();
        initRandomBoxMail();
    }

    private void createData() {
        initAllGenerateList();

        //Hospital and nodes
        Hospital hospital = new Hospital("Hopital Rothschild", "France", "5 Rue Santerre 75012 Paris");

        Node nodePole0 = new Node("Pediatrics", NodeLevel.pole);
        Node nodeService01 = new Node("Service d’endocrinologie", NodeLevel.service);
        Node nodeService02 = new Node("Service de gastro-entérologie, nutrition, pneumologie et mucoviscidose", NodeLevel.service);
        Node nodeService03 = new Node("Service d’hématologie", NodeLevel.service);
        Node nodeService04 = new Node("Service de pneumologie", NodeLevel.service);
        services.add(nodeService01);
        services.add(nodeService02);
        services.add(nodeService03);
        services.add(nodeService04);
        Node nodeHU011 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU012 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU013 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU014 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU021 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU022 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU023 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU024 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU031 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU032 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU033 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU034 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU041 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU042 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU043 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU044 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHCU0111 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0121 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0131 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0141 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0211 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0221 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0231 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0241 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0311 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0321 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0331 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0341 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0411 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0421 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0431 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0441 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU0112 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0122 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0132 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0142 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0212 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0222 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0232 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0242 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0312 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0322 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0332 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0342 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0412 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0422 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0432 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU0442 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        nodePole0.addNode(nodeService01).addNode(nodeService02).addNode(nodeService03).addNode(nodeService04);
        nodeService01.addNode(nodeHU011).addNode(nodeHU012).addNode(nodeHU013).addNode(nodeHU014);
        nodeService02.addNode(nodeHU021).addNode(nodeHU022).addNode(nodeHU023).addNode(nodeHU024);
        nodeService03.addNode(nodeHU031).addNode(nodeHU032).addNode(nodeHU033).addNode(nodeHU034);
        nodeService04.addNode(nodeHU041).addNode(nodeHU042).addNode(nodeHU043).addNode(nodeHU044);
        nodeHU011.addNode(nodeHCU0111).addNode(nodeHCU0112);
        nodeHU012.addNode(nodeHCU0121).addNode(nodeHCU0122);
        nodeHU013.addNode(nodeHCU0131).addNode(nodeHCU0132);
        nodeHU014.addNode(nodeHCU0141).addNode(nodeHCU0142);
        nodeHU021.addNode(nodeHCU0211).addNode(nodeHCU0212);
        nodeHU022.addNode(nodeHCU0221).addNode(nodeHCU0222);
        nodeHU023.addNode(nodeHCU0231).addNode(nodeHCU0232);
        nodeHU024.addNode(nodeHCU0241).addNode(nodeHCU0242);
        nodeHU031.addNode(nodeHCU0311).addNode(nodeHCU0312);
        nodeHU032.addNode(nodeHCU0321).addNode(nodeHCU0322);
        nodeHU033.addNode(nodeHCU0331).addNode(nodeHCU0332);
        nodeHU034.addNode(nodeHCU0341).addNode(nodeHCU0342);
        nodeHU041.addNode(nodeHCU0411).addNode(nodeHCU0412);
        nodeHU042.addNode(nodeHCU0421).addNode(nodeHCU0422);
        nodeHU043.addNode(nodeHCU0431).addNode(nodeHCU0432);
        nodeHU044.addNode(nodeHCU0441).addNode(nodeHCU0442);
        hospital.addNodePole(nodePole0);

        //pole emergency
        Node nodePoleEmergency = new Node("Pediatrics", NodeLevel.pole);
        Node nodeServiceEmergency = new Node("Service d’endocrinologie", NodeLevel.service);

        services.add(nodeService01);
        services.add(nodeService02);
        services.add(nodeService03);
        services.add(nodeService04);
        Node nodeHUEmergency11 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHUEmergency12 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHUEmergency13 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHUEmergency14 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);

        Node nodeHCUEmergency111 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCUEmergency121 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCUEmergency131 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCUEmergency141 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);

        nodePoleEmergency.addNode(nodeServiceEmergency);
        nodeService01.addNode(nodeHUEmergency11).addNode(nodeHUEmergency12).addNode(nodeHUEmergency13).addNode(nodeHUEmergency14);
        nodeHUEmergency11.addNode(nodeHCUEmergency111);
        nodeHUEmergency12.addNode(nodeHCUEmergency121);
        nodeHUEmergency13.addNode(nodeHCUEmergency131);
        nodeHUEmergency14.addNode(nodeHCUEmergency141);

        hospital.addNodePole(nodePoleEmergency);


        //other pole
        Node nodePole1 = new Node("Chirurgie", NodeLevel.pole);
        Node nodePole2 = new Node("Médecine générale", NodeLevel.pole);
        Node nodePole3 = new Node("Radiologie", NodeLevel.pole);
        Node nodeService11 = new Node("Chirurgie cardio-thoracique", NodeLevel.service);
        Node nodeService12 = new Node("Neurochirurgie", NodeLevel.service);
        Node nodeService13 = new Node("Chirurgie orthopédique et traumatologique", NodeLevel.service);
        Node nodeService14 = new Node("Chirurgie pédiatrique", NodeLevel.service);
        Node nodeService21 = new Node("Service de consultation", NodeLevel.service);
        Node nodeService22 = new Node("Service de prescription", NodeLevel.service);
        Node nodeService23 = new Node("Service de rééducation", NodeLevel.service);
        Node nodeService31 = new Node("Radiologie cardio-thoracique", NodeLevel.service);
        Node nodeService32 = new Node("Radiologie crânienne", NodeLevel.service);
        Node nodeService33 = new Node("Radiologie des membres", NodeLevel.service);
        services.add(nodeService11);
        services.add(nodeService12);
        services.add(nodeService13);
        services.add(nodeService14);
        services.add(nodeService21);
        services.add(nodeService22);
        services.add(nodeService23);
        services.add(nodeService31);
        services.add(nodeService32);
        services.add(nodeService33);
        Node nodeHU111 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU112 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU113 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU114 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU121 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU122 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU123 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU124 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU131 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU132 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU133 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU134 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU141 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU142 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU143 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU144 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU211 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU212 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU213 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU214 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU221 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU222 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU223 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU224 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU231 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU232 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU233 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU234 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU311 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU312 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU313 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU314 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU321 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU322 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU323 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU324 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);
        Node nodeHU331 = new Node("Unité hospitalière 1", NodeLevel.hospitalUnit);
        Node nodeHU332 = new Node("Unité hospitalière 2", NodeLevel.hospitalUnit);
        Node nodeHU333 = new Node("Unité hospitalière 3", NodeLevel.hospitalUnit);
        Node nodeHU334 = new Node("Unité hospitalière 4", NodeLevel.hospitalUnit);

        Node nodeHCU1111 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1121 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1131 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1141 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1211 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1221 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1231 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1241 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1311 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1321 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1331 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1341 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1411 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1421 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1431 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU1441 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2111 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2121 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2131 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2141 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2211 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2221 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2231 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2241 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2311 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2321 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2331 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU2341 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3111 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3121 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3131 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3141 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3211 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3221 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3231 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3241 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3311 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3321 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3331 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);
        Node nodeHCU3341 = new Node("Unité de soin 1", NodeLevel.healthCareUnit);

        Node nodeHCU1112 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1122 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1132 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1142 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1212 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1222 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1232 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1242 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1312 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1322 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1332 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1342 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1412 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1422 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1432 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU1442 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2112 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2122 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2132 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2142 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2212 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2222 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2232 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2242 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2312 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2322 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2332 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU2342 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3112 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3122 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3132 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3142 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3212 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3222 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3232 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3242 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3312 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3322 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3332 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);
        Node nodeHCU3342 = new Node("Unité de soin 2", NodeLevel.healthCareUnit);

        nodePole1.addNode(nodeService11).addNode(nodeService12).addNode(nodeService13).addNode(nodeService14);
        nodePole2.addNode(nodeService21).addNode(nodeService22).addNode(nodeService23);
        nodePole3.addNode(nodeService31).addNode(nodeService32).addNode(nodeService33);

        nodeService11.addNode(nodeHU111).addNode(nodeHU112).addNode(nodeHU113).addNode(nodeHU114);
        nodeService12.addNode(nodeHU121).addNode(nodeHU122).addNode(nodeHU123).addNode(nodeHU124);
        nodeService13.addNode(nodeHU131).addNode(nodeHU132).addNode(nodeHU133).addNode(nodeHU134);
        nodeService14.addNode(nodeHU141).addNode(nodeHU142).addNode(nodeHU143).addNode(nodeHU144);
        nodeService21.addNode(nodeHU211).addNode(nodeHU212).addNode(nodeHU213).addNode(nodeHU214);
        nodeService22.addNode(nodeHU221).addNode(nodeHU222).addNode(nodeHU223).addNode(nodeHU224);
        nodeService23.addNode(nodeHU231).addNode(nodeHU232).addNode(nodeHU233).addNode(nodeHU234);
        nodeService31.addNode(nodeHU311).addNode(nodeHU312).addNode(nodeHU313).addNode(nodeHU314);
        nodeService32.addNode(nodeHU321).addNode(nodeHU322).addNode(nodeHU323).addNode(nodeHU324);
        nodeService33.addNode(nodeHU331).addNode(nodeHU332).addNode(nodeHU333).addNode(nodeHU334);

        nodeHU111.addNode(nodeHCU1111).addNode(nodeHCU1112);
        nodeHU112.addNode(nodeHCU1121).addNode(nodeHCU1122);
        nodeHU113.addNode(nodeHCU1131).addNode(nodeHCU1132);
        nodeHU114.addNode(nodeHCU1141).addNode(nodeHCU1142);
        nodeHU121.addNode(nodeHCU1211).addNode(nodeHCU1212);
        nodeHU122.addNode(nodeHCU1221).addNode(nodeHCU1222);
        nodeHU123.addNode(nodeHCU1231).addNode(nodeHCU1232);
        nodeHU124.addNode(nodeHCU1241).addNode(nodeHCU1242);
        nodeHU131.addNode(nodeHCU1311).addNode(nodeHCU1312);
        nodeHU132.addNode(nodeHCU1321).addNode(nodeHCU1322);
        nodeHU133.addNode(nodeHCU1331).addNode(nodeHCU1332);
        nodeHU134.addNode(nodeHCU1341).addNode(nodeHCU1342);
        nodeHU141.addNode(nodeHCU1411).addNode(nodeHCU1412);
        nodeHU142.addNode(nodeHCU1421).addNode(nodeHCU1422);
        nodeHU143.addNode(nodeHCU1431).addNode(nodeHCU1432);
        nodeHU144.addNode(nodeHCU1441).addNode(nodeHCU1442);
        nodeHU211.addNode(nodeHCU2111).addNode(nodeHCU2112);
        nodeHU212.addNode(nodeHCU2121).addNode(nodeHCU2122);
        nodeHU213.addNode(nodeHCU2131).addNode(nodeHCU2132);
        nodeHU214.addNode(nodeHCU2141).addNode(nodeHCU2142);
        nodeHU221.addNode(nodeHCU2211).addNode(nodeHCU2212);
        nodeHU222.addNode(nodeHCU2221).addNode(nodeHCU2222);
        nodeHU223.addNode(nodeHCU2231).addNode(nodeHCU2232);
        nodeHU224.addNode(nodeHCU2241).addNode(nodeHCU2242);
        nodeHU231.addNode(nodeHCU2311).addNode(nodeHCU2312);
        nodeHU232.addNode(nodeHCU2321).addNode(nodeHCU2322);
        nodeHU233.addNode(nodeHCU2331).addNode(nodeHCU2332);
        nodeHU234.addNode(nodeHCU2341).addNode(nodeHCU2342);
        nodeHU311.addNode(nodeHCU3111).addNode(nodeHCU3112);
        nodeHU312.addNode(nodeHCU3121).addNode(nodeHCU3122);
        nodeHU313.addNode(nodeHCU3131).addNode(nodeHCU3132);
        nodeHU314.addNode(nodeHCU3141).addNode(nodeHCU3142);
        nodeHU321.addNode(nodeHCU3211).addNode(nodeHCU3212);
        nodeHU322.addNode(nodeHCU3221).addNode(nodeHCU3222);
        nodeHU323.addNode(nodeHCU3231).addNode(nodeHCU3232);
        nodeHU324.addNode(nodeHCU3241).addNode(nodeHCU3242);
        nodeHU331.addNode(nodeHCU3311).addNode(nodeHCU3312);
        nodeHU332.addNode(nodeHCU3321).addNode(nodeHCU3322);
        nodeHU333.addNode(nodeHCU3331).addNode(nodeHCU3332);
        nodeHU334.addNode(nodeHCU3341).addNode(nodeHCU3342);

        hospital.addNodePole(nodePole1);
        hospital.addNodePole(nodePole2);
        hospital.addNodePole(nodePole3);

        hospitalRepository.save(hospital);
        //user accounts for testing
        Staff staff1 = new Staff("ameline@aphp.fr", "ameline", "Moreau", "Ameline", "0606060606", "123 fake street", Status.ADMIN);
        Staff staff2 = new Staff("charles@aphp.fr", "charles", "Da Silva Costa", "Charles", "0606060607", "125 fake street", Status.DOCTOR);
        Staff staff3 = new Staff("jeanluc@aphp.fr", "jeanluc", "Fernandes", "Jean-Luc", "0606060608", "127 fake street", Status.SECRETARY);
        staff1.setNode(nodePole1);
        staff2.setNode(nodePole1);
        staff3.setNode(nodePole1);
        staffRepository.save(staff1);
        staffs.add(staff1);
        staffRepository.save(staff2);
        staffs.add(staff2);
        staffRepository.save(staff3);
        staffs.add(staff3);

        //dataset accounts for coherent data
        Staff doc1 = new Staff("mgrey@aphp.fr", "mgrey", "Grey", "Meredith", "0606060608", "15 rue Chapatte 75001 Paris", Status.DOCTOR);
        doc1.setNode(nodePole2);
        staffRepository.save(doc1);
        staffs.add(doc1);

        Staff doc0 = new Staff("milgrey@aphp.fr", "milgrey", "Ilgrey", "Patrick", "0606060108", "17 rue Chapatte 75001 Paris", Status.DOCTOR);
        doc0.setNode(nodeService32);
        staffRepository.save(doc0);
        staffs.add(doc0);

        Staff doc2 = new Staff("cyang@aphp.fr", "cyang", "Yang", "Cristina", "0606060609", "25 allée de la brûme 75012 Paris", Status.DOCTOR);
        doc2.setNode(nodeService11);
        staffRepository.save(doc2);
        staffs.add(doc2);

        Staff doc3 = new Staff("dshep@aphp.fr", "dshep", "Shepherd", "Derek", "0606060610", "15 rue Chapatte 75001 Paris", Status.DOCTOR);
        doc3.setNode(nodeService12);
        staffRepository.save(doc3);
        staffs.add(doc3);

        Staff doc4 = new Staff("akepn@aphp.fr", "akepn", "Kepner", "April", "0606060611", "32 rue de la mairie 77450 Esbly", Status.DOCTOR);
        doc4.setNode(nodeService13);
        staffRepository.save(doc4);
        staffs.add(doc4);

        Staff doc5 = new Staff("arobb@aphp.fr", "arobb", "Robbins", "Arizona", "0606060612", "47 rue Pasteur 77700 Coupvray", Status.DOCTOR);
        doc5.setNode(nodeService14);
        staffRepository.save(doc5);
        staffs.add(doc5);

        Staff doc6 = new Staff("mbail@aphp.fr", "mbail", "Bailey", "Miranda", "0606060613", "25 avenue de la République 77340 Pontault-Combault", Status.DOCTOR);
        doc6.setNode(nodePole1);
        staffRepository.save(doc6);
        staffs.add(doc6);

        Staff doc7 = new Staff("ohunt@aphp.fr", "ohunt", "Hunt", "Owen", "0606060615", "64 allée des mille et une nuits 77184 Emerainville", Status.DOCTOR);
        doc7.setNode(nodePole3);
        staffRepository.save(doc7);
        staffs.add(doc7);

        Staff doc8 = new Staff("ghous@aphp.fr", "ghous", "House", "Gregory", "0606060616", "18 allée Montesquieu 93190 Livry-Gargan", Status.DOCTOR);
        doc8.setNode(nodeService21);
        staffRepository.save(doc8);
        staffs.add(doc8);

        Staff nurse1 = new Staff("obaker@aphp.fr", "obaker", "Baker", "Oscar", "0606060617", "65 avenue Jean Jaurès 93390 Clichy-sous-bois", Status.NURSE);
        nurse1.setNode(nodeHU111);
        staffRepository.save(nurse1);
        staffs.add(nurse1);

        Staff nurse2 = new Staff("obake2@aphp.fr", "obake2", "Baker", "Olivia", "0606060618", "65 avenue Jean Jaurès 93390 Clichy-sous-bois", Status.NURSE);
        nurse2.setNode(nodeHU112);
        staffRepository.save(nurse2);
        staffs.add(nurse2);

        Staff nurse3 = new Staff("mvalen@aphp.fr", "mvalen", "Valentine", "Mai", "0606060619", "16 allée Monge 93320 Les Pavillons-sous-bois", Status.NURSE);
        nurse3.setNode(nodeHU113);
        staffRepository.save(nurse3);
        staffs.add(nurse3);

        generateRandomStaff(2, Status.NURSE, nodeHCU1112);
        generateRandomStaff(2, Status.NURSE, nodeHCU1111);
        generateRandomStaff(2, Status.NURSE, nodeHCU1122);
        generateRandomStaff(2, Status.NURSE, nodeHCU1121);
        generateRandomStaff(2, Status.NURSE, nodeHCU1132);
        generateRandomStaff(2, Status.NURSE, nodeHCU1131);
        generateRandomStaff(2, Status.NURSE, nodeHCU1142);
        generateRandomStaff(2, Status.NURSE, nodeHCU1141);
        generateRandomStaff(2, Status.NURSE, nodeHCU1212);
        generateRandomStaff(2, Status.NURSE, nodeHCU1211);
        generateRandomStaff(2, Status.NURSE, nodeHCU1222);
        generateRandomStaff(2, Status.NURSE, nodeHCU1221);
        generateRandomStaff(2, Status.NURSE, nodeHCU1232);
        generateRandomStaff(2, Status.NURSE, nodeHCU1231);
        generateRandomStaff(2, Status.NURSE, nodeHCU1242);
        generateRandomStaff(2, Status.NURSE, nodeHCU1241);
        generateRandomStaff(2, Status.NURSE, nodeHCU1312);
        generateRandomStaff(2, Status.NURSE, nodeHCU1311);
        generateRandomStaff(2, Status.NURSE, nodeHCU1322);
        generateRandomStaff(2, Status.NURSE, nodeHCU1321);
        generateRandomStaff(2, Status.NURSE, nodeHCU1332);
        generateRandomStaff(2, Status.NURSE, nodeHCU1331);
        generateRandomStaff(2, Status.NURSE, nodeHCU1342);
        generateRandomStaff(2, Status.NURSE, nodeHCU1341);
        generateRandomStaff(2, Status.NURSE, nodeHCU1412);
        generateRandomStaff(2, Status.NURSE, nodeHCU1411);
        generateRandomStaff(2, Status.NURSE, nodeHCU1422);
        generateRandomStaff(2, Status.NURSE, nodeHCU1421);
        generateRandomStaff(2, Status.NURSE, nodeHCU1432);
        generateRandomStaff(2, Status.NURSE, nodeHCU1431);
        generateRandomStaff(2, Status.NURSE, nodeHCU1442);
        generateRandomStaff(2, Status.NURSE, nodeHCU1441);
        generateRandomStaff(2, Status.NURSE, nodeHCU2112);
        generateRandomStaff(2, Status.NURSE, nodeHCU2111);
        generateRandomStaff(2, Status.NURSE, nodeHCU2122);
        generateRandomStaff(2, Status.NURSE, nodeHCU2121);
        generateRandomStaff(2, Status.NURSE, nodeHCU2132);
        generateRandomStaff(2, Status.NURSE, nodeHCU2131);
        generateRandomStaff(2, Status.NURSE, nodeHCU2142);
        generateRandomStaff(2, Status.NURSE, nodeHCU2141);
        generateRandomStaff(2, Status.NURSE, nodeHCU2212);
        generateRandomStaff(2, Status.NURSE, nodeHCU2211);
        generateRandomStaff(2, Status.NURSE, nodeHCU2222);
        generateRandomStaff(2, Status.NURSE, nodeHCU2221);
        generateRandomStaff(2, Status.NURSE, nodeHCU2232);
        generateRandomStaff(2, Status.NURSE, nodeHCU2231);
        generateRandomStaff(2, Status.NURSE, nodeHCU2242);
        generateRandomStaff(2, Status.NURSE, nodeHCU2241);
        generateRandomStaff(2, Status.NURSE, nodeHCU2312);
        generateRandomStaff(2, Status.NURSE, nodeHCU2311);
        generateRandomStaff(2, Status.NURSE, nodeHCU2322);
        generateRandomStaff(2, Status.NURSE, nodeHCU2321);
        generateRandomStaff(2, Status.NURSE, nodeHCU2332);
        generateRandomStaff(2, Status.NURSE, nodeHCU2331);
        generateRandomStaff(2, Status.NURSE, nodeHCU2342);
        generateRandomStaff(2, Status.NURSE, nodeHCU2341);
        generateRandomStaff(2, Status.NURSE, nodeHCU3112);
        generateRandomStaff(2, Status.NURSE, nodeHCU3111);
        generateRandomStaff(2, Status.NURSE, nodeHCU3122);
        generateRandomStaff(2, Status.NURSE, nodeHCU3121);
        generateRandomStaff(2, Status.NURSE, nodeHCU3132);
        generateRandomStaff(2, Status.NURSE, nodeHCU3131);
        generateRandomStaff(2, Status.NURSE, nodeHCU3142);
        generateRandomStaff(2, Status.NURSE, nodeHCU3141);
        generateRandomStaff(2, Status.NURSE, nodeHCU3212);
        generateRandomStaff(2, Status.NURSE, nodeHCU3211);
        generateRandomStaff(2, Status.NURSE, nodeHCU3222);
        generateRandomStaff(2, Status.NURSE, nodeHCU3221);
        generateRandomStaff(2, Status.NURSE, nodeHCU3232);
        generateRandomStaff(2, Status.NURSE, nodeHCU3231);
        generateRandomStaff(2, Status.NURSE, nodeHCU3242);
        generateRandomStaff(2, Status.NURSE, nodeHCU3241);
        generateRandomStaff(2, Status.NURSE, nodeHCU3312);
        generateRandomStaff(2, Status.NURSE, nodeHCU3311);
        generateRandomStaff(2, Status.NURSE, nodeHCU3322);
        generateRandomStaff(2, Status.NURSE, nodeHCU3321);
        generateRandomStaff(2, Status.NURSE, nodeHCU3332);
        generateRandomStaff(2, Status.NURSE, nodeHCU3331);
        generateRandomStaff(2, Status.NURSE, nodeHCU3342);
        generateRandomStaff(2, Status.NURSE, nodeHCU3341);

        //pole pediatrics - staffs

        generateRandomStaff(2, Status.NURSE, nodeHCU0112);
        generateRandomStaff(2, Status.NURSE, nodeHCU0111);
        generateRandomStaff(2, Status.NURSE, nodeHCU0122);
        generateRandomStaff(2, Status.NURSE, nodeHCU0121);
        generateRandomStaff(2, Status.NURSE, nodeHCU0132);
        generateRandomStaff(2, Status.NURSE, nodeHCU0131);
        generateRandomStaff(2, Status.NURSE, nodeHCU0142);
        generateRandomStaff(2, Status.NURSE, nodeHCU0141);
        generateRandomStaff(2, Status.NURSE, nodeHCU0212);
        generateRandomStaff(2, Status.NURSE, nodeHCU0211);
        generateRandomStaff(2, Status.NURSE, nodeHCU0222);
        generateRandomStaff(2, Status.NURSE, nodeHCU0221);
        generateRandomStaff(2, Status.NURSE, nodeHCU0232);
        generateRandomStaff(2, Status.NURSE, nodeHCU0231);
        generateRandomStaff(2, Status.NURSE, nodeHCU0242);
        generateRandomStaff(2, Status.NURSE, nodeHCU0241);
        generateRandomStaff(2, Status.NURSE, nodeHCU0312);
        generateRandomStaff(2, Status.NURSE, nodeHCU0311);
        generateRandomStaff(2, Status.NURSE, nodeHCU0322);
        generateRandomStaff(2, Status.NURSE, nodeHCU0321);
        generateRandomStaff(2, Status.NURSE, nodeHCU0332);
        generateRandomStaff(2, Status.NURSE, nodeHCU0331);
        generateRandomStaff(2, Status.NURSE, nodeHCU0342);
        generateRandomStaff(2, Status.NURSE, nodeHCU0341);
        generateRandomStaff(2, Status.NURSE, nodeHCU0412);
        generateRandomStaff(2, Status.NURSE, nodeHCU0411);
        generateRandomStaff(2, Status.NURSE, nodeHCU0422);
        generateRandomStaff(2, Status.NURSE, nodeHCU0421);
        generateRandomStaff(2, Status.NURSE, nodeHCU0432);
        generateRandomStaff(2, Status.NURSE, nodeHCU0431);
        generateRandomStaff(2, Status.NURSE, nodeHCU0442);
        generateRandomStaff(2, Status.NURSE, nodeHCU0441);

        generateRandomStaff(2, Status.NURSE, nodeHU011);
        generateRandomStaff(2, Status.NURSE, nodeHU012);
        generateRandomStaff(2, Status.NURSE, nodeHU013);
        generateRandomStaff(2, Status.NURSE, nodeHU014);
        generateRandomStaff(2, Status.NURSE, nodeHU021);
        generateRandomStaff(2, Status.NURSE, nodeHU022);
        generateRandomStaff(2, Status.NURSE, nodeHU023);
        generateRandomStaff(2, Status.NURSE, nodeHU024);
        generateRandomStaff(2, Status.NURSE, nodeHU031);
        generateRandomStaff(2, Status.NURSE, nodeHU032);
        generateRandomStaff(2, Status.NURSE, nodeHU033);
        generateRandomStaff(2, Status.NURSE, nodeHU034);
        generateRandomStaff(2, Status.NURSE, nodeHU041);
        generateRandomStaff(2, Status.NURSE, nodeHU042);
        generateRandomStaff(2, Status.NURSE, nodeHU043);
        generateRandomStaff(2, Status.NURSE, nodeHU044);

        generateRandomStaff(2, Status.DOCTOR, nodeService01);
        generateRandomStaff(2, Status.DOCTOR, nodeService02);
        generateRandomStaff(2, Status.DOCTOR, nodeService03);
        generateRandomStaff(2, Status.DOCTOR, nodeService04);
        generateRandomStaff(1, Status.DOCTOR, nodePole0);

        //pole emergencies - staffs

        generateRandomStaff(2, Status.NURSE, nodeHCUEmergency111);
        generateRandomStaff(2, Status.NURSE, nodeHCUEmergency121);
        generateRandomStaff(2, Status.NURSE, nodeHCUEmergency131);
        generateRandomStaff(2, Status.NURSE, nodeHCUEmergency141);
        generateRandomStaff(2, Status.NURSE, nodeHUEmergency11);
        generateRandomStaff(2, Status.NURSE, nodeHUEmergency12);
        generateRandomStaff(2, Status.NURSE, nodeHUEmergency13);
        generateRandomStaff(2, Status.NURSE, nodeHUEmergency14);
        generateRandomStaff(1, Status.DOCTOR, nodeServiceEmergency);
        generateRandomStaff(1, Status.DOCTOR, nodePoleEmergency);


        Staff alainFieney = new Staff("afieney@aphp.fr", "fieney", "Fieney", "Alain", generatePhoneNumber("06"), addressSamplesOnParis.get(Math.abs(rand.nextInt(addressSamplesOnParis.size()))).toString(), Status.DOCTOR);
        alainFieney.setNode(nodeService04);
        staffRepository.save(alainFieney);
        staffs.add(alainFieney);

        //Patient 1
        AdminFile adminFile1 = generateAdminFile(
                "Szalony",
                "Raymond",
                "M",
                "1958-06-25",
                new Address("", "Livry-Gargan", 93190, "france", "93046"),
                "18 allée de Chartres",
                "93190",
                "Livry-Gargan",
                null,
                "France",
                "szalony.raymond@thisisafakeaddress.com",
                "0143320054",
                "0654789565",
                "0143320053",
                "Soldat"
        );

        MedicalInfo medicalInfo1 = new MedicalInfo();
        medicalInfo1.addInformations("allergies", "sel", "gluten", "lactose");
        medicalInfoRepository.save(medicalInfo1);

        MedicalFile medicalFile1 = new MedicalFile(true, nodeHCU1111.getId());
        medicalFileRepository.save(medicalFile1);

        Observation observation1 = new Observation(staff2.getId(),
                "Le patient a été admis pour une chute violente depuis une échelle."
        );
        observationRepository.save(observation1);

        Examen exam1 = new Examen(
                "Chute d'une échelle",
                "Radio crânienne",
                null,
                "Traumatisme crânien",
                "2018-02-16",
                doc0.getId()
        );
        examenRepository.save(exam1);

        Prescription prescription1 = new Prescription(
                "Morphine, injection par intra-veineuse, 1mL toutes les 10 minutes",
                new ArrayList<>(),
                "2018-02-16",
                "2018-02-16",
                "2018-02-19",
                staff2.getId()
        );
        prescriptionRepository.save(prescription1);

        medicalFile1.addObservation(observation1);
        medicalFile1.addExamen(exam1);
        medicalFile1.addPrescription(prescription1);

        Patient patient1 = new Patient(adminFile1, medicalInfo1);
        patient1.addMedicalFile(medicalFile1);

        patientRepository.save(patient1);

        //Patient autogeneration

        generateRandomPatient(20, staff2, doc1, nodeService11, 12, 85);
        generateRandomPatient(20, alainFieney, alainFieney, nodeService04, 2, 12);
        //setSarahLeroyCase(alainFieney, nodeServicePediatricsPneumonology);
        System.err.println("\n====================> INIT DATA IS DONE <====================\n");
    }

    private AdminFile generateAdminFile(String lastName,
                                        String firstName,
                                        String gender,
                                        String birthDate,
                                        Address birthPlace,
                                        String address,
                                        String postalCode,
                                        String city,
                                        String addressComplement,
                                        String country,
                                        String email,
                                        String phone,
                                        String cellPhone,
                                        String fax,
                                        String profession) {
        StringBuilder socialID = new StringBuilder();

        Random random = new Random();

        socialID.append(gender.equals("M") ? 1 : 2);
        socialID.append(birthDate.subSequence(2, 4)).append(birthDate.subSequence(5, 7));
        socialID.append(birthPlace.getPostCode());
        for (int i = 0; i < 5; i++)
            socialID.append(random.nextInt(10));

        AdminFile adminFile = new AdminFile(
                lastName,
                firstName,
                gender,
                birthDate,
                birthPlace.getCity(),
                socialID.toString(),
                address,
                postalCode,
                city,
                addressComplement,
                country,
                email,
                phone,
                cellPhone,
                fax,
                profession
        );

        adminFileRepository.save(adminFile);
        return adminFile;
    }

    private String generatePhoneNumber(String indicatif) {
        StringBuilder phone = new StringBuilder().append(indicatif);
        for (int i = 0; i < 8; i++)
            phone.append(rand.nextInt(10));
        return phone.toString();
    }

    private String generateRandomOccupation(int age) {
        if (age < 6)
            return "Sans-emploi";
        if (age < 21)
            return "Etudiant";
        if (age > 63)
            return "Retraité";

        return jobs.get(rand.nextInt(jobs.size()));
    }

    private String generateRandomOccupationByGender(int age, boolean isMale) {
        if (age < 6)
            return "Sans-emploi";

        if (isMale) {
            if (age < 21)
                return "Étudiant";
            if (age > 63)
                return "Retraité";

            if (jobsM.isEmpty()) {
                return generateRandomOccupation(age);
            }
            return jobsM.get(rand.nextInt(jobsM.size()));
        }
        if (age < 21)
            return "Étudiante";
        if (age > 63)
            return "Retraitée";
        if (jobsF.isEmpty()) {
            return generateRandomOccupation(age);
        }
        return jobsF.get(rand.nextInt(jobsF.size()));
    }

    private List<AdminFile> generateRandomAdminFiles(int nb, int minAge, int maxAge) {

        List<AdminFile> list = new ArrayList<>();
        if (firstNames.isEmpty() || prenoms.isEmpty() || addressSamples.isEmpty() || emailBox.isEmpty())
            return list;
        for (int i = 0; i < nb; i++) {
            String firstName = firstNames.get(rand.nextInt(firstNames.size()));
            String gender;
            if (prenoms.get(firstName))
                gender = "M";
            else gender = "F";
            int birthYear = (2017 - Math.abs(rand.nextInt(maxAge - minAge)) + minAge);
            int birthMonth = (1 + rand.nextInt(12));
            int birthDays = (1 + rand.nextInt(28));
            String birthDate = birthYear + "-" + String.format("%02d", birthMonth) + "-" + String.format("%02d", birthDays);
            Address address = addressSamples.get(rand.nextInt(addressSamples.size()));
            Address birthAddress = addressSamples.get(rand.nextInt(addressSamples.size()));
            String lastName = firstNames.get(rand.nextInt(firstNames.size()));
            list.add(generateAdminFile(
                    lastName,
                    firstName,
                    gender,
                    birthDate,
                    birthAddress,
                    address.getAddress(),
                    address.getPostCode().toString(),
                    address.getCity(),
                    null,
                    address.getCountry(),
                    lastName + "." + firstName + emailBox.get(rand.nextInt(emailBox.size())),
                    generatePhoneNumber("01"),
                    generatePhoneNumber("06"),
                    generatePhoneNumber("01"),
                    generateRandomOccupationByGender(LocalDateTime.now().getYear() - birthYear, prenoms.get(firstName))
            ));
        }
        return list;
    }

    private List<Patient> generateRandomPatient(int nb, Staff resp, Staff docExamen, Node nodeService, int minAge, int maxAge) {

        List<AdminFile> adminFiles = generateRandomAdminFiles(nb, minAge, maxAge);
        for (AdminFile adminFile : adminFiles) {

            MedicalInfo medicalInfo = new MedicalInfo();
            medicalInfo.addInformations("allergies", "sel", "gluten");
            medicalInfoRepository.save(medicalInfo);
            Patient patient = new Patient(adminFile, medicalInfo);
            patientRepository.save(patient);
            Node nodeHU = nodeService.getSubNodes().get(Math.abs(rand.nextInt(nodeService.getSubNodes().size())));
            Node nodeHCU = nodeHU.getSubNodes().get(Math.abs(rand.nextInt(nodeHU.getSubNodes().size())));
            switch (nodeService.getSpeciality().toLowerCase()) {
                case "chirurgie cardio-thoracique":
                    System.err.println("New cardio-thorax case");
                    System.err.println(setCaseCardioThorax(nodeHCU, resp.getId(), docExamen.getId(), patient));
                    break;

                case "service de pneumologie":
                    System.err.println("New pneumologie case");
                    System.err.println(setCasePediatricsPneumology(nodeHCU, resp.getId(), patient));
                    break;
            }
            list.add(patient);
        }
        return list;
    }

    private List<Staff> generateRandomStaff(int nb, Status status, Node node) {

        List<Staff> list = new ArrayList<>();
        if (firstNames.isEmpty() || addressSamplesOnParis.isEmpty())
            return list;
        for (int i = 0; i < nb; i++) {
            String firstName = firstNames.get(rand.nextInt(firstNames.size()));
            Address address = addressSamplesOnParis.get(Math.abs(rand.nextInt(addressSamplesOnParis.size())));
            String lastName = firstNames.get(rand.nextInt(firstNames.size()));
            Staff staff = new Staff(
                    firstName.charAt(1) + lastName + "@aphp.fr",
                    generatePhoneNumber("00"),
                    lastName,
                    firstName,
                    generatePhoneNumber("06"),
                    address.getAddress() + "/" + address.getPostCode().toString() + " " + address.getCity(),
                    status
            );
            if (staffs.stream().anyMatch(p -> p.getLogin().equals(firstName.charAt(1) + lastName + "@aphp.fr")))
                i--;
            else {
                staff.setNode(node);
                staffRepository.save(staff);
                list.add(staff);
                staffs.add(staff);
            }
        }
        return list;
    }

    private String printDate(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    private void addObservation(MedicalFile mf, Observation o) {
        observationRepository.save(o);
        mf.addObservation(o);
    }

    private void addExamen(MedicalFile mf, Examen e) {
        examenRepository.save(e);
        mf.addExamen(e);
    }

    private void addPrescription(MedicalFile mf, Prescription p) {
        prescriptionRepository.save(p);
        mf.addPrescription(p);
    }

    private boolean setCaseCardioThorax(Node node, Integer staffIdObs, Integer staffIdExa, Patient patient) {

        List<Staff> nurses = staffs.stream().filter(p -> p.getNode().equals(node) && p.getStatus().equals(Status.NURSE)).collect(Collectors.toList());
        LocalDateTime firstDate = LocalDateTime.now().minusMonths(2).minusDays(Math.abs(rand.nextInt(10) + 1)).plusMinutes(Math.abs(rand.nextInt(3600) + 1));
//        int minAge = 12;
//        int maxAge = 100;
        MedicalFile mf1 = new MedicalFile(false, node.getId());
        MedicalFile mf2 = new MedicalFile(true, node.getId());
        //todo found image
        medicalFileRepository.save(mf1);

        medicalFileRepository.save(mf2);

        addObservation(mf1, new Observation(staffIdObs, "Patient ayant des douleurs de poitrine depuis 3 semaines et essouflement cardiaque rapide", printDate(firstDate)));
        addObservation(mf1, new Observation(staffIdObs, "Angine de poitrine récurente soupsonné, besoin d'une coronarographie", printDate(firstDate.plusMinutes(7))));
        addExamen(mf1, new Examen("Angine de poitrine récurente", "Coronarographie", new ArrayList<>(), "Découverte d'une maladie coronarienne", printDate(firstDate.plusDays(10).plusMinutes(7 + 7)), staffIdExa));
        addPrescription(mf1, new Prescription("Examen coronarographie, 1/mois pendant 1 an", new ArrayList<>(), printDate(firstDate.plusDays(10).plusMinutes(7 + 7)), printDate(firstDate.plusDays(10).plusMinutes(7 + 7)), printDate(firstDate.plusMonths(13).plusDays(10).plusMinutes(7 + 7)), staffIdObs));
        addObservation(mf1, new Observation(staffIdObs, "Sortie du patient après examen et prescription pour la maladie coronarienne prescrite", printDate(firstDate.plusDays(11))));
        addObservation(mf2, new Observation(staffIdObs, "Visite pour examen coronarographie mensuel", printDate(firstDate.plusMonths(1))));
        addExamen(mf2, new Examen("Examen mensuel", "Coronarographie", new ArrayList<>(), "Découverte d'une ischémie silencieuse", printDate(firstDate.plusMonths(1).plusDays(3).plusMinutes(433)), staffIdExa));
        addObservation(mf2, new Observation(staffIdObs, "Demande d'un examen appronfondie", printDate(firstDate.plusMonths(1).plusDays(4).plusMinutes(43))));
        addExamen(mf2, new Examen("Demande d'examen appronfondie", "Coronarographie", new ArrayList<>(), "Découverte de lésions bitronculaires sévères sous la forme d'une sténose du tronc gauche supérieure à 60 % et d'une sténose de l'artère circonflexe supérieure à 60 %", printDate(firstDate.plusMonths(1).plusDays(7).plusMinutes(433)), staffIdExa));
        addExamen(mf2, new Examen("Ischémie silencieuse avec lésions bitronculaires sévères sous la forme d'une sténose du tronc gauche supérieure à 60 % et d'une sténose de l'artère circonflexe supérieure à 60 %", "Intervention: double revascularisation coronarienne", new ArrayList<>(), "Déroulé sans problème", printDate(firstDate.plusMonths(1).plusDays(17).plusMinutes(433)), staffIdExa));

        List<Posology> lp = new ArrayList<>();
        for (LocalDateTime ldt = firstDate.plusMonths(1).plusDays(17).plusMinutes(433); ldt.isBefore(LocalDateTime.now()); ldt = ldt.plusDays(1)) {
            Staff nurse = nurses.get(Math.abs(rand.nextInt(nurses.size())));
            Posology p = new Posology(printDate(ldt), "Bilan patient ok", nurse.getFirstName(), nurse.getLastName(), true);
            //posologyRepository.save(p);
            lp.add(p);
        }
        addPrescription(mf2, new Prescription("Repos sous surveillance. Bilan 1/jour", lp, printDate(firstDate.plusMonths(1).plusDays(17).plusMinutes(433)), printDate(firstDate.plusMonths(1).plusDays(17).plusMinutes(433)), printDate(firstDate.plusMonths(2).plusDays(17).plusMinutes(433)), staffIdObs));

        patient.addMedicalFile(mf1);
        patient.addMedicalFile(mf2);
        return true;
    }

    private void setSarahLeroyCase(Staff doctor, Node nodeServicePediatry) {

    }

    private boolean setCasePediatricsPneumology(Node node, int doctorId, Patient patient) {

        //emergency service

        LocalDateTime firstDate = LocalDateTime.now().minusDays(2).minusMinutes(Math.abs(rand.nextInt(1000)));

        MedicalFile mf1 = new MedicalFile(true, node.getId());
        medicalFileRepository.save(mf1);
        List<Staff> nurses = staffs.stream().filter(p -> p.getNode().equals(node) && p.getStatus().equals(Status.NURSE)).collect(Collectors.toList());

        //int staffId, String comment, String date){
            //int staffId, String comment, String date
            addObservation(mf1, new Observation(doctorId, "Amené par sa mère pour une gêne respiratoire évoluant depuis 5 jours.", printDate(firstDate)));
            addObservation(mf1, new Observation(doctorId,  "Le patient a pris 50 mL de chacun des biberons de la journée. Les parents sont enrhumés et sont allés voir leur médecin traitant qui leur a donné un traitement symptomatique", printDate(firstDate)));
            addObservation(mf1, new Observation(doctorId, "À l’arrivée : T° = 37,4 °C ; FR = 65/min ; saturation = 95 % ; FC = 145/min ; TA = 85/65 mmHg, un tirage intercostal ainsi qu’un battement des ailes du nez. ", printDate(firstDate.plusMinutes(27))));
            addObservation(mf1, new Observation(doctorId, "L’auscultation retrouve des sibilants diffus avec un murmure vésiculaire mieux perçu à droite. L’examen du carnet de santé montre que le patient est né au terme d’une grossesse normale. " , printDate(firstDate.plusMinutes(28))));
            addObservation(mf1, new Observation(doctorId, "La maman avait réussi pendant la grossesse à arrêter sa consommation de tabac ; elle a repris après l’accouchement. ", printDate(firstDate.plusMinutes(29))));
            addObservation(mf1, new Observation(doctorId, "Il n’y a pas d’antécédent médical particulier, notamment de détresse respiratoire néonatale. ", printDate(firstDate.plusMinutes(30))));
            addObservation(mf1, new Observation(doctorId, "Ses parents ont un terrain atopique avec rhinoconjonctivite allergique saisonnière et asthme dans l’enfance.", printDate(firstDate.plusMinutes(31))));

            //String treatment, List<Posology> posologys, String date, String startDate, String endDate, Integer staffId
            addPrescription(mf1, new Prescription("Radiographie du thorax", new ArrayList<>(), printDate(firstDate.plusMinutes(32)), printDate(firstDate.plusMinutes(32)), printDate(firstDate.plusMinutes(32).plusDays(1)), doctorId));
            int prescriptionIndex = 0;

//            addPrescription(mf1, new Prescription("Désobstructions  rhinopharyngées", new ArrayList<>(), printDate(firstDate.plusMinutes(32)), printDate(firstDate.plusMinutes(32)), printDate(firstDate.plusMinutes(32).plusDays(1)), emergencyStaff.getId()));
//            prescriptionIndex++;

            //String motive, String description, List<String> imgPath, String observation, String date, Integer staffId
            addExamen(mf1, new Examen("FR > 60/min et Difficultés alimentaires, possible bronchiolite aiguë", "Radiographie du thorax", new ArrayList<>(), "La radiographie du thorax montre une distension thoracique sans foyer ni cardiomégalie", printDate(firstDate.plusMinutes(25).plusDays(1)), doctorId));

            //String date, String observation, String nurseName, String nurseSurname, boolean taken
            mf1.getPrescriptions().get(prescriptionIndex).addPosology(new Posology(printDate(firstDate.plusMinutes(25).plusDays(1)), "Examen effectué", nurses.get(0).getFirstName(), nurses.get(0).getLastName(), true));

//            addPrescription(mf1, new Prescription("Proclive  dorsal", new ArrayList<>(), printDate(firstDate.plusMinutes(32)), printDate(firstDate.plusMinutes(32)), printDate(firstDate.plusMinutes(32).plusDays(1)), emergencyStaff.getId());
//            prescriptionIndex++;

//            addPrescription(mf1, new Prescription("Scope  cardiorespiratoire", new ArrayList<>(), printDate(firstDate.plusMinutes(32)), printDate(firstDate.plusMinutes(32)), printDate(firstDate.plusMinutes(32).plusDays(1)), emergencyStaff.getId());
//            prescriptionIndex++;

            addObservation(mf1, new Observation(doctorId, "Patient a du mal à respirer", printDate(firstDate.plusDays(2).minusHours(3).minusMinutes(234))));

            patient.addMedicalFile(mf1);
            return true;
    }
}

        /*


Noémie restera 48 h hospitalisée, avec une évolution rapidement favorable.
Elle revient à l’âge de 4 mois pour un épisode semblable de dyspnée expiratoire débuté la veille. Elle est gardée depuis
un mois à la crèche.
À l’arrivée : T° = 37,8 °C ; FR = 38/min ; saturation = 98 % ; FC = 135/min ; TA = 85/56 mmHg.
Elle  est  en  bon  état  général.  Elle  a  un  tirage  intercostal  et  sus-claviculaire.  Elle  conserve  une  excellente  perfusion
périphérique et vous ne palpez pas d’hépatomégalie. L’auscultation retrouve des sibilants et des crépitants.
4.
Quel(s) est (sont) le(s) diagnostic(s) compatible(s) ?
❏
A.  Bronchiolite
❏
B.  Asthme du nourrisson
❏
C.  Myocardite
❏
D.  Pneumonie
❏
E.  Trachéite
5.
Quelle est votre prise en charge aux urgences ?
❏
A.  Hospitalisation
❏
B.  Corticothérapie  orale
❏
C.  Nébulisation  d’adrénaline
❏
D.  Kinésithérapie  respiratoire
❏
E.  Désobstructions  rhinopharyngées
6.
Quelle(s) mesure(s) est (sont) à proposer en prévention des récidives ?
❏
A.  Arrêt du tabagisme maternel
❏
B.  Traitement par inhibiteur de la pompe à protons
❏
C.  Sérothérapie anti-VRS par palivizumab
❏
D.  Lavage des mains en période épidémique
❏
E.  Antibiothérapie  alternée
7.
Quel(s) vaccin(s) doit (doivent) apparaître à son âge sur son carnet de santé ?
❏
A.  2 doses DTP – Ca – Hib
❏
B.  1 dose méningocoque C
❏
C.  1 dose ROR
❏
D.  1 dose varicelle
❏
E.  2 doses pneumocoque 23 valents
Noémie fait quatre épisodes de dyspnée expiratoire au cours de sa première année de vie.
Elle a actuellement 16 mois. Elle suit son couloir de croissance en poids et taille. Elle a été diversifiée à l’âge de 4 mois
et boit du lait de croissance.
8.
Quel(s) examen(s) réalisez-vous pour argumenter le diagnostic d’asthme ?
❏
A.  Radiographie du thorax
❏
B.  EFR
❏
C.  Gaz du sang
❏
D.  Scanner  thoracique
❏
E.  Test de la sueur
9.
Quel(s) est (sont) le(s) diagnostic(s) différentiel(s) à évoquer ?
❏
A.  Tuberculose
❏
B.  Déficit  immunitaire
❏
C.  Trachéomalacie
❏
D.  Double arc aortique
❏
E.  Dysplasie  bronchopulmonaire
Noémie a maintenant 3 ans. Les parents n’arrivent plus à gérer ses nombreux épisodes de dyspnée expiratoire. Vous
reprenez l’enquête diagnostique et décidez de réaliser un bilan allergologique.
10.   Quel bilan allergologique proposez-vous ?
❏
A.  Anamnèse
❏
B.
Pricks tests
❏
C.
Patchs tests
❏
D.  Eosinophilie  sanguine
❏
E.  IgE  totales
ÉNONCÉ
100
Les  tests  cutanés  sont  positifs  pour  les  graminés,  l’œuf  et  l’arachide.  L’anamnèse  vous  apprend  qu’elle  consomme
régulièrement de l’œuf sans réaction, mais qu’elle n’a jamais essayé la cacahuète.
À 4 ans, Noémie est amenée de nouveau aux urgences. Elle a commencé à tousser en quintes puis a développé une
gêne respiratoire sifflante après avoir gouté aux biscuits apéritifs lors d’un barbecue familial. Elle a eu également mal au
ventre, vomi et est devenue toute rouge avec un œdème de la face. Les parents ont essayé des bouffées de salbutamol
en chambre d’inhalation mais cela ne l’a que très peu améliorée.
À l’arrivée : T° = 36,5 °C ; FR = 42/min ; saturation = 96 % ; FC = 150/min ; TA = 69/50 mmHg.
L’enfant est agitée, dysphonique. Signes de lutte expiratoire. Extrémités chaudes avec TRC à 2 secondes, pas de foie
palpable. L’abdomen est souple et sans défense.
Vous observez des lésions urticariennes de l’ensemble du corps avec un œdème des lèvres.
11.   Quelle est votre prise en charge thérapeutique aux urgences ?
❏
A.  Oxygénothérapie
❏
B.  Adrénaline 0,01 mg/kg en IM
❏
C.  Nébulisations de salbutamol
❏
D.  Antiémétique
❏
E.  Catécholamines
L’état clinique s’améliore suite à l’injection IM d’adrénaline ainsi qu’aux thérapeutiques symptomatiques instituées.
12.   Quelle est la suite de votre prise en charge ?
❏
A.  Retour à domicile
❏
B.  Dosage de tryptase
❏
C.  Carte  d’allergique
❏
D.  PAI
❏
E.  Trousse d’urgence (anti-H1, seringue auto-injectable d’adrénaline)
Le diagnostic d’allergie à l’arachide a été retenu après cet épisode anaphylactique.
Le tableau respiratoire se calme progressivement jusqu’à l’âge de 6 ans et demi. Elle a bénéficié d’un traitement par
corticoïdes inhalés à doses moyennes.
13.   Que pouvez-vous dire à propos de ce traitement à l’âge de Noémie ?
❏
A.  Administration par aérosol-doseur
❏
B.  Nécessité d’une chambre d’inhalation
❏
C.  Risque de candidose buccale
❏
D.  Seule type de traitement de fond à cet âge
❏
E.  Peut ralentir la vitesse de croissance
14.   Quel(s) est (sont) le(s) élément(s) de suivi d’un asthme à cet âge ?
❏
A.  EFR
❏
B.
Peak-flow
❏
C.  ECBC
❏
D.  Cliché  thoracique
❏
E.  Observance  thérapeutique
Près d’un an plus tard, Noémie a un syndrome fébrile à 39 °C évoluant depuis 48 h avec rhinite et pharyngite. Sa
maman lui auto-administre un traitement par amoxicilline. Quelques heures après la prise, Noémie est conduite aux
urgences pour une éruption érythémateuse maculopapuleuse du tronc constatée lors de l’habillage. L’examen clinique
est rassurant par ailleurs.
La famille a peur de l’éventualité d’une nouvelle allergie.
15.   Quelle est votre attitude pratique ?
❏
A.  Hospitalisation
❏
B.  Adrénaline 0,01 mg/kg en IM
❏
C.  Arrêt de la prescription d’amoxicilline
❏
D.  Test de provocation ultérieure
❏
E.  Contre-indication aux C3G

         */
