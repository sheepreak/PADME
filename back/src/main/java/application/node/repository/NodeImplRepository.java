package application.node.repository;

import application.node.domain.NodeImpl;
import application.node.Node;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class NodeImplRepository {

    @PersistenceContext(unitName = "JPAPU")
    private EntityManager entityManager;

    public List<Node> list(){
        List<Node> list = new ArrayList<>(entityManager
                .createNamedQuery(NodeImpl.FIND_ALL, NodeImpl.class)
                .getResultList());
        return list;
    }

    public Node find(Long id){
        return entityManager.find(NodeImpl.class, id);
    }

    public Long save(Node node) {
        entityManager.persist(node);
        return node.getId();
    }

    public void delete(Long id) throws NoSuchEntityException{
        Node node = null;
        node = find(id);
        if(node!=null)
            entityManager.remove(node);
        else throw new NoSuchEntityException();
    }

    public void update(Node hospital){
        entityManager.merge(hospital);
    }

}