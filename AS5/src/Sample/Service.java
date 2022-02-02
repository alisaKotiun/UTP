package Sample;

import Main.MQueue;
import Message.Request;
import Message.Response;

public class Service extends Sample{
    public Service(MQueue mq){
        super(mq);
    }

    @Override
    public void run() {
        while (true){
            Request request = getMQueue().receiveRequest();
            if(request != null){
                createAndAdd(request);
            }
        }
    }

    public void createAndAdd(Request request){
        System.out.println(request + " is accepted");
        Response r = new Response(request);
        getMQueue().addMessage(r);
    }
}
