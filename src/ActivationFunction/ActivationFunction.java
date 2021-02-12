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
public abstract class ActivationFunction 
{
    public abstract Vector calculateVec(Vector v);
    public abstract Matrix calculateMat(Matrix M);
    public abstract Vector calculateDerivVec(Vector v);
    public abstract Matrix calculateDerivMat(Matrix M);
    
}

