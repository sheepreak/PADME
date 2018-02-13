package application.patient.repository;


import application.adminfile.repository.AdminFileRepository;
import application.medicalfile.repository.MedicalFileRepository;
import application.patient.domain.Patient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PatientRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    private MedicalFileRepository medicalFileRepository;

    private AdminFileRepository adminFileRepository;



    public List<Patient> getFiles() {
        return em.createNamedQuery(Patient.FIND_ALL, Patient.class).getResultList();
    }

    public Patient find(Integer id) {
        return em.find(Patient.class, id);
    }

    public void save(Patient file) {
        em.persist(file);
    }

    public void update(Patient file) {
        em.merge(file);
    }
}
