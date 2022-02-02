package Message;

import java.util.Random;

public enum Priority {
    High("high"),
    Normal("normal"),
    Low("low");

    private String name;

    Priority(String s) {
        name = s;
    }

    static Priority getPriority(){
        Random random = new Random();
        Priority[] priorities = Priority.values();
        int i = random.nextInt(priorities.length);
        return priorities[i];
    }

    @Override
    public String toString() {
        return name;
    }
}
