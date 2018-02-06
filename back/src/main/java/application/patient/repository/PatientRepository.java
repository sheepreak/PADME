package application.patient.repository;


import application.patient.domain.Patient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PatientRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

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
