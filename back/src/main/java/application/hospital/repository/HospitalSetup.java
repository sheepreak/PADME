package application.hospital.repository;

import application.hospital.domain.Hospital;
import application.node.NodeLevel;
import application.node.domain.Node;
import application.node.repository.NodeRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class HospitalSetup {

    @EJB
    private HospitalRepository hospitalRepository;

    @EJB
    private NodeRepository nodeRepository;

    @PostConstruct
    private void createData() {

        Hospital hospital = new Hospital("Mamen Hospital", "Pays", "Address");
        Node node = new Node("Specialité", NodeLevel.pole);
        Node node2 = new Node("Specialité", NodeLevel.service);
        Node node3 = new Node("Specialité", NodeLevel.service);
        Node node4 = new Node("Specialité", NodeLevel.hospitalUnit);
        node.addNode(node2).addNode(node3);
        node2.addNode(node4);
        hospital.addNodePole(node);
        hospitalRepository.save(hospital);

    }




}
