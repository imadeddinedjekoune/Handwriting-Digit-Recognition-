/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActivationFunction;

import Math.Matrix;
import Math.Vector;

/**
 *
 * @author Imad
 */
public class SigmoidFunction extends ActivationFunction {

    private double calculate(double x) {
        return 1 / (1 + Math.exp(-x));
    }
    
    @Override
    public Vector calculateVec(Vector v) {
        double[] c = new double[v.getN()];
        double[] va = v.getArray();
        for (int i = 0; i < v.getN(); i++) {
            c[i] = calculate(va[i]);
        }
        return new Vector(c);
    }
    
    public Matrix calculateMat(Matrix M) {
        double[][] C = new double[M.getN()][M.getM()];
        double[][] MA = M.getArray();
        for (int i = 0; i < M.getN(); i++) {
            for (int j = 0; j < M.getM(); j++) {
                C[i][j] = calculate(MA[i][j]);
            }
        }
        return new Matrix(C);
    }
    
    private double calculateDeriv(double x) {
        return Math.exp(x) / Math.pow(Math.exp(x) + 1.0, 2.0);
    }

    public Vector calculateDerivVec(Vector v) {
        double[] c = new double[v.getN()];
        double[] va = v.getArray();
        for (int i = 0; i < v.getN(); i++) {
            c[i] = calculateDeriv(va[i]);
        }
        return new Vector(c);
    }

    public Matrix calculateDerivMat(Matrix M) {
        double[][] C = new double[M.getN()][M.getM()];
        double[][] MA = M.getArray();
        for (int i = 0; i < M.getN(); i++) {
            for (int j = 0; j < M.getM(); j++) {
                C[i][j] = calculateDeriv(MA[i][j]);
            }
        }
        return new Matrix(C);
    }

    
    
}
