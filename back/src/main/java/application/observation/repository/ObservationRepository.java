package application.observation.repository;

import application.observation.domain.Observation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ObservationRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    public Observation find(Integer id){
        return em.find(Observation.class, id);
    }

    public void save(Observation observation) {
        em.persist(observation);
    }

}
