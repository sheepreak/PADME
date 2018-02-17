package application.staff.domain;

import application.node.domain.Node;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Entity
public class Staff {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @NotNull
    @Column
    private String lastName;    // nom du membre du Staff

    @NotNull
    @Column
    private String token;    // nom du membre du Staff

    @NotNull
    @Column
    private String firstName;   //prenom du membre du Staff

    @Column
    private String phone;   // numéro de téléphone du membre du Staff

    @Column
    private String address;   // adresse du membre du Staff

    @NotNull
    @Column
    private String status; // indique le poste du membre du Staff

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "NODE_ID")
    private Node node;// contient le noeud auquel le membre du Staff est rattaché

    public Staff() {
    }


    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public List<Node> getLeaves(){
        return getLeavesHelper(this.node);
    }

    private List<Node> getLeavesHelper(Node node){

        List<Node> sons;
        if((sons = node.getSubNodes()) == null)
            return Collections.EMPTY_LIST;

        for(Node n : sons) {
            if(n.getSubNodes() == Collections.EMPTY_LIST)
                sons.add(n);
            else
                sons.addAll(getLeavesHelper(n));
        }
        return sons;
    }

}
