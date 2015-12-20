package net.coderodde.matrix.support;

import net.coderodde.matrix.Matrix;
import org.junit.Test;
import static org.junit.Assert.*;

public class OptimalMatrixChainMultiplierTest {

    private static final double E = 1e-5;
    
    @Test
    public void testMultiply() {
        Matrix[] matrices = new Matrix[3];
        
        matrices[0] = new Matrix(2, 2);
        matrices[1] = new Matrix(3, 2);
        matrices[2] = new Matrix(2, 3);
        
        int num = 1;
        
        for (Matrix m : matrices) {
            int width = m.getWidth();
            int height = m.getHeight();
            
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    m.write(x, y, num++);
                }
            }
        }
        
        Matrix result = new OptimalMatrixChainMultiplier().multiply(matrices);
        
        assertEquals(2, result.getWidth());
        assertEquals(2, result.getHeight());
        
        assertEquals(948.0 , result.read(0, 0), E);
        assertEquals(2134.0, result.read(0, 1), E);
        assertEquals(1020.0, result.read(1, 0), E);
        assertEquals(2296.0, result.read(1, 1), E);
    }
}
