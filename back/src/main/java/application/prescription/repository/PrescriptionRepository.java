package application.prescription.repository;

import application.prescription.domain.Prescription;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PrescriptionRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    public Prescription find(Integer id){
        return em.find(Prescription.class, id);
    }

    public void save(Prescription prescription) {
        em.persist(prescription);
    }

    public void update(Prescription prescription) {
        em.merge(prescription);
    }
}
