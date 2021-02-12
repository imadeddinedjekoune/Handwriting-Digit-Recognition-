package Cost;

import Math.Matrix;
import Math.Vector;
import ActivationFunction.ActivationFunction;
import Options.NeuralNetwork.NeuralNetwork;

public class QuadraticCostFunction extends CostFunction {

 
    @Override
    public double calculateTotal(NeuralNetwork net, Vector[] dataIn, Vector[] dataOut) {
        double sum = 0;
        Vector[] errorCols = new Matrix(dataOut).subMat(net.feedforward(new Matrix(dataIn))).getCols();
        for (int i = 0; i < dataIn.length; i++) {
            sum += Math.pow(errorCols[i].length(), 2);
        }
        return (1.0 / (2.0 * dataIn.length)) * sum;
    }

    @Override
    public Matrix calculateError(Matrix calcOut, Matrix dataOut, Matrix values, ActivationFunction activationFunction) {
        return calcOut.subMat(dataOut).mult_hadamardMat(activationFunction.calculateDerivMat(values));
    }
}
