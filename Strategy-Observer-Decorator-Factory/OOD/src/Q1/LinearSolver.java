package Q1;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract LinearSolver that holds solution method
 */
public abstract class LinearSolver {
    SolutionMethod solutionMethod;

    public LinearSolver() {
    }

    /**
     * Set solution methods dynamically
     * @param solutionMethod solution method object
     */
    public void setSolutionMethod(SolutionMethod solutionMethod) {
        this.solutionMethod = solutionMethod;
    }

    /**
     * Solve the equations according to gauss or matrix elimination
     * @param linearEquations list of equations that will solved
     * @return list of solution
     */
    public List<Double> performSolution(List<List<Double>> linearEquations){
        return solutionMethod.solution(linearEquations);
    }

    /**
     * Display the result of the solution
     * @param result list of solution
     */
    public abstract void displaySolution(List<Double> result);

}
