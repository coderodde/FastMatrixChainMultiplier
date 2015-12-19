package net.coderodde.matrix;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    private static final double E = 1e-4;
    private Matrix m;
    
    @Test
    public void testSmallestMatrixDoesNotThrow() {
        new Matrix(1, 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMatrixThrowsOnNonPositiveWidth() {
        new Matrix(0, 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMatrixThrowsOnNonPositiveHeight() {
        new Matrix(1, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMatrixThrowsOnNonPositiveWidthAndHeight() {
        new Matrix(0, 0);
    }
    
    @Test(expected = IncompatibleMatrixException.class)
    public void testMatrixThrowsOnMultiplyintIncompatibleMatrix() {
        new Matrix(3, 2).multiply(new Matrix(2, 2));
    }
    
    @Test
    public void testGetWidth() {
        m = new Matrix(2, 3);
        assertEquals(2, m.getWidth());
        
        m = new Matrix(5, 3);
        assertEquals(5, m.getWidth());
        
        m = new Matrix(5, 4);
        assertEquals(5, m.getWidth());
    }

    @Test
    public void testGetHeight() {
        m = new Matrix(4, 6);
        assertEquals(6, m.getHeight());
        
        m = new Matrix(2, 6);
        assertEquals(6, m.getHeight());
        
        m = new Matrix(2, 5);
        assertEquals(5, m.getHeight());
    }

    @Test
    public void testRead() {
        m = new Matrix(3, 3);
        
        for (int y = 0; y < m.getHeight(); ++y) {
            for (int x = 0; x < m.getWidth(); ++x) {
                assertEquals(0.0, m.read(x, y), E);
            }
        }
        
        m.write(1, 1, 4.0);
        assertEquals(4.0, m.read(1, 1), E);
        assertEquals(0.0, m.read(2, 2), E);
        
        m.write(2, 2, 5.0);
        assertEquals(4.0, m.read(1, 1), E);
        assertEquals(5.0, m.read(2, 2), E);
        
        m.write(2, 2, 9.5);
        assertEquals(4.0, m.read(1, 1), E);
        assertEquals(9.5, m.read(2, 2), E);
        
        m.write(1, 1, 2.0);
        assertEquals(2.0, m.read(1, 1), E);
        assertEquals(9.5, m.read(2, 2), E);
    }

    @Test
    public void testWrite() {
        m = new Matrix(4, 3);
        assertEquals(0.0, m.read(3, 1), E);
        
        m.write(3, 1, 4.6);
        assertEquals(4.6, m.read(3, 1), E);
        
        m.write(3, 1, 2.6);
        assertEquals(2.6, m.read(3, 1), E);
    }

    @Test
    public void testMultiply() {
        // 1, 2,
        // 3, 4
        // 5, 6
        m = new Matrix(2, 3);
        
        int num = 1;
        
        for (int y = 0; y < m.getHeight(); ++y) {
            for (int x = 0; x < m.getWidth(); ++x) {
                m.write(x, y, num++);
            } 
        }
        
        // 3,  2
        // 1, -1
        
        Matrix m2 = new Matrix(2, 2);
        
        m2.write(0, 0, 3);
        m2.write(1, 0, 2);
        m2.write(0, 1, 1);
        m2.write(1, 1, -1);
        
        
                // 3, 2
                // 1, -1
        // 1, 2              5,  0
        // 3, 4     x      = 13, 2      
        // 5, 6              21, 4
        Matrix result = m.multiply(m2);
        
        assertEquals(2, result.getWidth());
        assertEquals(3, result.getHeight());
        
        assertEquals(5.0 , result.read(0, 0), E);
        assertEquals(0.0 , result.read(1, 0), E);
        assertEquals(13.0, result.read(0, 1), E);
        assertEquals(2.0 , result.read(1, 1), E);
        assertEquals(21.0, result.read(0, 2), E);
        assertEquals(4.0 , result.read(1, 2), E);
    }

    @Test
    public void testEquals() {
        m = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 3);
        
        assertFalse(m.equals(m2));
        
        m2 = new Matrix(3, 2);
        assertFalse(m.equals(m2));
        
        m2 = new Matrix(2, 2);
        assertEquals(m, m2);
        
        m.write(1, 0, 3.14);
        assertFalse(m.equals(m2));
        
        m2.write(1, 0, 3.14);
        assertEquals(m, m2);
        
        m2.write(0, 1, 2.78);
        assertFalse(m.equals(m2));
        
        assertEquals(m, m);
        assertEquals(m2, m2);
        
        assertFalse(m.equals(null));
        assertFalse(m.equals(new Object()));
    }

    @Test
    public void testHashCode() {
        m = new Matrix(3, 3);
        m.write(1, 1, 5.0);
        
        Matrix m2 = new Matrix(3, 3);
       
        assertFalse(m.hashCode() == m2.hashCode());
        
        m2.write(1, 1, 4.0);
        assertFalse(m.hashCode() == m2.hashCode());
        
        m2.write(1, 1, 5.0);
        assertEquals(m.hashCode(), m2.hashCode());
    }

    @Test
    public void testToString() {
        System.out.println("toString():");
        
        m = new Matrix(3, 4);
        m.write(2, 2, 3.14);
        m.write(2, 3, 0.0002);
        m.write(0, 1, 268.9);
        
        System.out.println(m);
    }
}
