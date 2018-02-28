package application.staff.repository;

import application.staff.domain.Staff;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

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

    public void updateDataStaff(Staff staff) {
        em.createQuery("Update Staff set address =:adress ,lastName=:lastName," +
                "firstName=:firstName, phone=:phone  where id=:id")
                .setParameter("adress", staff.getAddress())
                .setParameter("lastName", staff.getLastName())
                .setParameter("firstName", staff.getFirstName())
                .setParameter("phone", staff.getPhone())
                .setParameter("id", staff.getId())
                .executeUpdate();
    }

    public Staff tryConnection(String password, String login) throws NoResultException{
        try {
            return (Staff) em.createQuery("SELECT s FROM Staff s WHERE s.password LIKE :password AND s.login LIKE :login")
                    .setParameter("password", password)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public List<Staff> getStaffs() {
        List<Staff> staffs = em.createQuery("SELECT s FROM Staff s").getResultList();
        return staffs;
    }

}
