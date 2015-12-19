package net.coderodde.matrix;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    private static final double E = 1e-4;
    private Matrix m;
    
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
    }

    @Test
    public void testEquals() {
        
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testToString() {
        m = new Matrix(3, 4);
        m.write(2, 2, 3.14);
        m.write(2, 3, 0.0002);
        m.write(0, 1, 268.9);
        System.out.println(m);
    }
    
}
