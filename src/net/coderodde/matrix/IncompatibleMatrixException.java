package net.coderodde.matrix;

/**
 * This class implements an exception that is thrown whenever multiplying two
 * <b>incompatible</b> matrices. Whenever computing the product {@code AB}, the
 * two matrices {@code A} and {@code B} are incompatible if and only if the 
 * number of rows in {@code A} does not equal the amount of columns of 
 * {@code B}.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 19, 2015)
 */
public class IncompatibleMatrixException extends RuntimeException {
    
    public IncompatibleMatrixException(String message) {
        super(message);
    }
}
