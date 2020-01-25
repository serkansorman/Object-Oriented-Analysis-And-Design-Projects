package Q1;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete matrix inversion solution class
 */
public class MatrixInversion implements SolutionMethod {

    /**
     * Copies inverse of matrix to original matrix
     * @param ar matrix that is 20x20
     * @param newArr matrix is that has original size
     * @param n matrix row
     * @param m matrix column
     */
    private void getInverseToSquareMatrix(double ar[][],double newArr[][], int n, int m) {
        for (int x = 0,i = 0; i < n; i++,++x) {
            for (int y = 0,j = n; j < m; j++,++y) {
                newArr[x][y] = ar[i][j];
            }
        }
    }

    /**
     * Solve the equations according matrix inversion
     * @param linearEquations solution method object
     * @return list of equations
     */
    @Override
    public List<Double> solution(List<List<Double>> linearEquations) {

        int size = linearEquations.size();

        double[][] mat = new double[size][size];
        double[][] constMat = new double[size][1];

        for(int i = 0; i < size; ++i)
            for(int j = 0; j < size; ++j)
                mat[i][j] = linearEquations.get(i).get(j);

        for(int i = 0; i < size; ++i){
            for(int j = 0; j < size+1; ++j)
                if(j == size)
                    constMat[i][0] = linearEquations.get(i).get(j);
        }

        double[][] matrix = new double[20][20];
        double[][] newArr = new double[size][size];

        for(int i = 0; i < size; ++i)
            System.arraycopy(mat[i], 0, matrix[i], 0, size);

        inverse(matrix,mat.length);
        getInverseToSquareMatrix(matrix,newArr,size,size * 2);

        double [][] product = multiplyMatrices(newArr,constMat,mat.length,mat[0].length,constMat[0].length);

        List<Double> result = new ArrayList<>();
        for(int i = 0; i < size; ++i)
                result.add(product[i][0]);

        return result;
    }

    /**
     * Gets inverse of given matrix
     * @param matrix matrix to be reversed
     * @param size number of row of given matrix
     */
    private void inverse(double[][] matrix,int size){

        //Create identity matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2 * size; j++) {
                if (j == (i + size))
                    matrix[i][j] = 1;
            }
        }

        //Swap rows
        for (int i = size - 1; i > 0; i--) {
            if (matrix[i - 1][0] < matrix[i][0]) {
                double[] tempRow = matrix[i];
                matrix[i] = matrix[i - 1];
                matrix[i - 1] = tempRow;
            }
        }

        // Replace a row by sum of current row
        double temp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    temp = matrix[j][i] / matrix[i][i];
                    for (int k = 0; k < 2 * size; k++) {
                        matrix[j][k] -= matrix[i][k] * temp;
                    }
                }
            }
        }

        // Divide row element by the diagonal element 
        for (int i = 0; i < size; i++) {
            temp = matrix[i][i];
            for (int j = 0; j < 2 * size; j++) {
                matrix[i][j] = matrix[i][j] / temp;
            }
        }
    }

    /**
     * Multiplies given two matrix
     * @param firstMatrix
     * @param secondMatrix
     * @param r1 number of row of first matrix
     * @param c1 number of column of first matrix
     * @param c2 number of column of second matrix
     * @return
     */
    private double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix, int r1, int c1, int c2) {
        double[][] result = new double[r1][c2];
        for(int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return result;
    }

}
