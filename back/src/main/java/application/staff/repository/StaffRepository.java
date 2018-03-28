package application.staff.repository;

import application.staff.domain.Staff;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.security.Key;
import java.util.List;

import static javax.crypto.Cipher.ENCRYPT_MODE;

@Stateless
public class StaffRepository {

    private static String KEY = "Password Key";

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

        try {
            return (Staff) em.createQuery("SELECT s FROM Staff s WHERE s.password LIKE :password AND s.login LIKE :login")
                    .setParameter("password", encodePassword(password))
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

    public List<Staff> getStaffsWithPagination(int page, int size) {
        List<Staff> staffs = em.createQuery("SELECT s FROM Staff s LIMIT 10").setMaxResults(size)
                /*.setParameter("offset",size*page)
                .setParameter("limit",size)*/
                .getResultList();
        return staffs;
    }

    public Staff findByToken(String token) {

        try {
            Staff staff = (Staff) em.createQuery("SELECT s FROM Staff s WHERE s.token LIKE :token").setParameter("token", token).getSingleResult();
            return staff;
        }
        catch(Exception e) {
            return null;
        }

    }

    private String encodePassword(String password) {
        try {
            Key key = new SecretKeySpec(KEY.getBytes("ISO-8859-2"), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(ENCRYPT_MODE, key);
            return new String(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
}
