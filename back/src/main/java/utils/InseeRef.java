package utils;

public class InseeRef {

    private String city ="";
    private Integer postCode = -1;
    private String insee = "";

    public InseeRef(String city, Integer postCode, String insee){
        this.city = city;
        this.postCode = postCode;
        this.insee = insee;
    }

    public String getInsee() {
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
