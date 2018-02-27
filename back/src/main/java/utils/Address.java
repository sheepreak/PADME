package utils;

public class Address {
    private String address = "";
    private String city ="";
    private Integer postCode = -1;
    private String country = "";
    private Integer insee = -1;
    public Address(String address, String city, Integer postCode, String country, Integer insee){
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.insee = insee;
    }

    public Integer getInsee() {
        return insee;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return address+ ","+ postCode + " " + city +  ","+country;
    }
}
