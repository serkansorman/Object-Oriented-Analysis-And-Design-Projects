package Q2;

/**
 * Barrier class for thread synchronization
 */
public class Barrier
{
    /**
     * Wait inticated thread
     * @throws InterruptedException
     */
    public synchronized void block() throws InterruptedException
    {
        wait();
    }

    /**
     * Notify one of the waiting threads
     */
    public synchronized void release()
    {
        notify();
    }

    /**
     * Releases all waiting threads
     */
    public synchronized void releaseAll()
    {
        notifyAll();
    }

}