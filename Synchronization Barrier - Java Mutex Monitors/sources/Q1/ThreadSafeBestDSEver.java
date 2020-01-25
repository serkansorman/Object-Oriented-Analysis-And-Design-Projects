package Q1;

public class ThreadSafeBestDSEver implements IThreadSafeBestDSEver{

    /**
     * Thread safe insert method
     * @param o
     */
    @Override
    public synchronized void insertThreadSafe(Object o) {
        System.out.println("ThreadSafeBestDSEver insert thread-safe");
    }

    /**
     * Thread safe remove method
     * @param o
     */
    @Override
    public synchronized void removeThreadSafe(Object o) {
        System.out.println("ThreadSafeBestDSEver remove thread-safe");
    }

    /**
     * Thread safe get method
     * @param index
     * @return
     */
    @Override
    public synchronized Object getThreadSafe(int index) {
        System.out.println("ThreadSafeBestDSEver get thread-safe");
        return null;
    }
}
