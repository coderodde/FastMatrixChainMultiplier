package net.coderodde.matrix.support;

import net.coderodde.matrix.Matrix;
import org.junit.Test;
import static org.junit.Assert.*;

public class OptimalMatrixChainMultiplierTest {

    @Test
    public void testMultiply() {
        Matrix[] chain = new Matrix[]{
            new Matrix(35, 30),
            new Matrix(15, 35),
            new Matrix(5, 15),
            new Matrix(10, 5),
            new Matrix(20, 10),
            new Matrix(25, 20),
        };
        
        new OptimalMatrixChainMultiplier().multiply(chain);
    }
}
