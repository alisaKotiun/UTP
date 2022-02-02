package Sample;

import Main.MQueue;

import java.util.Map;

public abstract class Sample implements Runnable{
    private static int counter = 0;
    private int id;
    private MQueue mQueue;

    public Sample(MQueue mq){
        mQueue = mq;
        id = counter++;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" + id + "]";
    }

    public MQueue getMQueue() {
        return mQueue;
    }

    public void sleep(){
         int sleep = 2000;
         try {
             Thread.sleep(sleep);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
    }
}
