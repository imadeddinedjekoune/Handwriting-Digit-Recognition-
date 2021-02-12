package Options.init;

import Math.Matrix;
import Math.Vector;


public abstract class WeightInitialization 
{
    public abstract Vector[] initBiases(int[] sizes);
    public abstract Matrix[] initWeights(int[] sizes);
}
