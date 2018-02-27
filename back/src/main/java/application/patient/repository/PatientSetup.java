package application.patient.repository;

import application.adminfile.domain.AdminFile;
import application.adminfile.repository.AdminFileRepository;
import application.examen.domain.Examen;
import application.examen.repository.ExamenRepository;
import application.medicalfile.domain.MedicalFile;
import application.medicalfile.repository.MedicalFileRepository;
import application.medicalinfo.domain.MedicalInfo;
import application.medicalinfo.repository.MedicalInfoRepository;
import application.observation.domain.Observation;
import application.observation.repository.ObservationRepository;
import application.patient.domain.Patient;
import application.prescription.domain.Prescription;
import application.prescription.repository.PrescriptionRepository;
import utils.Address;
import utils.InseeRef;
import utils.Parse;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Startup
@Singleton
public class PatientSetup {

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




    @PostConstruct
    private void createData() {
        AdminFile adminFile1 = new AdminFile(
                "Zipstein",
                "Marc",
                "M",
                "1958-06-25",
                "Paris",
                "158067511425801",
                "18 allée de Chartres",
                "93190",
                "Livry-Gargan",
                null,
                "France",
                "zipstein.marc@thisisafakeaddress.com",
                "0143320054",
                "0654789565",
                "0143320053",
                "Chercheur et maître de conférences"
        );
        adminFileRepository.save(adminFile1);


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

        initPatients();
    }
}
