package Main;

import Message.Request;
import Message.Response;
import Sample.Requester;
import Sample.Sample;
import Sample.Service;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MQueue myQueue = new MQueue();
        List<Requester> requesterList = requesterList(myQueue);
        List<Service> serviceList = serviceList(myQueue);
        start(requesterList);
        start(serviceList);
    }

    public static List<Requester> requesterList(MQueue mq){
        List<Requester> list = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Requester requester = new Requester(mq);
            list.add(requester);
        }
        return list;
    }

    public static List<Service> serviceList(MQueue mq){
        List<Service> list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            Service service = new Service(mq);
            list.add(service);
        }
        return list;
    }

    public static <Sam extends Sample> void start(List<Sam> list){
        for (Sam l : list) {
            new Thread(l).start();
        }
    }
}
