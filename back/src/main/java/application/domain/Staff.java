package application.domain;
import java.security.SecureRandom;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

public class Staff {


    @Id
    @GeneratedValue
    @NotNull
    @Column(name= "id")
    private Integer id;

    @NotNull
    @Column(name= "lastname")
    private String lastname;    // nom du membre du Staff

    @NotNull
    @Column(name= "token")
    private String token;    // nom du membre du Staff
    SecureRandom random = new SecureRandom();


    @NotNull
    @Column(name="firstname")
    private String firstname;   //prenom du membre du Staff

    @Column(name="phone")
    private String phone;   // numéro de téléphone du membre du Staff

    @Column(name="adress")
    private String adress;   // adresse du membre du Staff


    @NotNull
    @Column(name="status")
    private String status; // indique le poste du membre du Staff

    @OneToMany
    private final Noeud ;// contient le noeud auquel le membre du Staff est rattaché

    @OneToMany(mappedBy ="patient")
    private final List<Patient>; //contient la liste des patients dont s’occupe le membre du Staff


}
