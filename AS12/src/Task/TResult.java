package Task;

import java.util.Random;

public class TResult {
    private String string;

    public TResult(){
        string = "" + getTResult();
    }

    public int getTResult(){
        Random random = new Random();
        return random.nextInt();
    }

    @Override
    public String toString() {
        return "" + string;
    }
}
