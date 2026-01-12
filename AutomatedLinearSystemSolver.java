
public class AutomatedLinearSystemSolver {

    public static void solver(double[][] A) {
        int rowCount = A.length;
        int colCount = A[0].length;

        int currentColumn = 0;

        for(int currentRow = 0; currentRow < rowCount; ++currentRow) {
            if(currentColumn >= colCount) {
                return;
            }

            int pivotRow = currentRow;

            // Find the pivot row.
            while(A[pivotRow][currentColumn] == 0) {
                pivotRow++;
                if(pivotRow == rowCount) {
                    pivotRow = currentRow;
                    currentColumn++;
                    if(currentColumn == colCount) {
                        return;
                    }
                }
            }

            // Swap the current row with the pivot row.
            if(pivotRow != currentRow) {
                double[] temp = A[currentRow];
                A[currentRow] = A[pivotRow];
                A[pivotRow] = temp;
            }

            // Normalize the pivot row.
            double pivotValue = A[currentRow][currentColumn];
            for(int j = 0; j < colCount; ++j ) {
                A[currentRow][j] /= pivotValue;
            }

            // Eliminate the current column entries in other rows.
            for(int i = 0; i < rowCount; ++i) {
                if(i != currentRow) {
                    double factor = A[i][currentColumn];
                    for(int j = 0; j < colCount; ++j) {
                        A[i][j] -= factor * A[currentRow][j];
                    }
                }
            }
            currentColumn++;
        }

    // Snap tiny values to zero for cleaner output. Outputs can be -0.0 otherwise.
    for (int i = 0; i < rowCount; i++) {
        for (int j = 0; j < colCount; j++) {
                // If the number is tiny (like -0.0000001), snap it to 0.0
            if (Math.abs(A[i][j]) < 1e-10) {
                    A[i][j] = 0.0;
            }
        }
    }
} 
    

    public static void main(String[] args) {
        double[][] A = {
            {2, 1, -1, 8},
            {-3, -1, 2, -11},
            {-2, 1, 2, -3}
        };

        solver(A);

        // Print the resulting matrix
        for (double[] row : A) {
            for (double value : row) {
                System.out.printf("%8.4f ", value);
            }
            System.out.println();
        }
    }
}
