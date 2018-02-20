package application.staff;

public enum Status {

    ADMIN("admin"),
    SECRETAIRE("secretaire"),
    MEDICAL("medical");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
