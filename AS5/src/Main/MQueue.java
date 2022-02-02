package Main;

import Message.Message;
import Message.Request;
import Message.Response;
import Sample.Requester;

import java.util.PriorityQueue;

public class MQueue {
    PriorityQueue<Message> myQueue;

    public MQueue(){
        myQueue  = new PriorityQueue<Message>();
    }

    public void addMessage(Message m){
        myQueue.add(m);
    }

    public synchronized Request receiveRequest(){
        Message message = myQueue.peek();
        if(message instanceof Request){
            return  (Request)myQueue.poll();
        }
        return null;
    }

    public synchronized Response receiveResponse(Requester requester){
        Message message = myQueue.peek();
        if(message instanceof Response && message.getRequester() == requester){
            return (Response)myQueue.poll();
        }
        return null;
    }
}
