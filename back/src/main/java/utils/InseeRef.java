package utils;

public class InseeRef {

    private String city ="";
    private Integer postCode = -1;
    private Integer insee = -1;

    public InseeRef(String city, Integer postCode, Integer insee){
        this.city = city;
        this.postCode = postCode;
        this.insee = insee;
    }

    public Integer getInsee() {
        return insee;
    }

    public Integer getPostCode() {
        return postCode;
    }


    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return insee+ ":"+ postCode + " " + city;
    }
}
