
package app.exceptions;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ValentinOliver
 * @date Last updated on 12 November 2017
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
     * @return 
     */
    @ExceptionHandler (FileUploadException.class)
    public String fileUploadFailed(HttpServletRequest req,
            FileUploadException e, ModelMap model) {
        
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
     * @return 
     */
    @ExceptionHandler (RoadNotFoundException.class)
    public String roadNotFound(HttpServletRequest req,
            RoadNotFoundException e, ModelMap model) {
      
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Exception: " + e.getMessage() + " Caused by: " + e.getCause().toString());
        model.addAttribute("message", e.getRoadName() + " fannst ekki í gagnagrunni, reyndu aftur.");
        return "new_post";
    }
    
    /**
     * Exception handler for road-not-found exception
     * 
     * @param req   http-request
     * @param e     the exception
     * @param model model used for rendering
     * @return 
     */
    @ExceptionHandler (HashException.class)
    public String passwordHashingException(HttpServletRequest req,
            HashException e, ModelMap model) {
      
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Exception: " + e.getMessage() + " Caused by: " + e.getCause().toString());
        return "";
    }
    
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError(HttpServletRequest req, 
            Exception e, ModelMap model) {
        
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Database exception: " + e.toString());
        model.addAttribute("errorCode", "");
        model.addAttribute("errorMessage", "Gagnagrunnsþjónninn skilaði villu, reyndu aftur síðar");
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
        
        // Annars birtum við sjálfgefna villusíðu
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorCode", "");
        mav.addObject("errorMessage", "Óþekkt villa");

        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
