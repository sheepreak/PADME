package application.adminfile.domain;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ext.JodaDeserializers;
import org.codehaus.jackson.map.ext.JodaSerializers;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class AdminFile {

    @Id
    @NotNull
    @GeneratedValue
    @Column
    private Integer id;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String birthDate;

    @NotNull
    @Column
    private String socialID;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column
    private String country;

    public AdminFile() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSocialID() {
        return socialID;
    }

    public void setSocialID(String socialID) {
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
}
