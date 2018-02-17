package application.hospital.repository;

import application.hospital.domain.Hospital;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class HospitalRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager entityManager;

    public List<Hospital> list(){
        List<Hospital> list = new ArrayList<>(entityManager
                .createNamedQuery(Hospital.FIND_ALL, Hospital.class)
                .getResultList());
        return list;
    }

    public Hospital find(Long id){
        return entityManager.find(Hospital.class, id);
    }

    public Long save(Hospital hospital) {
        entityManager.persist(hospital);
        return hospital.getId();
    }

    public void delete(Long id) throws NoSuchEntityException{
        Hospital hospital = find(id);
        if(hospital != null)
            entityManager.remove(hospital);
        else throw new NoSuchEntityException();
    }

    public void update(Hospital hospital){
        entityManager.merge(hospital);
    }
}
