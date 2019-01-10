package com.wayneleo.quickstart.configcenter.app;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.wayneleo.quickstart.configcenter.model.Environment;
import com.wayneleo.quickstart.configcenter.model.Key;
import com.wayneleo.quickstart.configcenter.model.Module;
import com.wayneleo.quickstart.configcenter.model.Project;
import com.wayneleo.quickstart.configcenter.model.Property;
import com.wayneleo.quickstart.framework.base.BaseService;

@Service
public class SearchService extends BaseService {
    private ProjectRepository     projectRepository;
    private EnvironmentRepository environmentRepository;
    private ModuleRepository      moduleRepository;
    private KeyRepository         keyRepository;
    private PropertyRepository    propertyRepository;

    public SearchService( ProjectRepository projectRepository, EnvironmentRepository environmentRepository, ModuleRepository moduleRepository, KeyRepository keyRepository, PropertyRepository propertyRepository ) {
        super();
        this.projectRepository = projectRepository;
        this.environmentRepository = environmentRepository;
        this.moduleRepository = moduleRepository;
        this.keyRepository = keyRepository;
        this.propertyRepository = propertyRepository;
    }

    public Project search( Project project ) {
        if ( null == project ) return null;
        project = projectRepository.findOne( Example.of( project ) );
        return project;
    }

    public Module search( Project project, Module module ) {
        if ( ( null == project ) || ( null == module ) ) return null;
        project = search( project );
        if ( null == project ) return null;
        module.setProject( project );
        module = moduleRepository.findOne( Example.of( module ) );
        return module;
    }

    public Key search( Project project, Module module, Key key ) {
        if ( ( null == project ) || ( null == module ) || ( null == key ) ) return null;
        module = search( project, module );
        if ( null == module ) return null;
        key.setModule( module );
        key = keyRepository.findOne( Example.of( key ) );
        return key;
    }

    public Environment search( Project project, Module module, Environment environment ) {
        if ( ( null == project ) || ( null == module ) || ( null == environment ) ) return null;
        module = search( project, module );
        if ( null == module ) return null;
        environment.setModule( module );
        environment = environmentRepository.findOne( Example.of( environment ) );
        return environment;
    }

    public Property search( Project project, Environment environment, Module module, Key key, Property property ) {
        if ( ( null == project ) || ( null == environment ) || ( null == module ) || ( null == key ) || ( null == property ) ) return null;
        key = search( project, module, key );
        environment = search( project, module, environment );
        if ( ( null == key ) || ( null == environment ) ) return null;
        property.setKey( key );
        property.setEnvironment( environment );
        property = propertyRepository.findOne( Example.of( property ) );
        return property;
    }
}
