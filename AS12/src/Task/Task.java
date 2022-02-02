package Task;


import java.util.Random;

public class Task implements Runnable {

    private String name;
    private TStatus status;
    private TResult result;
    private int id;

    private static int count = 1;

    public Task(){
        id = count++;
        name = "Task" + id;
        status = TStatus.PENDING;
        result = null;
    }

    @Override
    public void run() {
        try {
            status = TStatus.RUNNING;
            System.out.println(this.toString());
            sleep();
            process();
            System.out.println(this.toString());
        } catch (Exception e) {
            status = TStatus.FAILED;
        }
    }

    private void process() throws Exception {
        if(success()) {
            result = new TResult();
            status = TStatus.ACCOMPLISHED;
        }
        else throw new Exception();
    }

    private boolean success(){
        Random random = new Random();
        int i = random.nextInt(100);
        return i%4 != 0;
    }

    public void sleep(){
        int sleep = 2000;
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return result == null? "" : result.toString();
    }

    public String getName() {
        return name;
    }

    public TStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return name + " " + status + " " + result + " ";
    }
}
