package Q1;

import java.util.List;

public class LinearSolverDeluxe extends LinearSolver {

    /**
     *
     * @param result list of solution
     */
    @Override
    public void displaySolution(List<Double> result) {
        for(Double i : result){
            if(Double.isNaN(i) || Double.isInfinite(i)){
                System.out.println("No unique solution");
                return;
            }
            System.out.println(String.format("%.2f", i));
        }

    }
}
