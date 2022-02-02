package Task;

import java.util.ArrayList;
import java.util.List;

public class TQueue {
    private int size;
    private List<Task> processing;
    private List<Task> waiting;

    public TQueue(int s){
        size = s;
        processing = new ArrayList<>();
        waiting = new ArrayList<>();
        waiting();
    }

    public synchronized void process(Task task){
        if(check()){
            processing.add(task);
            task.run();
        }
        else{
            waiting.add(task);
        }
    }

    private synchronized boolean check(){
        if(processing.size() < size) return true;
        for (Task t: processing) {
            if(t.getStatus() == TStatus.FAILED || t.getStatus() == TStatus.ACCOMPLISHED){
                processing.remove(t);
                return true;
            }
        }
        return false;
    }

    private synchronized void waiting(){
        new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(10);
                    if(!waiting.isEmpty()) {
                        if (check()) {
                            Task t = waiting.get(0);
                                process(t);
                                waiting.remove(0);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Task get(int i){
        return processing.get(i);
    }

}
