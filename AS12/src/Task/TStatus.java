package Task;

public enum TStatus {
    PENDING ("Pending"),
    RUNNING ("Running"),
    ACCOMPLISHED ("Accomplished"),
    FAILED ("Failed");

    String name;
    TStatus(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}
