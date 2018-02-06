package application.medicalfile.repository;


import application.medicalfile.domain.MedicalFile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MedicalFileRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager em;


    public List<MedicalFile> getFiles() {
        return em.createNamedQuery(MedicalFile.FIND_ALL, MedicalFile.class).getResultList();
    }

    public MedicalFile getFile(Integer id){
        return em.find(MedicalFile.class, id);
    }

    public void save(MedicalFile medicalFile){
        em.persist(medicalFile);
    }

    public void update(MedicalFile medicalFile) {
        em.merge(medicalFile);
    }

}
