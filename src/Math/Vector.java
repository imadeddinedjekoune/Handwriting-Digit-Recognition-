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
public class Vector {
    
    private final int n;
    private final double[] a;

    // Constructeur //
    public Vector(int size) 
    {
        this.a = new double[size];
        this.n = size;
    }
    
    public Vector(double... v) {
        this.a = v;
        this.n = v.length;
    }
    
   public Vector(int... v) {
        
        a = new double[v.length];
        for(int i=0; i<v.length; i++) {
            a[i] = v[i];
        }
        this.n = v.length;
    }
    
    
    // Operration //
    public Vector addVec(Vector b) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++) {
            c.a[i] = a[i] + b.a[i];
        }
        return c;
    }
    public Vector subVec(Vector b) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++) {
            c.a[i] = a[i] - b.a[i];
        }
        return c;
    }
    public Vector mult_hadamardVec(Vector b) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++) {
            c.a[i] = a[i] * b.a[i];
        }
        return c;
    }
    public Vector mulSca(double s) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++) {
            c.a[i] = a[i] * s;
        }
        return c;
    }
    public double dotVec(Vector b) {
        double s = 0;
        for (int i = 0; i < n; i++) {
            s += a[i] * b.a[i];
        }
        return s;
    }
    public double length() {
        return Math.sqrt(dotVec(this));
    }
    
    // Utils //
    public int getN() {
        return n;
    }
    public double[] getArray() {
        return a;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.n;
        hash = 47 * hash + Arrays.hashCode(this.a);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector other = (Vector) obj;
        if (this.n != other.n) {
            return false;
        }
        if (!Arrays.equals(this.a, other.a)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (double vi : a) 
        {
            result.append(vi).append(" ");
        }
        return result.toString();
    }
    
    
}
