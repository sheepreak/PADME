package application.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import application.hospital.Hospital;
import application.node.Node;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static application.hospital.domain.HospitalImpl.FIND_ALL;

@Table(name="Hospital")
@Entity
@NamedQuery(name=FIND_ALL, query="SELECT h FROM Hospital h ORDER BY h.name ASC")
public class HospitalImpl implements Hospital {

    public static final String FIND_ALL = "Hospital.findAllHospitals";

    @Id
    @GeneratedValue
    private Long id;

    @Size(min=2, max=20)
    @NotNull
    @Column(nullable=false)
    private String name;

    @Size(min=2, max=20)
    @NotNull
    @Column(nullable=false)
    private String country;

    @Size(min = 2, max = 20)
    @NotNull
    @Column(nullable = false)
    private String address;

    @OneToMany
    private List<Node> hierarchy;

    public HospitalImpl(String name, String country, String address) {
        this.name = Objects.requireNonNull(name);
        this.country = Objects.requireNonNull(country);
        this.address = Objects.requireNonNull(address);
        hierarchy = new ArrayList<>();
    }

    public HospitalImpl() {
    }

    @Override
    public boolean addNodePole(Node nodePole) {
        Objects.requireNonNull(nodePole);
        if(!nodePole.isNodePole())
            throw new IllegalArgumentException();
        return hierarchy.add(nodePole);
    }

    @Override
    public void initHierarchy(Path path) {
        Objects.requireNonNull(path);
        if(!hierarchy.isEmpty())
            return;
        File file = new File(path.toUri());
        if(file.isDirectory()){
            String message = "Target path("+file.getAbsolutePath()+") is a directory and not a json file";
            System.err.println(message);//Logger.error(message)
            throw new IllegalArgumentException(message);
        }
        //TODO parse the json file

    }

    @Override
    public Node getPosition(Node node) {
        for (Node nodePole: hierarchy) {
            if (nodePole.equals(node))
                return nodePole;
            if (nodePole.getLevel().equals(node.getLevel()))
                continue;
            if (!nodePole.getSpeciality().equals(node.getSpeciality()))
                continue;
            if(nodePole.getSubNodes() == null)
                continue;
            for (Node nodeService: nodePole.getSubNodes()) {
               if (nodeService.equals(node))
                   return nodeService;
               if(nodeService.getSubNodes() == null)
                   continue;
               for (Node nodeHospUnit: nodeService.getSubNodes()) {
                   if (nodeHospUnit.equals(node))
                       return nodeHospUnit;
                   if(nodeHospUnit.getSubNodes() == null)
                       continue;
                   for (Node nodeHealthCareUnit: nodeHospUnit.getSubNodes()) {
                         if(nodeHealthCareUnit.equals(node))
                             return nodeHealthCareUnit;
                   }
               }
            }
        }
        return null;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public List<Node> getHierarchy() {
        return hierarchy;
    }
}
