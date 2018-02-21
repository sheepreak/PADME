package application.staff.domain;

import application.node.domain.Node;
import application.staff.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Staff {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @NotNull
    @Column(unique=true)
    private String login;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String lastName;    // nom du membre du Staff

    @NotNull
    @Column
    private String firstName;   //prenom du membre du Staff

    @Column
    private String phone;   // numéro de téléphone du membre du Staff

    @Column
    private String address;   // adresse du membre du Staff

    @NotNull
    @Column
    private Status status; // indique le poste du membre du Staff

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "NODE_ID")
    private Node node; // contient le noeud auquel le membre du Staff est rattaché

    private String token; // nom du membre du Staff

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Node> leaves(){
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

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String staffConnectionInfo() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ")
                .append("\"id\" : \"").append(lastName).append("\",")
                .append("\"login\" : \"").append(login).append("\",")
                .append("\"lastName\" : \"").append(lastName).append("\",")
                .append("\"firstName\" : \"").append(firstName).append("\",")
                .append("\"phone\" : \"").append(phone).append("\",")
                .append("\"address\" : \"").append(address).append("\",")
                .append("\"status\" : \"").append(status).append("\",")
                .append("\"token\" : \"").append(token).append("\",")
                .append("\"address\" : \"").append(address).append("\"")
                .append("}");
        return stringBuilder.toString();
    }

}
