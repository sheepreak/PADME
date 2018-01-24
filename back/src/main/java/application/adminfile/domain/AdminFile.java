package application.adminfile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class AdminFile {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    private String firstName;

    @NotNull
    @Column
    private Date birthDate;

    @NotNull
    @Column
    private Integer socialID;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column
    private String country;


    public AdminFile(String lastName, String firstName, Date birthDate, Integer socialID, String address, String country){
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.socialID = socialID;
        this.address = address;
        this.country = country;
    }

    public AdminFile(){}

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "test";
    }

    public Integer getSocialID() {
        return socialID;
    }

    public void setSocialID(Integer socialID) {
        this.socialID = socialID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
