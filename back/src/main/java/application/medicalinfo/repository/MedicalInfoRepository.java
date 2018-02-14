package application.medicalinfo.repository;

import application.medicalinfo.domain.MedicalInfo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MedicalInfoRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;

    public MedicalInfo getMedicalInfo(Integer id){
        return em.find(MedicalInfo.class, id);
    }

    public void save(MedicalInfo medicalInfo){
        em.persist(medicalInfo);
    }

    public void update(MedicalInfo medicalInfo) {
        em.merge(medicalInfo);
    }

}
