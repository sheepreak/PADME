package application.medicalfile.repository;


import application.medicalfile.domain.MedicalFile;
import application.node.domain.Node;

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

   public List<MedicalFile> findFilesByNode(Integer nodeId) {
        return em.createNamedQuery(MedicalFile.FIND_ALL_BY_NODE, MedicalFile.class)
                .setParameter("custNode", nodeId)
                .getResultList();
    }

    public void save(MedicalFile medicalFile){
        em.persist(medicalFile);
    }

    public void update(MedicalFile medicalFile) {
        em.merge(medicalFile);
    }

}
