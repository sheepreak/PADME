package application.staff.repository;

import application.staff.domain.Staff;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class StaffRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    public Staff find(Integer id) {
        return em.find(Staff.class, id);
    }

    public void save(Staff staff) {
        em.persist(staff);
    }

    public void update(Staff staff) {
        em.merge(staff);
    }

    public Staff tryConnection(String password, String login) {

        Query query = em.createQuery("select s from staff s where s.password=:password and s.login=:login");
        query.setParameter("password", password);
        query.setParameter("login", login);
        return (Staff)query.getSingleResult();

    }

}
