package sample.Q2;

public class Barrier
{
    public synchronized void block() throws InterruptedException
    {
        wait();
    }

    public synchronized void release()
    {
        notify();
    }

    public synchronized void releaseAll()
    {
        notifyAll();
    }

}