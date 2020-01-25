package Q1;

/**
 * Adapter class of ThreadSafeBestDSEver
 */
public class ThreadSafeBestDSEverAdapter implements IBestDSEver {
    ThreadSafeBestDSEver threadSafeBestDSEver;

    public ThreadSafeBestDSEverAdapter(ThreadSafeBestDSEver threadSafeBestDSEver) {
        this.threadSafeBestDSEver = threadSafeBestDSEver;
    }

    @Override
    public void insert(Object o) {
        threadSafeBestDSEver.insertThreadSafe(o);
    }

    @Override
    public void remove(Object o) {
        threadSafeBestDSEver.removeThreadSafe(o);
    }

    @Override
    public Object get(int index) {
        return threadSafeBestDSEver.getThreadSafe(index);
    }
}
