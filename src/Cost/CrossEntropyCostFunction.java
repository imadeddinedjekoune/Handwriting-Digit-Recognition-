/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cost;

import Math.Matrix;
import Math.Vector;
import ActivationFunction.ActivationFunction;
import Options.NeuralNetwork.NeuralNetwork;

/**
 *
 * @author Imad
 */
public class CrossEntropyCostFunction extends CostFunction {

    @Override
    public double calculateTotal(NeuralNetwork net, Vector[] dataIn, Vector[] dataOut) 
    {
        double sum = 0;
        double[][] MA = net.feedforward(new Matrix(dataIn)).getArray();
        
        for (int i = 0; i < dataIn.length; i++) 
        {
            double[] y = dataOut[i].getArray();
            for (int j = 0; j < y.length; j++) 
            {
                sum += y[j] * Math.log(MA[j][i]) + (1.0 - y[j]) * Math.log(1.0 - MA[j][i]);
            }
        }
        return -(1.0 / dataIn.length) * sum;
    }

    @Override
    public Matrix calculateError(Matrix calcOut, Matrix dataOut, Matrix values, ActivationFunction activationFunction) 
    {
        return calcOut.subMat(dataOut);
    }

}
