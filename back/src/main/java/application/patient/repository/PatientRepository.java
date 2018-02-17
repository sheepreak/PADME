package application.patient.repository;


import application.Node;
import application.patient.domain.Patient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PatientRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    public List<Patient> getFiles() {
        return em.createNamedQuery(Patient.FIND_ALL, Patient.class).getResultList();
    }

    public Patient find(Integer id) {
        return em.find(Patient.class, id);
    }

    public List<Patient> getPatients(List<Node> leafs) {
        List<Patient> patients = new ArrayList<>();
        for(Node n : leafs) {
            em.find(Patient.class, n);
            patients.add(null);
        }
        return patients;
    }

    public void save(Patient file) {
        em.persist(file);
    }

    public void update(Patient file) {
        em.merge(file);
    }
}
