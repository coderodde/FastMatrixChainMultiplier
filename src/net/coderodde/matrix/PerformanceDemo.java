package net.coderodde.matrix;

import java.util.Random;
import net.coderodde.matrix.support.NaiveMatrixChainMultiplier;
import net.coderodde.matrix.support.OptimalMatrixChainMultiplier;

public class PerformanceDemo {

    private static final int MATRIX_CHAIN_LENGTH = 10;
    private static final int MAXIMUM_WIDTH = 500;
    private static final int MAXIMUM_HEIGHT = 500;
    private static final int MINIMUM_VALUE = -5;
    private static final int MAXIMUM_VALUE = 5;
    private static final double E = 0.001;
    
    public static void main(final String... args) {
        long seed = System.nanoTime();
        Random random = new Random(seed);
        Matrix[] chain = createRandomMatrixChain(MATRIX_CHAIN_LENGTH,
                                                 MAXIMUM_WIDTH,
                                                 MAXIMUM_HEIGHT,
                                                 MINIMUM_VALUE,
                                                 MAXIMUM_VALUE,
                                                 random);
        System.out.println("Seed = " + seed);
        
        Matrix result1 = profile(new NaiveMatrixChainMultiplier(), chain);
        Matrix result2 = profile(new OptimalMatrixChainMultiplier(), chain);
        
        System.out.println("Matrices equal: " + equals(result1, result2, E));
    }
    
    private static boolean equals(Matrix m1, Matrix m2, double e) {
        if (m1.getWidth() != m2.getWidth()) {
            return false;
        }
        
        if (m1.getHeight() != m2.getHeight()) {
            return false;
        }
        
        for (int y = 0; y < m1.getHeight(); ++y) {
            for (int x = 0; x < m1.getWidth(); ++x) {
                if (Math.abs(m1.read(x, y) - m2.read(x, y)) > e) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static Matrix profile(MatrixChainMultiplier multiplier, 
                                  Matrix[] chain) {
        long startTime = System.nanoTime();
        Matrix ret = multiplier.multiply(chain);
        long endTime = System.nanoTime();
        
        System.out.println(multiplier.getClass().getSimpleName() + " in " +
                           (int)((endTime - startTime) / 1e6) + 
                           " milliseconds.");
        
        return ret;
    }
    
    private static Matrix[] createRandomMatrixChain(int matrices,
                                                    int maximumWidth,
                                                    int maximumHeight,
                                                    int minimumValue,
                                                    int maximumValue,
                                                    Random random) {
        Matrix[] ret = new Matrix[matrices];

        if (ret.length == 0) {
            return ret;
        }
       
        ret[0] = createRandomMatrix(randomInt(1, maximumWidth, random),
                                    randomInt(1, maximumHeight, random),
                                    minimumValue,
                                    maximumValue,
                                    random);
        
        int previousWidth = ret[0].getWidth();
        
        for (int i = 1; i < matrices; ++i) {
            ret[i] = createRandomMatrix(randomInt(1, maximumWidth, random),
                                        previousWidth,
                                        minimumValue,
                                        maximumValue,
                                        random);
            previousWidth = ret[i].getWidth();
        }
        
        return  ret;
    }
    
    private static Matrix createRandomMatrix(int width, 
                                             int height, 
                                             int minimumValue, 
                                             int maximumValue, 
                                             Random random) {
        Matrix ret = new Matrix(width, height);
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                ret.write(x, y, randomInt(minimumValue, maximumValue, random));
            }
        }
        
        return ret;
    }
    
    private static int randomInt(int minimumValue, 
                                 int maximumValue, 
                                 Random random) {
        return random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
    }
}
