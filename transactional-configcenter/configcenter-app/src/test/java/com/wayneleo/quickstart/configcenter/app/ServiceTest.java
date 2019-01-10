package com.wayneleo.quickstart.configcenter.app;

import java.util.Properties;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import com.wayneleo.quickstart.configcenter.api.ConfigCenterService;
import com.wayneleo.quickstart.configcenter.api.ModuleDTO;
import com.wayneleo.quickstart.configcenter.api.ProjectDTO;
import com.wayneleo.quickstart.configcenter.model.Environment;
import com.wayneleo.quickstart.configcenter.model.Key;
import com.wayneleo.quickstart.configcenter.model.Module;
import com.wayneleo.quickstart.configcenter.model.Project;
import com.wayneleo.quickstart.configcenter.model.Property;
import com.wayneleo.quickstart.publish.test.BaseTest;

@SuppressWarnings( { "rawtypes", "unchecked" } )
public class ServiceTest extends BaseTest {
    @Autowired
    private ConfigCenterService service;
    @Autowired
    private ProjectRepository   projectRepository;
    @Autowired
    private PropertyRepository  propertyRepository;

    @Override
    public void beforeTest() {
        Project project = new Project();
        project.setName( "readProperties" );
        if ( projectRepository.exists( Example.of( project ) ) ) {
            return;
        }
        project.getModules().add( new Module() );
        Module module = project.getModules().iterator().next();
        module.setName( "sample" );
        Key key1 = new Key();
        key1.setName( "key-1" );
        Key key2 = new Key();
        key2.setName( "key-2" );
        module.getKeys().add( key1 );
        module.getKeys().add( key2 );
        module.getEnvironments().add( new Environment() );
        Environment environment = module.getEnvironments().iterator().next();
        environment.setName( "dev-wayne" );
        projectRepository.save( project );
        Property prop = new Property();
        prop.setValue( "value-1" );
        prop.setKey( key1 );
        prop.setEnvironment( environment );
        propertyRepository.save( prop );
        prop = new Property();
        prop.setValue( "value-2" );
        prop.setKey( key2 );
        prop.setEnvironment( environment );
        propertyRepository.save( prop );
    }

    @Override
    public void afterTest() {}

    @Test
    @Transactional
    public void fetchProperties() {
        ProjectDTO project = new ProjectDTO();
        project.setName( "readProperties" );
        ModuleDTO module = new ModuleDTO();
        module.setName( "sample" );
        project.getModules().add( module );
        Environment environment = new Environment();
        environment.setName( "dev-wayne" );
        module.getEnvironments().add( environment );
        Properties properties = service.fetchProperties( project );
        Assert.assertTrue( !properties.isEmpty() );
        System.out.println( properties );
    }
}
