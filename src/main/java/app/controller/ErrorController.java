/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Error controller handles and renders custom error pages.
 */
@Controller
public class ErrorController {

    // Request mapping for /error. Handles error pages and replaces the default error page with
    // customized error page
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest httpRequest, ModelMap model) {
         
        String errorMsg = "Úúps! Eitthvað fór úrskeiðis";
        int httpErrorCode = 55;
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Úúps! Eitthvað fór úrskeiðis";
                break;
            }
            case 401: {
                errorMsg = "Óheimill aðgangur";
                break;
            }
            case 404: {
                errorMsg = "Úúps! Þú ert týndur á veginum";
                break;
            }
            case 500: {
                errorMsg = "Úúps! Það er vegaframkvæmd í gangi á þessari síðu";
                break;
            }
        }
        model.addAttribute("errorMsg", errorMsg);
        model.addAttribute("errorCode", httpErrorCode);
        return "error";
    }

    /**
     * Get error code if any occurs during http request
     *
     * @param httpRequest
     * @return error code
     */
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
}