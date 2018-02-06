package application.prescription.repository;

import application.prescription.domain.Prescription;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PrescriptionRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    public Prescription find(Integer id){
        return em.find(Prescription.class, id);
    }

    public void save(Prescription prescription) {
        em.persist(prescription);
    }
}
