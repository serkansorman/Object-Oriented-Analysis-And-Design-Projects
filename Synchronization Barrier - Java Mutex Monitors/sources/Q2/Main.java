package Q2;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    static void printMatrix(Complex[][] m){
        for (Complex[] complexes : m) {
            for (int j = 0; j < m[0].length; ++j) {
                System.out.print(complexes[j] + " ");
            }
            System.out.println();
        }
    }

    static void summation(Complex[][] sumResult,Complex[][] m1,Complex[][] m2){

        for(int i=0; i<m1.length; ++i){
            for(int j=0; j<m1[0].length; ++j){
                sumResult[i][j] = m1[i][j].plus(m2[i][j]);
            }
        }

    }


    static Complex[][] calculateDFT(Complex[][] inputData) {
        int height = inputData.length;
        int width = inputData[0].length;
        Complex[][] result = new Complex[height][width];

        // Two outer loops iterate on output data.
        for (int yWave = 0; yWave < height; yWave++) {
            for (int xWave = 0; xWave < width; xWave++) {
                double real = 0, img = 0;
                for (int ySpace = 0; ySpace < height; ySpace++) {
                    for (int xSpace = 0; xSpace < width; xSpace++) {

                        double val = 2
                                * Math.PI
                                * ((1.0 * xWave * xSpace / width) + (1.0
                                * yWave * ySpace / height));

                        real += (inputData[ySpace][xSpace].re * Math
                                .cos(val))
                                / Math.sqrt(width * height);


                        img -= (inputData[ySpace][xSpace].re * Math
                                .sin(val))
                                / Math.sqrt(width * height);
                    }
                }

                if(result[yWave][xWave] == null)
                    result[yWave][xWave] = new Complex();

                result[yWave][xWave].re = real * 2;
                result[yWave][xWave].im = img * 2;
            }
        }

        return result;
    }


    public static void main(String... args) throws InterruptedException {
        int n = 4;


        Complex[][] m1 = new Complex[n][n];
        Complex[][] m2 = new Complex[n][n];
        Complex[][] sumResult = new Complex[n][n];
        Complex[][] DFTResult = new Complex[n][n];

        Random r = new Random();
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                m1[i][j] = new Complex(1 + (10) * r.nextDouble(),1 + (10) * r.nextDouble());
                m2[i][j] = new Complex(1 + (10) * r.nextDouble(),1 + (10) * r.nextDouble());
            }
        }


        Barrier barrier = new Barrier();
        DFTMonitor dftMonitor = new DFTMonitor();

        DFT dft1 = new DFT(dftMonitor,barrier,new Parameters(DFTResult,sumResult,0,0,m1,m2,n),0);
        DFT dft2 = new DFT(dftMonitor,barrier,new Parameters(DFTResult,sumResult,0,n/2,m1,m2,n),1);
        DFT dft3 = new DFT(dftMonitor,barrier,new Parameters(DFTResult,sumResult,n/2,0,m1,m2,n),2);
        DFT dft4 = new DFT(dftMonitor,barrier,new Parameters(DFTResult,sumResult,n/2,n/2,m1,m2,n),3);

        Thread t0 = new Thread(dft1);
        Thread t1 = new Thread(dft2);
        Thread t2 = new Thread(dft3);
        Thread t3 = new Thread(dft4);

        long startTimeMultiThreaded = System.currentTimeMillis();
        t0.start();
        t1.start();
        t2.start();
        t3.start();

        t0.join();
        t1.join();
        t2.join();
        t3.join();
        long estimatedTimeMultiThreaded = System.currentTimeMillis() - startTimeMultiThreaded;

        System.out.println("##### First matrix #######");
        printMatrix(m1);
        System.out.println();
        System.out.println("##### Second matrix #######");
        printMatrix(m2);
        System.out.println();
        System.out.println("##### Sum of two matrix #######");
        printMatrix(sumResult);
        System.out.println();
        System.out.println("##### DFT of two matrix #######");
        printMatrix(DFTResult);
        System.out.println();
        System.out.println("##### Single threaded DFT of two matrix #######");
        printMatrix(calculateDFT(sumResult));
        System.out.println();


        startTimeMultiThreaded = System.currentTimeMillis();
        summation(sumResult,m1,m2);
        calculateDFT(sumResult);
        long estimatedTimeSingleThreaded = System.currentTimeMillis() - startTimeMultiThreaded;


        System.out.println("Single thread time: "+ estimatedTimeSingleThreaded+"ms");
        System.out.println("Multithreaded time: " + estimatedTimeMultiThreaded+"ms");




    }
}
