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
import utils.Address;
import utils.Parse;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

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
        Node nodeService14 = new Node("Chirurgie pédiatrique", NodeLevel.service);
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
        Node nodeHU141 = new Node("Unité 1", NodeLevel.hospitalUnit);
        Node nodeHU142 = new Node("Unité 2", NodeLevel.hospitalUnit);
        Node nodeHU143 = new Node("Unité 3", NodeLevel.hospitalUnit);
        Node nodeHU144 = new Node("Unité 4", NodeLevel.hospitalUnit);
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

        hospital.addNodePole(nodePole1);
        hospital.addNodePole(nodePole2);
        hospital.addNodePole(nodePole3);

        hospitalRepository.save(hospital);

        //user accounts for testing
        Staff staff1 = new Staff("ameline", "ameline", "Moreau", "Ameline", "0606060606", "123 fake street", Status.ADMIN);
        Staff staff2 = new Staff("charles", "charles", "Da Silva Costa", "Charles", "0606060607", "125 fake street", Status.DOCTOR);
        Staff staff3 = new Staff("jeanluc", "jeanluc", "Fernandes", "Jean-Luc", "0606060608", "127 fake street", Status.SECRETAIRE);
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
                "Livry-Gargan",
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
                "Injection par intra-veineuse, 1mL toutes les 10 minutes",
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

        generateRandomPatient(292);

    }

    private AdminFile generateAdminFile(String lastName,
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

    private String generatePhoneNumber(String indicatif){
        StringBuilder phone = new StringBuilder().append(indicatif);
        for(int i = 0; i<8; i++)
            phone.append(rand.nextInt(10));
        return phone.toString();
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
        List<String> jobs = new ArrayList<>();
        jobs.add("Coursier");
        jobs.add("Vendeur Immobilier");
        jobs.add("Gestionnaire de fonds");
        jobs.add("Sans emploie");
        jobs.add("Paintre");
        jobs.add("Vendeur");
        jobs.add("Courtier");
        jobs.add("Professeur");
        jobs.add("Architecte");
        List<AdminFile> list = new ArrayList<>();
        try {
            List<String> dataPrenom = Parse.parseFileToString(Paths.get("/home/dev/Dropbox_Host/padme_project/PADME-Project/back/src/main/resources/dataForSetup/Prenoms.csv"));
            HashMap<String, Boolean> prenoms = Parse.parseFirstname(dataPrenom);


            List<String> dataAddress = Parse.parseFileToString(Paths.get("/home/dev/Dropbox_Host/padme_project/PADME-Project/back/src/main/resources/dataForSetup/les_bureaux_de_poste_et_agences_postales_en_idf.csv"));
            List<Address> addressSamples = Parse.parseSampleAddress(dataAddress);

            List<String> firstNames = new ArrayList<>(prenoms.keySet());
            for(int i= 0; i < nb; i++) {
                String firstName = firstNames.get(rand.nextInt(firstNames.size()));
                String gender;
                if (prenoms.get(firstName))
                    gender = "M";
                else gender = "F";
                int birthYear = (2017 - Math.abs(rand.nextInt(100)));
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
                        birthAddress.getCity(),
                        address.getAddress(),
                        address.getPostCode().toString(),
                        address.getCity(),
                        null,
                        address.getCountry(),
                        lastName + "." + firstName + emailBox.get(rand.nextInt(emailBox.size())),
                        generatePhoneNumber("01"),
                        generatePhoneNumber("06"),
                        generatePhoneNumber("01"),
                        jobs.get(rand.nextInt(jobs.size()))
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
