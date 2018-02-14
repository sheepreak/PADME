package application.adminfile.repository;

import application.adminfile.domain.AdminFile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdminFileRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    public AdminFile find(Integer id) {
        return em.find(AdminFile.class, id);
    }

    public void save(AdminFile file) {
        em.persist(file);
    }

    public void update(AdminFile file) {
        em.merge(file);
    }
}
