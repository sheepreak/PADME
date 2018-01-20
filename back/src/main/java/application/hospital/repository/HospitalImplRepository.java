package application.hospital.repository;

import application.hospital.Hospital;
import application.hospital.domain.HospitalImpl;

import javax.ejb.NoSuchEntityException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HospitalImplRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<HospitalImpl> list(){
        List<HospitalImpl> list = new ArrayList<>(entityManager
                .createNamedQuery(HospitalImpl.FIND_ALL, HospitalImpl.class)
                .getResultList());
        return list;
    }

    public HospitalImpl find(Long id){
        return entityManager.find(HospitalImpl.class, id);
    }

    public Long save(HospitalImpl hospital) {
        entityManager.persist(hospital);
        return hospital.getId();
    }

    public void delete(Long id) throws NoSuchEntityException{
        Hospital hospital = null;
        hospital = find(id);
        if(hospital!=null)
            entityManager.remove(hospital);
        else throw new NoSuchEntityException();
    }

    public void update(HospitalImpl hospital){
        entityManager.merge(hospital);
    }
}
