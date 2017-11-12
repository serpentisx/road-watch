
package app.exceptions;

/**
 *
 * @author Valentin Oliver
 * @date Last updated on 12 November 2017
 * 
 * Hash exception (source: PasswordService)
 */
public class HashException extends Exception {    
    
    public HashException(String message) {
        super(message);
    }
    
    public HashException(String message, Throwable source) {
        super(message, source);
    }
}