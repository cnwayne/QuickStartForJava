package com.wayneleo.quickstart.framework.core.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @RequestMapping( "/" )
    public String index() {
        return "Server is running !";
    }
}
