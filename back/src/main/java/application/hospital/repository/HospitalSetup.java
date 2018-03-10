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
import application.prescription.domain.Prescription;
import application.prescription.repository.PrescriptionRepository;
import application.staff.Status;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;
import org.glassfish.embeddable.web.Context;
import org.glassfish.embeddable.web.config.SecurityConfig;
import utils.Address;
import utils.InseeRef;
import utils.Parse;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
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

    private Random rand = new Random();

    private int nbAutoGeneratePatient = 300;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    List<String> jobsM = new ArrayList<>();
    List<String> jobsF = new ArrayList<>();
    List<String> jobs = new ArrayList<>();

    private void initJobsList(){
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
//        try {
//            List<String> dataJobs = Parse.parseFileToString(Paths.get(this.getClass().getClassLoader().getResource("dataForSetup/Professions.csv").getPath()));
//            jobs = Parse.parseJobs(dataJobs);
//            for(String job:jobs){
//                String[] values = job.split("/");
//                if(values.length == 2){
//                    jobsM.add(values[0]);
//                    jobsF.add(values[1]);
//                }
//            }
//        } catch (IOException ioe){
//            //do nothing
//        }
    }

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager entityManager;
        @PostConstruct
    private void createData() {
        initJobsList();
        //Hospital and nodes
        Hospital hospital = new Hospital("Hopital Rothschild", "France", "5 Rue Santerre 75012 Paris");
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
        Staff staff1 = new Staff("ameline", "ameline", "Moreau", "Ameline", "0606060606", "123 fake street", Status.ADMIN);
        Staff staff2 = new Staff("charles", "charles", "Da Silva Costa", "Charles", "0606060607", "125 fake street", Status.DOCTOR);
        Staff staff3 = new Staff("jeanluc", "jeanluc", "Fernandes", "Jean-Luc", "0606060608", "127 fake street", Status.SECRETARY);
        staff1.setNode(nodePole1);
        staff2.setNode(nodePole1);
        staff3.setNode(nodePole1);
        staffRepository.save(staff1);
        staffRepository.save(staff2);
        staffRepository.save(staff3);

        //dataset accounts for coherent data
        Staff doc1 = new Staff("mgrey", "mgrey", "Grey", "Meredith", "0606060608", "15 rue Chapatte 75001 Paris", Status.DOCTOR);
        doc1.setNode(nodePole2);
        staffRepository.save(doc1);

        Staff doc2 = new Staff("cyang", "cyang", "Yang", "Cristina", "0606060609", "25 allée de la brûme 75012 Paris", Status.DOCTOR);
        doc2.setNode(nodeService11);
        staffRepository.save(doc2);

        Staff doc3 = new Staff("dshep", "dshep", "Shepherd", "Derek", "0606060610", "15 rue Chapatte 75001 Paris", Status.DOCTOR);
        doc3.setNode(nodeService12);
        staffRepository.save(doc3);

        Staff doc4 = new Staff("akepn", "akepn", "Kepner", "April", "0606060611", "32 rue de la mairie 77450 Esbly", Status.DOCTOR);
        doc4.setNode(nodeService13);
        staffRepository.save(doc4);

        Staff doc5 = new Staff("arobb", "arobb", "Robbins", "Arizona", "0606060612", "47 rue Pasteur 77700 Coupvray", Status.DOCTOR);
        doc5.setNode(nodeService14);
        staffRepository.save(doc5);

        Staff doc6 = new Staff("mbail", "mbail", "Bailey", "Miranda", "0606060613", "25 avenue de la République 77340 Pontault-Combault", Status.DOCTOR);
        doc6.setNode(nodePole1);
        staffRepository.save(doc6);

        Staff doc7 = new Staff("ohunt", "ohunt", "Hunt", "Owen", "0606060615", "64 allée des mille et une nuits 77184 Emerainville", Status.DOCTOR);
        doc7.setNode(nodePole3);
        staffRepository.save(doc7);

        Staff doc8 = new Staff("ghous", "ghous", "House", "Gregory", "0606060616", "18 allée Montesquieu 93190 Livry-Gargan", Status.DOCTOR);
        doc8.setNode(nodeService21);
        staffRepository.save(doc8);

        Staff nurse1 = new Staff("obaker", "obaker", "Baker", "Oscar", "0606060617", "65 avenue Jean Jaurès 93390 Clichy-sous-bois", Status.NURSE);
        nurse1.setNode(nodeHU111);
        staffRepository.save(nurse1);

        Staff nurse2 = new Staff("obake2", "obake2", "Baker", "Olivia", "0606060618", "65 avenue Jean Jaurès 93390 Clichy-sous-bois", Status.NURSE);
        nurse2.setNode(nodeHU112);
        staffRepository.save(nurse2);

        Staff nurse3 = new Staff("mvalen", "mvalen", "Valentine", "Mai", "0606060619", "16 allée Monge 93320 Les Pavillons-sous-bois", Status.NURSE);
        nurse3.setNode(nodeHU113);
        staffRepository.save(nurse3);




        //Patient 1
        AdminFile adminFile1 = generateAdminFile(
                "Szalony",
                "Raymond",
                "M",
                "1958-06-25",
                new Address("","Livry-Gargan", 93190, "france", "93046"),
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

        MedicalFile medicalFile1 = new MedicalFile(true, 20);
        medicalFileRepository.save(medicalFile1);

        Observation observation1 = new Observation(23,
                "Le patient a été admis pour une chute violente depuis une échelle."
        );
        observationRepository.save(observation1);

        Examen exam1 = new Examen(
                "Chute d'une échelle",
                "Radio crânienne",
                null,
                "Traumatisme crânien",
                "2018-02-16",
                23
        );
        examenRepository.save(exam1);

        Prescription prescription1 = new Prescription(
                "Morphine",
                new ArrayList<Posology>(),
                "2018-02-16",
                "2018-02-16",
                "2018-02-19",
                23
        );
        prescriptionRepository.save(prescription1);

        medicalFile1.addObservation(observation1);
        medicalFile1.addExamen(exam1);
        medicalFile1.addPrescription(prescription1);

        Patient patient1 = new Patient(adminFile1, medicalInfo1);
        patient1.addMedicalFile(medicalFile1);

        patientRepository.save(patient1);


        //Patient autogeneration
        generateRandomPatient(nbAutoGeneratePatient);

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

        socialID.append(gender.equals("M") ? 1 : 2 );
        socialID.append(birthDate.subSequence(2, 4)).append(birthDate.subSequence(5,7));
        socialID.append(birthPlace.getPostCode());
        for(int i = 0 ; i < 5 ; i++)
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

    private String generatePhoneNumber(String indicatif){
        StringBuilder phone = new StringBuilder().append(indicatif);
        for(int i = 0; i<8; i++)
            phone.append(rand.nextInt(10));
        return phone.toString();
    }

    private String generateRandomOccupation(int age){
        if(age < 6)
            return "Sans-emploi";
        if(age < 21)
             return "Etudiant";
        if(age > 63)
            return "Retraité";

        return jobs.get(rand.nextInt(jobs.size()));
    }

    private String generateRandomOccupationByGender(int age, boolean isMale){
        if(age < 6)
            return "Sans-emploi";

        if(isMale) {
            if (age < 21)
                return "Étudiant";
            if (age > 63)
                return "Retraité";

            if(jobsM.isEmpty()){
                return generateRandomOccupation(age);
            }
            return jobsM.get(rand.nextInt(jobsM.size()));
        }
        if (age < 21)
            return "Étudiante";
        if (age > 63)
            return "Retraitée";
        if(jobsF.isEmpty()){
            return generateRandomOccupation(age);
        }
        return jobsF.get(rand.nextInt(jobsF.size()));
    }

    private List<AdminFile> generateRandomAdminFiles(int nb){
        List<String> emailBox = new ArrayList<>();
        emailBox.add("@yahoo.fr");
        emailBox.add("@gmail.com");
        emailBox.add("@free.fr");
        emailBox.add("@hotmail.fr");
        emailBox.add("@hotmail.com");
        emailBox.add("@live.fr");
        emailBox.add("@sfr.fr");
        emailBox.add("@orange.fr");
        emailBox.add("@yopmail.com");

        List<AdminFile> list = new ArrayList<>();
        try {


            List<String> dataPrenom = Parse.parseFileToString(Paths.get(this.getClass().getClassLoader().getResource("dataForSetup/Prenoms.csv").getPath()));
            HashMap<String, Boolean> prenoms = Parse.parseFirstname(dataPrenom);

            List<String> dataInseeRefs = Parse.parseFileToString(Paths.get(this.getClass().getClassLoader().getResource("dataForSetup/laposte_hexasmal.csv").getPath()));
            List<InseeRef> listInseeRefs = Parse.parseInseeRef(dataInseeRefs);

            List<String> dataAddress = Parse.parseFileToString(Paths.get(this.getClass().getClassLoader().getResource("dataForSetup/les_bureaux_de_poste_et_agences_postales_en_idf.csv").getPath()));
            List<Address> addressSamples = Parse.parseSampleAddress(dataAddress, listInseeRefs);

            List<String> firstNames = new ArrayList<>(prenoms.keySet());
            for(int i= 0; i < nb; i++) {
                String firstName = firstNames.get(rand.nextInt(firstNames.size()));
                String gender;
                if (prenoms.get(firstName))
                    gender = "M";
                else gender = "F";
                int birthYear = (2017 - Math.abs(rand.nextInt(85)));
                int birthMonth = (1 + rand.nextInt(12));
                int birthDays = (1 + rand.nextInt(28));
                String birthDate = birthYear + "-" + String.format("%02d", birthMonth)+"-"+String.format("%02d", birthDays);
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
                        generateRandomOccupationByGender(LocalDateTime.now().getYear()-birthYear, prenoms.get(firstName))
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private List<Patient> generateRandomPatient(int nb){
        List<Patient> list = new ArrayList<>();

        List<AdminFile> adminFiles = generateRandomAdminFiles(nb);
        for(AdminFile adminFile : adminFiles) {
            MedicalInfo medicalInfo = new MedicalInfo();
            medicalInfo.addInformations("allergies", "sel", "gluten", "lactose");
            medicalInfoRepository.save(medicalInfo);
            Patient patient = new Patient(adminFile, medicalInfo);
            patientRepository.save(patient);
            list.add(patient);
        }
        return list;
    }

    private List<MedicalFile> generateRandomMedicalFile(AdminFile adminFile, int nb) {
        List<MedicalFile> list = new ArrayList<>();
        for(int i= 0; i < nb; i++) {

        }
        return list;
    }

    private List<Observation> generateRandomObservation(MedicalFile medicalFile, int nb) {
        List<Observation> list = new ArrayList<>();
        for(int i= 0; i < nb; i++) {

        }
        return list;
    }

    private List<Examen> generateRandomExamem(MedicalFile medicalFile, int nb) {
        List<Examen> list = new ArrayList<>();
        for(int i= 0; i < nb; i++) {

        }
        return list;
    }

    private List<Prescription> generateRandomPrescription(MedicalFile medicalFile, int nb) {
        List<Prescription> list = new ArrayList<>();
        for(int i= 0; i < nb; i++) {

        }
        return list;
    }

}
