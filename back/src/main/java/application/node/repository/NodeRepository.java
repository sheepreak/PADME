package application.node.repository;

import application.node.domain.Node;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class NodeRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager entityManager;

    public List<Node> list(){
        List<Node> list = new ArrayList<>(entityManager
                .createNamedQuery(Node.FIND_ALL, Node.class)
                .getResultList());
        return list;
    }

    public Node find(Integer id){
        return entityManager.find(Node.class, id);
    }

    public Integer save(Node node) {
        entityManager.persist(node);
        return node.getId();
    }

    public void delete(Integer id) throws NoSuchEntityException{
        Node node = find(id);
        if(node!=null)
            entityManager.remove(node);
        else throw new NoSuchEntityException();
    }

    public void update(Node node){
        entityManager.merge(node);
    }

}
