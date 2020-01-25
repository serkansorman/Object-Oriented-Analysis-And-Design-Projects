package Q2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Monitor that provides mutex and cond variable
 */
public class DFTMonitor {

    final Lock mutex;
    final Condition summationCompleted;

    public DFTMonitor() {
        mutex = new ReentrantLock();
        summationCompleted = mutex.newCondition();
    }
}
