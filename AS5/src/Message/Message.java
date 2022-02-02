package Message;

import Sample.Requester;

import java.util.Date;

public class Message implements Comparable<Message> {
    private static int counter = 0;
    private int id;
    private Requester requester;
    private Priority priority;
    private Date date;

    public Message(Requester r, Priority p){
        id = counter++;
        requester = r;
        priority = p;
        date = new Date(System.currentTimeMillis());
    }

    public Date getDate() {
        return date;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    public Requester getRequester() {
        return requester;
    }


    @Override
    public int compareTo(Message message) {
        if (message == null) return 1;
        int result;
        result = priority.compareTo(message.getPriority());
        if(result != 0){ return result;}
        result = date.compareTo(message.getDate());
        if(result != 0){ return result;}
        return id - message.getId();
    }
}
