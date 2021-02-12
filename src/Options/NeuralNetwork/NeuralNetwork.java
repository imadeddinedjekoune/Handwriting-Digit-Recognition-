package Options.NeuralNetwork;

import Math.Matrix;
import Math.Vector;
import ActivationFunction.ActivationFunction;
import Cost.CostFunction;
import Options.init.NormalizedInitialization;
import Options.init.WeightInitialization;
import ActivationFunction.SigmoidFunction;
import java.util.Arrays;

/**
 *
 * @author Imad
 */
public class NeuralNetwork {
    private final int size;
    private Vector[] biases;
    private Matrix[] weights;
    private final Matrix[] lastValues;
    private final Matrix[] lastActivations;
    private final ActivationFunction activationFunction;

    public NeuralNetwork(Matrix[] weights, Vector[] biases, int size, ActivationFunction activationFunction) 
    {
        this.size = size;
        this.biases = biases;
        this.weights = weights;
        this.lastValues = new Matrix[size - 1];
        this.lastActivations = new Matrix[size];
        this.activationFunction = activationFunction;
    }

    public NeuralNetwork(ActivationFunction neuronFunc, WeightInitialization weightInitialization, int... sizes) {
        this(weightInitialization.initWeights(sizes), weightInitialization.initBiases(sizes), sizes.length, neuronFunc);
    }

    
    public NeuralNetwork(int... sizes) {
        this(new SigmoidFunction(), new NormalizedInitialization(), sizes);
    }

    
    public Matrix feedforward(Matrix in) {
        lastActivations[0] = in;
        for (int i = 0; i < size - 1; i++) {
            lastValues[i] = weights[i].mulMat(lastActivations[i]).addVec(biases[i]);
            lastActivations[i + 1] = activationFunction.calculateMat(lastValues[i]);
        }
        return lastActivations[size - 1];
    }

    
    public void backpropagate(Matrix trainingIn, Matrix trainingOut, Matrix[] weightErrors, Vector[] biasErrors, CostFunction costFunction) {
        Matrix error = costFunction.calculateError(feedforward(trainingIn), trainingOut, lastValues[size - 2], activationFunction);
        for (int i = size - 2; i >= 0; i--) {
            weightErrors[i] = error.mulMat(lastActivations[i].transpose());
            biasErrors[i] = error.sumCols();
            if (i > 0) {
                error = weights[i].transpose().mulMat(error).mult_hadamardMat(activationFunction.calculateDerivMat(lastValues[i - 1]));
            }
        }
    }

    public int getSize() {
        return size;
    }

    
    public Vector[] getBiases() {
        return biases;
    }

    
    public Matrix[] getWeights() {
        return weights;
    }

    
    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    
    public void setWeights(Matrix[] weights) {
        this.weights = weights;
    }

   
    public void setBiases(Vector[] biases) {
        this.biases = biases;
    }

    @Override
    public String toString() 
    {
        return  "size=" + size + "\nbiasesLen = " + biases.length+ "\nbiases = "+Arrays.toString(biases)+"\n" + "\nWeightLen = " + weights.length+ "\nWeight = "+Arrays.toString(weights)+"\n"+ ", lastValues=" + lastValues + ", lastActivations=" + lastActivations + ", activationFunction=" + activationFunction + '}';
    }
    
    public void debug_ ()
    {
        System.out.println("-------------size -------------");
        System.out.println(size);
        System.out.println("-------------biases-------------");
        for (int i = 0 ; i < this.biases.length ; i++)
        {
            System.out.println("Layer : "+(i+1));
            System.out.println(biases[i]);
        }
        System.out.println("-------------weights-------------");
        for (int i = 0 ; i < this.weights.length ; i++)
        {
            System.out.println("Layer : "+(i+1));
            System.out.println(weights[i]);
        }
    }
}
