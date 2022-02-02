package Sample;

import Main.MQueue;
import Message.Request;
import Message.Response;

public class Requester extends Sample{
    public Requester(MQueue mq) {
        super(mq);
    }

    @Override
    public void run() {
        while (true){
            createAndAdd();
            boolean isNotDone = true;
            while (isNotDone){
                Response response = getMQueue().receiveResponse(this);
                if(response != null){
                    isReceived(response);
                    isNotDone = false;
                }
                sleep();
            }
        }
    }

    public void isReceived(Response response){
        System.out.println(response + " is done");
    }

    public void createAndAdd(){
        Request r = new Request(this);
        getMQueue().addMessage(r);
    }
}
