package Person;

public enum Degree {
    AS("Associate’s degree"),
    BA("Bachelor’s degree"),
    MA("Master’s degree"),
    DO ("Doctoral degree"),
    PF("Professional degree");

    private String name;

    Degree(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
