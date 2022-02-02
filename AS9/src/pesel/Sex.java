package pesel;

public enum Sex {
    M("male"),
    F("female");

    private String name;

    Sex(String p) { name = p;}

    @Override
    public String toString() {
        return name;
    }
}
