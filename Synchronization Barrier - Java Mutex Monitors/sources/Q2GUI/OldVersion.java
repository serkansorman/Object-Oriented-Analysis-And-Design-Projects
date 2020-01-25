package sample.Q2;

import static sample.Q2.DFT.completedPartial;

/**
 * Provides synchronization using wait,notify
 */
public class OldVersion implements SolutionVersion {

    private Barrier barrier;

    public OldVersion() {
        barrier = new Barrier();
        System.out.println(this.getClass().getName());
    }

    @Override
    public void solution() throws InterruptedException {
        synchronized (DFT.class) {
            ++completedPartial;
        }

        if(completedPartial == Parameters.numberOfThread)
            barrier.releaseAll();
        else
            barrier.block();

    }
}
