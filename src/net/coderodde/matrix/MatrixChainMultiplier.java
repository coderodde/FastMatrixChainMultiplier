package net.coderodde.matrix;

/**
 * This abstract class defines the API for matrix chain multiplication 
 * algorithms.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 19, 2015)
 */
public interface MatrixChainMultiplier {
   
    /**
     * Computes the product of the matrix chain.
     * 
     * @param matrices an array of matrices to multiply.
     * @return         the matrix chain product.
     */
    public Matrix multiply(Matrix... matrices);
}
