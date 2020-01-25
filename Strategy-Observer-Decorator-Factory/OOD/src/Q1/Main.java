package Q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void  main(String... args){
        System.out.println("########### Gauss Elimination ##########");

        SolutionMethod gauss = new GaussianElimination();
        LinearSolver linearSolver = new LinearSolverDeluxe();
        linearSolver.setSolutionMethod(gauss);

        ArrayList<List<Double>> linearEquations = new ArrayList<>();

        linearEquations.add(Arrays.asList(1.0, 2.0, 3.0,1.0)); // x + 2y + 3z = 1.0
        linearEquations.add(Arrays.asList(4.0, 5.0, 6.0,1.0)); // 4x + 5y + 6z = 1.0
        linearEquations.add(Arrays.asList(1.0, 0.0, 1.0,1.0)); // x + z = 1.0


        ArrayList<List<Double>> copyEquations = new ArrayList<>();
        for (List<Double> equation : linearEquations ) {
            ArrayList<Double> equationCopy = new ArrayList<>(equation);
            copyEquations.add(equationCopy);
        }


        List<Double> solution = linearSolver.performSolution(linearEquations);
        linearSolver.displaySolution(solution);

        System.out.println("########### Matrix inversion ##########");
        SolutionMethod matrixInv = new MatrixInversion();
        linearSolver.setSolutionMethod(matrixInv);
        List<Double> solution2 = linearSolver.performSolution(copyEquations);
        linearSolver.displaySolution(solution2);


        System.out.println("########### Matrix inversion with No unique solution ##########");
        linearEquations = new ArrayList<>();
        linearEquations.add(Arrays.asList(3.0, 2.0, -4.0, 3.0)); // 3x + 2y + -4z = 3.0
        linearEquations.add(Arrays.asList(6.0, 4.0, -8.0, 6.0)); // 6x + 4y + -8z = 6.0
        linearEquations.add(Arrays.asList(9.0, 6.0, -12.0, 9.0));// 9x + 6y + -12z = 9.0

        List<Double> solution3 = linearSolver.performSolution(linearEquations);
        linearSolver.displaySolution(solution3);
    }
}
