package application;

public class Staff {

    private String nom_staff;
    private String prenom_staff;
    private Noeud noeud;
    private Statut statut;

    private Patient patient;

    public String getNom_staff() {
        return nom_staff;
    }

    public void setNom_staff(String nom_staff) {
        this.nom_staff = nom_staff;
    }

    public String getPrenom_staff() {
        return prenom_staff;
    }

    public void setPrenom_staff(String prenom_staff) {
        this.prenom_staff = prenom_staff;
    }

    public Noeud getNoeud() {
        return noeud;
    }

    public void setNoeud(Noeud noeud) {
        this.noeud = noeud;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
