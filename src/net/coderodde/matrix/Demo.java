package net.coderodde.matrix;

import java.util.Random;

public class Demo {

    public static void main(final String... args) {
        
    }
    
    private static Matrix createRandomMatrix(int width, 
                                             int height, 
                                             int minValue, 
                                             int maxValue, 
                                             Random random) {
        Matrix ret = new Matrix(width, height);
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                ret.write(x, y, randomInt(minValue, maxValue, random));
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
