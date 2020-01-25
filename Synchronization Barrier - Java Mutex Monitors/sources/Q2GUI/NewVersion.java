package sample.Q2;

import static sample.Q2.DFT.completedPartial;

/**
 * Provides synchronization using monitors
 */
public class NewVersion implements SolutionVersion {
    private DFTMonitor dftMonitor;

    public NewVersion() {
        dftMonitor = new DFTMonitor();
        System.out.println(this.getClass().getName());
    }

    @Override
    public void solution() throws InterruptedException {
        dftMonitor.mutex.lock();

        ++completedPartial;

        while (completedPartial < Parameters.numberOfThread){
            dftMonitor.summationCompleted.await();
        }

        dftMonitor.summationCompleted.signalAll();

        dftMonitor.mutex.unlock();

    }
}
