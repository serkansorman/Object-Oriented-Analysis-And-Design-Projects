package sample.Q2;

public class Parameters {

    private Complex[][] sumResult;
    private Complex[][] DFTResult;
    private int startRow;
    private int startColumn;
    public static int numberOfThread;
    private Complex[][] m1;
    private Complex[][] m2;
    private int n;

    public Parameters(Complex[][] DFTResult,Complex[][] sumResult, int numberOfThread,int startRow, int startColumn, Complex[][] m1, Complex[][] m2, int n) {
        Parameters.numberOfThread = numberOfThread;
        this.DFTResult = DFTResult;
        this.sumResult = sumResult;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.m1 = m1;
        this.m2 = m2;
        this.n = n;
    }

    public Complex[][] getSumResult() {
        return sumResult;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public Complex[][] getM1() {
        return m1;
    }

    public Complex[][] getM2() {
        return m2;
    }

    public int getN() {
        return n;
    }

    public Complex[][] getDFTResult() {
        return DFTResult;
    }

}
