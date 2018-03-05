package application.staff;

public enum Status {

    ADMIN("admin"),
    SECRETARY("secretary"),
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
