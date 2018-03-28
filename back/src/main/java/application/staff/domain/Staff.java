package application.staff.domain;

import application.node.domain.Node;
import application.staff.Status;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.crypto.Cipher.ENCRYPT_MODE;

@Entity
public class Staff {

    private static String KEY = "Password Key";

    @Id
    @NotNull
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
    @Pattern(regexp="^(0|\\+33)[1-9]([-. ]?[0-9]{2}){4}$")
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

    private String token;

    public Staff() {
    }

    public Staff(String login, String password, String lastName, String firstName, String phone, String address, Status status) {

        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.password = encodePassword(password);

    }

    private String encodePassword(String password) {
        try {
            Key key = new SecretKeySpec(KEY.getBytes("ISO-8859-2"), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(ENCRYPT_MODE, key);
            return new String(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
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
        this.password = encodePassword(password);
    }

    public void setPasswordWithoutEncode(String password) {
        this.password = password;
    }

    public List<Node> leaves() {

        if(node == null)
            return Collections.EMPTY_LIST;

        if(node.getSubNodes().size() == 0) {
            List<Node> leaves = new ArrayList<>();
            leaves.add(node);
            return leaves;
        }

        return leavesHelper(this.node);

    }

    private List<Node> leavesHelper(Node node){

        List<Node> subNodes;
        List<Node> sons = new ArrayList<>();
        if((subNodes = node.getSubNodes()) == null)
            return Collections.EMPTY_LIST;

        for(Node n : subNodes) {
            if(n.getSubNodes().size() == 0)
                sons.add(n);
            else
                sons.addAll(leavesHelper(n));
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
                .append("\"id\" : \"").append(id).append("\",")
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
