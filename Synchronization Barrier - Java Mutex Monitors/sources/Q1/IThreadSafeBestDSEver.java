package Q1;

/**
 * ThreadSafeBestDSEver interface
 */
public interface IThreadSafeBestDSEver{
    void insertThreadSafe(Object o);
    void removeThreadSafe(Object o);
    Object getThreadSafe(int index);
}
