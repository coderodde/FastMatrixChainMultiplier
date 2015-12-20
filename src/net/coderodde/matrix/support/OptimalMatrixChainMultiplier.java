package net.coderodde.matrix.support;

import net.coderodde.matrix.Matrix;
import net.coderodde.matrix.MatrixChainMultiplier;

/**
 * This class implements an optimal matrix chain multiplying algorithm, which
 * performs the minimum number of scalar multiplications.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 19, 2015)
 */
public class OptimalMatrixChainMultiplier implements MatrixChainMultiplier {

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
        
        Matrix[] cloneMatrices = new Matrix[matrices.length];
        
        for (int i = 0; i < matrices.length; ++i) {
            cloneMatrices[i] = matrices[i].clone();
        }
        
        return computeOptimalParenthesation(cloneMatrices);
    }
    
    private static int[] extractDimensionArray(Matrix[] chain) {
        int[] input = new int[chain.length + 1];
        
        for (int i = 0; i < chain.length; ++i) {
            input[i] = chain[i].getHeight();
        }
        
        input[input.length - 1] = chain[chain.length - 1].getWidth();
        return input;
    }
    
    private Matrix computeOptimalParenthesation(Matrix[] matrices) {
        int[] p = extractDimensionArray(matrices);
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        
        for (int len = 2; len <= n; ++len) {
            for (int i = 1; i <= n - len + 1; ++i) {
                int j = i + len - 1;
                m[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k <= j - 1; ++k) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    
                    if (m[i][j] > q) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        
        return process(s, matrices, 1, n);
    }
    
    private Matrix process(int[][] s, Matrix[] matrices, int i, int j) {
        if (i == j) {
            return matrices[i - 1];
        }
        
        return process(s, matrices, i, s[i][j])
              .multiply(process(s, matrices, s[i][j] + 1, j));
    }
}
