package com.wayneleo.quickstart.framework.apidocs;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocsController {
    /*
    @RequestMapping( "/docs" )
    public ModelAndView docs( HttpServletResponse response ) {
        ModelAndView model = new ModelAndView( "/swagger-ui.html" );
        return model;
    }
    */
    @RequestMapping( "/docs" )
    public void docs( HttpServletResponse response ) throws IOException {
        response.sendRedirect( "/swagger-ui.html" );
    }
}
