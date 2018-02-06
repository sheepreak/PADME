package application.examen.repository;

import application.examen.domain.Examen;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ExamenRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    public Examen find(Integer id){
        return em.find(Examen.class, id);
    }

    public void save(Examen examen) {
        em.persist(examen);
    }

    public java.util.List<Examen> getExams() {
        return em.createNamedQuery(Examen.FIND_ALL, Examen.class).getResultList();
    }

}
