
package app.exceptions;

/**
 *
 * @author Valentin Oliver
 * @date Last updated on 12 November 2017
 * 
 * Password verification exception (caused by HashException in PasswordService)
 */
public class PasswordVerificationException extends Exception {
  
    public PasswordVerificationException(Exception source) {
        super("Exception thrown during password verification.", source);
    }
}
