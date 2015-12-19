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
        
        int[] input = new int[matrices.length + 1];
        computeOptimalParenthesation(input);
        return null;
    }
    
    private int[][] computeOptimalParenthesation(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        
        for (int len = 2; len <= n; ++len) {
            for (int i = 1; i <= n - len; ++i) {
                int j = i + len - 2;
                m[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k < j; ++k) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    
                    if (m[i][j] > q) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        
        for (int[] row : m) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            
            System.out.println();
        }
        
        return s;
    }
}
