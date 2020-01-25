package Q2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DFT implements Runnable{

    /**
     * Provides synchronization to old version
     */
    private Barrier barrier;
    /**
     * Provides synchronization to new version
     */
    private DFTMonitor dftMonitor;
    /**
     * Includes all needed parameters
     */
    private Parameters parameters;
    private int threadId;
    /**
     * Indicates number of calculated parts
     */
    public static int completedPartial = 0;


    public DFT(DFTMonitor dftMonitor,Barrier barrier, Parameters parameters,int threadId) {
        this.dftMonitor = dftMonitor;
        this.barrier = barrier;
        this.parameters = parameters;
        this.threadId = threadId;
    }

    /**
     * Provides synchronization with wait and notifyAll then calculate DFT
     * @throws InterruptedException
     */
    private void runWithOldVersion() throws InterruptedException {

        summation();
        System.out.println("Thread "+threadId+" completed its summation");
        synchronized (DFT.class) {
            ++completedPartial;
        }

        if(completedPartial == 4)
            barrier.releaseAll();
        else
            barrier.block();

        calculateDFT();
        System.out.println("Thread "+threadId+" completed its DFT calculation");
    }

    /**
     * Provides synchronization with monitors then calculate DFT
     * @throws InterruptedException
     */
    private void runWithNewVersion() throws InterruptedException {

        summation();
        System.out.println("Thread "+threadId+" completed its summation");

        dftMonitor.mutex.lock();

        ++completedPartial;

        while (completedPartial < 4){
            dftMonitor.summationCompleted.await();
        }

        dftMonitor.summationCompleted.signalAll();

        dftMonitor.mutex.unlock();

        calculateDFT();
        System.out.println("Thread "+threadId+" completed its DFT calculation");
    }

    /**
     * Calculate sum of one part of matrix
     */
    private void summation(){

        int n = parameters.getN();
        int endRow = parameters.getStartRow() + n/2;
        int endColumn = parameters.getStartColumn() + n/2;

        for(int i=parameters.getStartRow(); i<endRow; ++i){
            for(int j=parameters.getStartColumn(); j<endColumn; ++j){
                parameters.getSumResult()[i][j] = parameters.getM1()[i][j].plus(parameters.getM2()[i][j]);
            }
        }

    }

    /**
     * Calculate DFT of one part of matrix
     */
    private void calculateDFT() {

        int n = parameters.getN();
        int endRow = parameters.getStartRow() + n/2;
        int endColumn = parameters.getStartColumn() + n/2;


        // Two outer loops iterate on output data.
        for (int yWave = parameters.getStartRow(); yWave < endRow; yWave++) {
            for (int xWave = parameters.getStartColumn(); xWave < endColumn; xWave++) {
                double real = 0, img = 0;
                for (int ySpace = 0; ySpace < n; ySpace++) {
                    for (int xSpace = 0; xSpace < n; xSpace++) {

                        double val = 2
                                * Math.PI
                                * ((1.0 * xWave * xSpace / n) + (1.0
                                * yWave * ySpace / n));

                        real += (parameters.getSumResult()[ySpace][xSpace].re * Math
                                .cos(val))
                                / Math.sqrt(n * n);


                        img -= (parameters.getSumResult()[ySpace][xSpace].re * Math
                                .sin(val))
                                / Math.sqrt(n * n);

                    }

                }

                if(parameters.getDFTResult()[yWave][xWave] == null)
                    parameters.getDFTResult()[yWave][xWave] = new Complex();

                parameters.getDFTResult()[yWave][xWave].re = real * 2;
                parameters.getDFTResult()[yWave][xWave].im = img * 2;
            }
        }
    }



    @Override
    public void run() {

        try {
            runWithNewVersion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
