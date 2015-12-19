package net.coderodde.matrix.support;

import net.coderodde.matrix.IncompatibleMatrixException;
import net.coderodde.matrix.Matrix;
import net.coderodde.matrix.MatrixChainMultiplier;

/**
 * This class implements a naive algorithm for multiplying matrix chains.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 19, 2015)
 */
public class NaiveMatrixChainMultiplier implements MatrixChainMultiplier {

    @Override
    public Matrix multiply(Matrix... matrices) {
        switch (matrices.length) {
            case 0: {
                return null;
            }
            
            case 1: {
                return matrices[0].clone();
            }
        }
        
        Matrix current = matrices[0].clone();

        for (int i = 1; i < matrices.length; ++i) {
            current = current.multiply(matrices[i]);
        }
        
        return current;
    }
}
