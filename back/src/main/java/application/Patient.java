package application;

public class Patient
{
    private int id_patient;
    private String nom_patient;
    private String prenom_patient;
    private Staff id_staff;

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getNom_patient() {
        return nom_patient;
    }

    public void setNom_patient(String nom_patient) {
        this.nom_patient = nom_patient;
    }

    public String getPrenom_patient() {
        return prenom_patient;
    }

    public void setPrenom_patient(String prenom_patient) {
        this.prenom_patient = prenom_patient;
    }

    public Staff getId_staff() {
        return id_staff;
    }

    public void setId_staff(Staff id_staff) {
        this.id_staff = id_staff;
    }

}
