package sample.Q2;

public class DFT implements Runnable{


    private SolutionVersion solutionVersion;
    private Parameters parameters;
    private int threadId;
    public static int completedPartial = 0;
    private int endRow;
    private int endColumn;


    public DFT(SolutionVersion solutionVersion, Parameters parameters,int threadId) {
        this.solutionVersion = solutionVersion;
        this.parameters = parameters;
        this.threadId = threadId;
    }

    public void setSolutionVersion(SolutionVersion solutionVersion){
        this.solutionVersion = solutionVersion;
    }

    /**
     * Determines threads calculation position
     */
    private void setEndRowColumn(){

        int n = parameters.getN();
        switch (Parameters.numberOfThread){
            case 1:
                endRow = n;
                endColumn = n;
                break;
            case 2:
                endRow = parameters.getStartRow() + n/2;
                endColumn = parameters.getStartColumn() + n;
                break;
            case 4:
                endRow = parameters.getStartRow() + n/2;
                endColumn = parameters.getStartColumn() + n/2;
                break;
            case 8:
                endRow = parameters.getStartRow() + n/4;
                endColumn = parameters.getStartColumn() + n/2;

        }
    }


    private void summation(){
        setEndRowColumn();
        for(int i=parameters.getStartRow(); i<endRow; ++i){
            for(int j=parameters.getStartColumn(); j<endColumn; ++j){
                parameters.getSumResult()[i][j] = parameters.getM1()[i][j].plus(parameters.getM2()[i][j]);
            }
        }

    }

    private void calculateDFT() {
        int n = parameters.getN();

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

        summation();
        System.out.println("Thread "+threadId+" completed its summation");

        try {
            solutionVersion.solution();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        calculateDFT();
        System.out.println("Thread "+threadId+" completed its DFT calculation");


    }
}
