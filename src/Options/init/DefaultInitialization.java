package Options.init;

import Math.Matrix;
import Math.Vector;
import java.util.Random;


public class DefaultInitialization extends WeightInitialization {

    
    @Override
    public Vector[] initBiases(int[] sizes) {
        Random rand = new Random();
        Vector[] biases = new Vector[sizes.length - 1];

        for (int i = 0; i < sizes.length - 1; i++) {
            double[] bias = new double[sizes[i + 1]];
            for (int j = 0; j < sizes[i + 1]; j++) {
                bias[j] = rand.nextGaussian();
            }
            biases[i] = new Vector(bias);
        }

        return biases;
    }

    
    @Override
    public Matrix[] initWeights(int[] sizes) {
        Random rand = new Random();
        Matrix[] weights = new Matrix[sizes.length - 1];

        for (int i = 0; i < sizes.length - 1; i++) {
            double[][] weight = new double[sizes[i + 1]][sizes[i]];
            for (int j = 0; j < sizes[i + 1]; j++) {
                for (int k = 0; k < sizes[i]; k++) {
                    weight[j][k] = rand.nextGaussian();
                }
            }
            weights[i] = new Matrix(weight);
        }

        return weights;
    }

}
