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
import application.prescription.domain.Prescription;
import application.prescription.repository.PrescriptionRepository;
import application.staff.Status;
import application.staff.domain.Staff;
import application.staff.repository.StaffRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Random;

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



    @PostConstruct
    private void createData() {

        //Hospital and nodes
        Hospital hospital = new Hospital("Hopital Rothschild", "France", "5 Rue Santerre 75012 Paris");
        Node nodePole1 = new Node("Chirurgie", NodeLevel.pole);
        Node nodePole2 = new Node("Médecine générale", NodeLevel.pole);
        Node nodePole3 = new Node("Radiologie", NodeLevel.pole);
        Node nodeService11 = new Node("Chirurgie cardio-thoracique", NodeLevel.service);
        Node nodeService12 = new Node("Neurochirurgie", NodeLevel.service);
        Node nodeService13 = new Node("Chirurgie orthopédique et traumatologique", NodeLevel.service);
        Node nodeService21 = new Node("Service de consultation", NodeLevel.service);
        Node nodeService22 = new Node("Service de prescription", NodeLevel.service);
        Node nodeService23 = new Node("Service de rééducation", NodeLevel.service);
        Node nodeService31 = new Node("Radiologie cardio-thoracique", NodeLevel.service);
        Node nodeService32 = new Node("Radiologie crânienne", NodeLevel.service);
        Node nodeService33 = new Node("Radiologie des membres", NodeLevel.service);
        Node nodeHU111 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU112 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU113 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU114 = new Node("Unité 4", NodeLevel.hospitalUnit);
        Node nodeHU121 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU122 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU123 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU124 = new Node("Unité 4", NodeLevel.hospitalUnit);
        Node nodeHU131 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU132 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU133 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU134 = new Node("Unité 4", NodeLevel.hospitalUnit);
        Node nodeHU211 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU212 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU213 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU214 = new Node("Unité 4", NodeLevel.hospitalUnit);
        Node nodeHU221 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU222 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU223 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU224 = new Node("Unité 4", NodeLevel.hospitalUnit);
        Node nodeHU231 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU232 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU233 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU234 = new Node("Unité 4", NodeLevel.hospitalUnit);
        Node nodeHU311 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU312 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU313 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU314 = new Node("Unité 4", NodeLevel.hospitalUnit);
        Node nodeHU321 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU322 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU323 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU324 = new Node("Unité 4", NodeLevel.hospitalUnit);
        Node nodeHU331 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU332 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU333 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU334 = new Node("Unité 4", NodeLevel.hospitalUnit);

        nodePole1.addNode(nodeService11).addNode(nodeService12).addNode(nodeService13);
        nodePole2.addNode(nodeService21).addNode(nodeService22).addNode(nodeService23);
        nodePole3.addNode(nodeService31).addNode(nodeService32).addNode(nodeService33);

        nodeService11.addNode(nodeHU111).addNode(nodeHU112).addNode(nodeHU113).addNode(nodeHU114);
        nodeService12.addNode(nodeHU121).addNode(nodeHU122).addNode(nodeHU123).addNode(nodeHU124);
        nodeService13.addNode(nodeHU131).addNode(nodeHU132).addNode(nodeHU133).addNode(nodeHU134);
        nodeService21.addNode(nodeHU211).addNode(nodeHU212).addNode(nodeHU213).addNode(nodeHU214);
        nodeService22.addNode(nodeHU221).addNode(nodeHU222).addNode(nodeHU223).addNode(nodeHU224);
        nodeService23.addNode(nodeHU231).addNode(nodeHU232).addNode(nodeHU233).addNode(nodeHU234);
        nodeService31.addNode(nodeHU311).addNode(nodeHU312).addNode(nodeHU313).addNode(nodeHU314);
        nodeService32.addNode(nodeHU321).addNode(nodeHU322).addNode(nodeHU323).addNode(nodeHU324);
        nodeService33.addNode(nodeHU331).addNode(nodeHU332).addNode(nodeHU333).addNode(nodeHU334);

        hospital.addNodePole(nodePole1);
        hospital.addNodePole(nodePole2);
        hospital.addNodePole(nodePole3);

        hospitalRepository.save(hospital);

        Staff staff1 = new Staff("ameline", "ameline", "Moreau", "Ameline", "0606060606", "123 fake street", Status.ADMIN);
        Staff staff2 = new Staff("charles", "charles", "Da Silva Costa", "Charles", "0606060607", "125 fake street", Status.MEDICAL);
        Staff staff3 = new Staff("jeanluc", "jeanluc", "Fernandes", "Jean-Luc", "0606060608", "127 fake street", Status.SECRETAIRE);
        staff1.setNode(nodePole1);
        staff2.setNode(nodePole1);
        staff3.setNode(nodePole1);
        staffRepository.save(staff1);
        staffRepository.save(staff2);
        staffRepository.save(staff3);

        //Patient 1
        AdminFile adminFile1 = generateAdminFile(
                "Szalony",
                "Raymond",
                "M",
                "1958-06-25",
                "Paris",
                "18 allée de Chartres",
                "93190",
                "Livry-Gargan",
                null,
                "France",
                "szalony.raymond@thisisafakeaddress.com",
                "0143320054",
                "0654789565",
                "0143320053",
                "Chercheur et maître de conférences"
        );


        MedicalInfo medicalInfo1 = new MedicalInfo();
        medicalInfo1.addInformations("allergies", "sel", "gluten", "lactose");
        medicalInfoRepository.save(medicalInfo1);

        //TODO remplacer le node ID (le 1) par une node cohérente après coup
        MedicalFile medicalFile1 = new MedicalFile(true, 1);
        medicalFileRepository.save(medicalFile1);

        Observation observation1 = new Observation(1,
                "Le patient a été admis pour une chute violente depuis une échelle."
        );
        observationRepository.save(observation1);

        //TODO remplacer le staff ID pour en dessous et au dessus en fonction des staffs existants
        Examen exam1 = new Examen(
                "Chute d'une échelle",
                "Radio crânienne",
                null,
                "Traumatisme crânien",
                "2018-02-16",
                1
        );
        examenRepository.save(exam1);

        Prescription prescription1 = new Prescription(
                "Morphine",
                "Injection par intra-veineuse, 1mL toutes les 10 minutes",
                "2018-02-16",
                "2018-02-19",
                1
        );
        prescriptionRepository.save(prescription1);

        medicalFile1.addObservation(observation1);
        medicalFile1.addExamen(exam1);
        medicalFile1.addPrescription(prescription1);

        Patient patient1 = new Patient(adminFile1, medicalInfo1);
        patient1.addMedicalFile(medicalFile1);

        patientRepository.save(patient1);

    }

    public AdminFile generateAdminFile(String lastName,
                                       String firstName,
                                       String gender,
                                       String birthDate,
                                       String birthPlace,
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
        socialID.append(postalCode);
        for(int i = 0 ; i < 5 ; i++)
            socialID.append(random.nextInt(10));

        AdminFile adminFile = new AdminFile(
                lastName,
                firstName,
                gender,
                birthDate,
                birthPlace,
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




}
