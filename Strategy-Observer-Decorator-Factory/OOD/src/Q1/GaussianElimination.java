package Q1;


import java.util.Arrays;
import java.util.List;

/**
 * Concrete gauss elimination solution class
 */
public class GaussianElimination implements SolutionMethod {
    /**
     * Solve the equations according to gauss elimination
     * @param linearEquations list of equations
     * @return list of solution
     */
    @Override
    public List<Double> solution(List<List<Double>> linearEquations) {
        int size = linearEquations.size();

        for (int i=0; i<size; ++i) {
            // Find max in current column
            double maxEl = Math.abs(linearEquations.get(i).get(i));
            int maxRow = i;
            for (int k=i+1; k<size; k++) {
                if (Math.abs(linearEquations.get(k).get(i)) > maxEl) {
                    maxEl = Math.abs(linearEquations.get(k).get(i));
                    maxRow = k;
                }
            }

            // Swap max row with current row
            for (int k=i; k<size+1;k++) {
                double tmp = linearEquations.get(maxRow).get(k);
                linearEquations.get(maxRow).set(k,linearEquations.get(i).get(k));
                linearEquations.get(i).set(k,tmp);
            }

            // Make all rows below this one 0 in current column
            for (int k=i+1; k<size; k++) {
                double c = -linearEquations.get(k).get(i)/linearEquations.get(i).get(i);
                for (int j=i; j<size+1; j++) {
                    if (i==j) {
                        linearEquations.get(k).set(j,0.0);
                    }
                    else
                        linearEquations.get(k).set(j,linearEquations.get(k).get(j) + c * linearEquations.get(i).get(j));

                }
            }
        }

        // Solve equation Ax=b for an upper triangular matrix A
        List<Double> result = Arrays.asList(new Double[size]);
        for (int i=size-1; i>=0; i--) {
            result.set(i,linearEquations.get(i).get(size) / linearEquations.get(i).get(i)) ;
            for (int k=i-1;k>=0; k--) {
                linearEquations.get(k).set(size,linearEquations.get(k).get(size) - (result.get(i) * linearEquations.get(k).get(i)));
            }
        }

        return result;

    }
}
