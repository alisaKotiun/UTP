package Message;

import Sample.Requester;

import java.util.Random;

public class Request extends Message{

    private static Random random;
    private String string; //request

    public Request(Requester r) {
        super(r, Priority.getPriority());
        random = new Random();
        string = "request_" + Math.abs(random.nextInt());
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "Request" + "["+getId()+  "][" + string + "]";
    }
}
