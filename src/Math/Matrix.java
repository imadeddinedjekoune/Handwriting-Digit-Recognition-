/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import java.util.Arrays;

/**
 *
 * @author Imad
 */
public class Matrix 
{
    private final int n, m;
    private final double[][] A;
    
    public Matrix(double[][] A)
    {
        this.A = A;
        this.n = A.length;
        this.m = A[0].length;
    }
    
    public Matrix(int n, int m) {
        this(new double[n][m]);
    }
    
    public Matrix(Vector... cols) {
        this(cols[0].getN(), cols.length);
        for (int j = 0; j < m; j++) {
            double[] b = cols[j].getArray();
            for (int i = 0; i < n; i++) {
                A[i][j] = b[i];
            }
        }
    }
    
    // Opperation //
    public Matrix transpose() {
        Matrix C = new Matrix(m, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C.A[j][i] = A[i][j];
            }
        }
        return C;
    }
    
    public Matrix addVec(Vector b) {
        Matrix C = new Matrix(n, m);
        double[] ba = b.getArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C.A[i][j] = A[i][j] + ba[i];
            }
        }
        return C;
    }

    /**
     * Adds another matrix.
     *
     * @param B matrix with values to add
     * @return resulting matrix
     */
    public Matrix addMat(Matrix B) {
        Matrix C = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C.A[i][j] = A[i][j] + B.A[i][j];
            }
        }
        return C;
    }

   
    public Matrix subMat(Matrix B) {
        Matrix C = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C.A[i][j] = A[i][j] - B.A[i][j];
            }
        }
        return C;
    }

   
    public Matrix mulMat(Matrix B) {
        Matrix C = new Matrix(n, B.m);
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < B.n; k++) {
                for (int j = 0; j < B.m; j++) {
                    C.A[i][j] += A[i][k] * B.A[k][j];
                }
            }
        }
        return C;
    }

   
    public Matrix mult_hadamardMat(Matrix B) {
        Matrix C = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C.A[i][j] = A[i][j] * B.A[i][j];
            }
        }
        return C;
    }

    
    public Vector mulVec(Vector b) {
        double[] c = new double[n];
        double[] ba = b.getArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i] += A[i][j] * ba[j];
            }
        }
        return new Vector(c);
    }

   
    public Matrix mulSca(double s) {
        Matrix C = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C.A[i][j] = s * A[i][j];
            }
        }
        return C;
    }

    public Vector sumCols() {
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i] += A[i][j];
            }
        }
        return new Vector(c);
    }

    public Vector[] getCols() {
        Vector[] result = new Vector[m];
        for (int j = 0; j < m; j++) {
            double[] v = new double[n];
            for (int i = 0; i < n; i++) {
                v[i] = A[i][j];
            }
            result[j] = new Vector(v);
        }
        return result;
    }

    // Utils //
    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public double[][] getArray() {
        return A;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix)) {
            return false;
        }
        Matrix B = ((Matrix) o);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != B.A[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Arrays.deepHashCode(this.A);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (double[] mr : A) {
            for (double mi : mr) {
                result.append(mi).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
