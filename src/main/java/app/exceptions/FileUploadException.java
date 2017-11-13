
package app.exceptions;

/**
 *
 * @author Valentin Oliver
 * @date Last updated on 12 November 2017
 * 
 * File upload exception (caused by IOException from Cloudinary)
 */
public class FileUploadException extends Exception {
  
    public FileUploadException(Exception source) {
        super("File upload failed.", source);
    }
}
