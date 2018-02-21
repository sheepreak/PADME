package application.adminfile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class AdminFile {

    @Id
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
    private String gender;

    @NotNull
    @Column
    private String birthDate;

    @NotNull
    @Column
    private String birthPlace;

    @NotNull
    @Column
    private String socialID;

    @Column
    private String address;

    @Column
    private String postalCode;

    @Column
    private String city;

    @Column
    private String addressComplement;

    @NotNull
    @Column
    private String country;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String cellPhone;

    @Column
    private String fax;

    @Column
    private String profession;

    public AdminFile() { }

    public AdminFile(String lastName, String firstName, String gender, String birthDate, String birthPlace, String socialID, String address, String postalCode, String city, String addressComplement, String country, String email, String phone, String cellPhone, String fax, String profession) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.socialID = socialID;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.addressComplement = addressComplement;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.fax = fax;
        this.profession = profession;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
