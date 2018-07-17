package com.wayneleo.quickstart.framework.apidocs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DocsController {
    @RequestMapping( "/docs" )
    public void docs( HttpServletResponse response ) throws IOException {
        response.sendRedirect( "/swagger-ui.html" );
    }
}
