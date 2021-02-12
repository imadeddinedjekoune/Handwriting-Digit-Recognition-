package IO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Math.Matrix;
import Math.Vector;
import ActivationFunction.ActivationFunction;
import ActivationFunction.SigmoidFunction;
import Options.NeuralNetwork.NeuralNetwork;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Imad
 */
public class NetworkIO {

 
    public static NeuralNetwork loadNetwork(String file) {
        Vector[] biases = null;
        Matrix[] weights = null;
        ActivationFunction activationFunction = null;
        int size = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
            if (lines.get(0).trim().equals("SIGMOID_ACTIVATION_FUNCTION")) {
                activationFunction = new SigmoidFunction();
            }
            size = Integer.parseInt(lines.get(1));
            biases = new Vector[size - 1];
            for (int i = 2; i < size + 1; i++) {
                String[] components = lines.get(i).trim().split(" ");
                double[] values = new double[components.length];
                for (int j = 0; j < components.length; j++) {
                    values[j] = Double.parseDouble(components[j]);
                }
                biases[i - 2] = new Vector(values);
            }
            weights = new Matrix[size - 1];
            int matrixIndex = 0;
            ArrayList<Double[]> rows = new ArrayList<>();
            for (int i = size + 1; i < lines.size(); i++) {
                if (lines.get(i).isEmpty()) {
                    double[][] matrix = new double[rows.size()][rows.get(0).length];
                    for (int j = 0; j < rows.size(); j++) {
                        for (int k = 0; k < rows.get(j).length; k++) {
                            matrix[j][k] = rows.get(j)[k];
                        }
                    }
                    rows.clear();
                    weights[matrixIndex++] = new Matrix(matrix);
                    i++;
                }
                if (i >= lines.size() || lines.get(i).isEmpty()) {
                    break;
                }
                String[] components = lines.get(i).trim().split(" ");
                Double[] values = new Double[components.length];
                for (int k = 0; k < components.length; k++) {
                    values[k] = Double.parseDouble(components[k]);
                }
                rows.add(values);

            }
        } catch (IOException ex) {
        }
        return new NeuralNetwork(weights, biases, size, activationFunction);
    }
    
    

    
}
