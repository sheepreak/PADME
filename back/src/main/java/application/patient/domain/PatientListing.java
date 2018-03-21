package application.patient.domain;

public class PatientListing {
    private Integer patientId;
    private String lastName;
    private String firstName;
    private String gender;
    private String birthDate;
    private String country;

    public PatientListing(Integer patientId, String firstName, String lastName, String gender, String birthDate,
                          String country){
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getCountry() {
        return country;
    }
}
