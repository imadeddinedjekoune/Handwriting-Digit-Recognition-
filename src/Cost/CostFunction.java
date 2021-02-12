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
public abstract class CostFunction 
{
    public abstract double calculateTotal(NeuralNetwork net, Vector[] dataIn, Vector[] dataOut);
    public abstract Matrix calculateError(Matrix calcOut, Matrix dataOut, Matrix values, ActivationFunction activationFunction);
}
