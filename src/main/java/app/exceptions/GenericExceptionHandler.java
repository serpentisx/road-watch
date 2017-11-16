
package app.exceptions;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailSendException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ValentinOliver
 * @date Last updated on 15 November 2017
 * 
 * Generic (global) exception handler
 */
@ControllerAdvice
public class GenericExceptionHandler {
  
    private static final Logger LOGGER = //
            LoggerFactory.getLogger(GenericExceptionHandler.class);
    
    private static final String DEFAULT_ERROR_VIEW = "errorPage";
    private static final String UPLOAD_ERROR = // 
            "Villa við að upphala stafrænni mynd, vinsamlegast reyndu aftur síðar";
    
    /**
     * Exception handler for file upload exception
     * 
     * @param req   http-request
     * @param e     the exception
     * @param model model used for rendering
     * @return      string representing view to be rendered
     */
    @ExceptionHandler (FileUploadException.class)
    public String fileUploadFailed(HttpServletRequest req,
            FileUploadException e, Model model) {
        
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Exception: " + e.getMessage() + " Caused by:" + e.getCause().toString());
        model.addAttribute("message", UPLOAD_ERROR);
        return "new_post";
    }
    
    /**
     * Exception handler for road-not-found exception
     * 
     * @param req   http-request
     * @param e     the exception
     * @param model model used for rendering
     * @return      string representing view to be rendered
     */
    @ExceptionHandler (RoadNotFoundException.class)
    public String roadNotFound(HttpServletRequest req,
            RoadNotFoundException e, Model model) {
      
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Exception: " + e.getMessage() + " Caused by: " + e.getCause().toString());
        model.addAttribute("message", e.getRoadName() + " fannst ekki í gagnagrunni, reyndu aftur.");
        return "new_post";
    }
    
    /**
     * Database exception handler
     * 
     * @param req   http-request
     * @param e     the exception
     * @param model model used for rendering
     * @return      string representing view to be rendered
     */
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError(HttpServletRequest req, 
            Exception e, Model model) {
        
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Database exception: " + e.toString());
        model.addAttribute("errorCode", "");
        model.addAttribute("errorMsg", "Gagnagrunnsþjónninn skilaði villu, reyndu aftur síðar");
        return DEFAULT_ERROR_VIEW;
    }
    
    /**
     * Mail-dispatching exception handler.
     * 
     * @param req   http-request
     * @param e     the exception
     * @param model model used for rendering
     * @return      string representing view to be rendered
     */
    @ExceptionHandler(MailSendException.class)
    public String mailSendError(HttpServletRequest req, 
            Exception e, Model model) {
      
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Mail exception: " + e.toString());
        model.addAttribute("errorCode", "");
        model.addAttribute("errorMsg", "Ekki tókst að senda skilaboðin, reyndu aftur síðar.");
        System.out.println("ERROR HANDLER");
        return DEFAULT_ERROR_VIEW;
    }
    
    /**
     * Exception-handler for all exceptions except those annotated with
     * ResponseStatus, as well as those that have already been handled 
     *
     * @param req         http-request
     * @param exception   the exception
     * @return            the model-and-view to render
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultHandler(HttpServletRequest req, Exception exception) 
      throws Exception {

        // Endurframköllum villu ef Exception-klasi er merktur með @ResponseStatus
        if (AnnotationUtils.findAnnotation(exception.getClass(), 
                ResponseStatus.class) != null) {
            throw exception;
        }

        LOGGER.error("Unknown error: " + req.getRequestURL());
        
        Throwable source = exception.getCause();
        String cause = "No cause found.";
        if (source != null) cause = source.toString();
        LOGGER.error("Exception: " + exception.toString() + " Caused by: " + cause);
        
        // Annars birtum við sjálfgefna villusíðu
        ModelAndView mav = new ModelAndView();
        
        mav.addObject("errorMsg", "Óþekkt villa");

        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
