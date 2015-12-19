package net.coderodde.matrix;

import java.util.Arrays;

/**
 * This class implements a matrix data type.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 19, 2015)
 */
public class Matrix {
    
    private final double[][] matrix;
    
    /**
     * Creates a square matrix with width and height equal to {@code dimension}.
     * 
     * @param dimension the width and height of the square matrix.
     */
    public Matrix(int dimension) {
        this(dimension, dimension);
    }
    
    public Matrix(int width, int height) {
        checkWidth(width);
        checkHeight(height);
        this.matrix = new double[height][width];
    }
    
    public int getWidth() {
        return matrix[0].length;
    }
    
    public int getHeight() {
        return matrix.length;
    }
    
    public double read(int x, int y) {
        return matrix[y][x];
    }
    
    public void write(int x, int y, double value) {
        matrix[y][x] = value;
    }
    
    public Matrix multiply(Matrix rightHandMatrix) {
        checkDimensions(rightHandMatrix);
        
        int thisWidth = getWidth();
        int thisHeight = getHeight();
        int otherWidth = rightHandMatrix.getWidth();
        
        Matrix ret = new Matrix(thisHeight, otherWidth);
        
        for (int thisY = 0; thisY < thisHeight; ++thisY) {
            for (int otherX = 0; otherX < otherWidth; ++otherX) {
                double sum = 0.0;
                
                for (int i = 0; i < thisWidth; ++i) {
                    sum += read(i, thisY) * rightHandMatrix.read(otherX, i);
                }
                
                ret.write(otherX, thisY, sum);
            }
        }
        
        return ret;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!getClass().equals(o.getClass())) {
            return false;
        }
        
        Matrix other = (Matrix) o;
        
        return Arrays.deepEquals(matrix, other.matrix);
    }
    
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        int width = getWidth();
        int height = getHeight();
        int maxIntegerPartLength = 0;
        int maxDecimalPartLength = 0;
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                String s = Double.toString(read(x, y));
                String[] parts = s.split("\\.");
                
                if (maxIntegerPartLength < parts[0].length()) {
                    maxIntegerPartLength = parts[0].length();
                }
                
                if (maxDecimalPartLength < parts[1].length()) {
                    maxDecimalPartLength = parts[1].length();
                }
            }
        } 
        
        String fmt = "%" + (maxIntegerPartLength + maxDecimalPartLength + 1) 
                         + "." + maxDecimalPartLength + "f";
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                sb.append(String.format(fmt, read(x, y)));
                
                if (x < width - 1) {
                    sb.append(' ');
                }
            }
            
            if (y < height - 1) {
                sb.append('\n');
            }
        }
        
        return sb.toString();
    }
    
    private void checkWidth(int width) {
        checkIsPositive(width);
    }
    
    private void checkHeight(int height) {
        checkIsPositive(height);
    }
    
    private void checkDimensions(Matrix rightHandMatrix) {
        if (this.getWidth() != rightHandMatrix.getHeight()) {
            throw new IllegalArgumentException(
                    "Dimension mismatch. The number of columns in this " +
                    "matrix (" + getWidth() + ") does not equal the number " +
                    "of rows in the right hand matrix (" + 
                    rightHandMatrix.getHeight() + ").");
        }
    }
    
    private void checkIsPositive(int number) {
        if (number < 1) {
            throw new IllegalArgumentException(
                    "The input number is not positive. Received: " + number);
        }
    }
}
