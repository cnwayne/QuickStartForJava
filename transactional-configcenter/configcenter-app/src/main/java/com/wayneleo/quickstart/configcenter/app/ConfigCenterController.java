package com.wayneleo.quickstart.configcenter.app;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wayneleo.quickstart.configcenter.api.ConfigCenterService;
import com.wayneleo.quickstart.configcenter.api.EnvironmentDTO;
import com.wayneleo.quickstart.configcenter.api.ModuleDTO;
import com.wayneleo.quickstart.configcenter.api.ProjectDTO;
import com.wayneleo.quickstart.framework.base.BaseController;

@RestController
@RequestMapping( "/" )
@SuppressWarnings( "rawtypes" )
public class ConfigCenterController extends BaseController {
    @Autowired
    private ConfigCenterService service;

    @GetMapping
    public Properties fetch( String p, String e, String m ) {
        ProjectDTO project = new ProjectDTO<>();
        project.setName( p );
        EnvironmentDTO environment = new EnvironmentDTO<>();
        environment.setName( e );
        Set<ModuleDTO> modules = new HashSet<>();
        ModuleDTO module;
        String[] ms = m.split( ";" );
        for ( int i = 0; i < ms.length; i++ ) {
            module = new ModuleDTO<>();
            module.setName( ms[i].trim() );
            modules.add( module );
        }
        return service.fetchProperties( project );
    }
}
