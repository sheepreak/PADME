package application.staff;

public enum Status {

    ADMIN("admin"),
    SECRETAIRE("secretaire"),
    DOCTOR("doctor"),
    NURSE("nurse");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
