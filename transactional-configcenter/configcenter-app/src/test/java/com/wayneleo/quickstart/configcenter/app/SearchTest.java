package com.wayneleo.quickstart.configcenter.app;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import com.wayneleo.quickstart.configcenter.model.Environment;
import com.wayneleo.quickstart.configcenter.model.Key;
import com.wayneleo.quickstart.configcenter.model.Module;
import com.wayneleo.quickstart.configcenter.model.Project;
import com.wayneleo.quickstart.configcenter.model.Property;
import com.wayneleo.quickstart.publish.test.BaseTest;

@FixMethodOrder( MethodSorters.JVM )
public class SearchTest extends BaseTest {
    @Autowired
    private ProjectRepository  projectRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private SearchService      searchService;

    @Override
    public void beforeTest() {
        Project project = new Project();
        project.setName( "searchService" );
        if ( projectRepository.exists( Example.of( project ) ) ) {
            return;
        }
        project.getModules().add( new Module() );
        Module module = project.getModules().iterator().next();
        module.setName( "sample" );
        module.getKeys().add( new Key() );
        Key key = module.getKeys().iterator().next();
        key.setName( "key-1" );
        module.getEnvironments().add( new Environment() );
        Environment environment = module.getEnvironments().iterator().next();
        environment.setName( "dev-wayne" );
        projectRepository.save( project );
        Property prop = new Property();
        prop.setValue( "value-1" );
        prop.setKey( key );
        prop.setEnvironment( environment );
        propertyRepository.save( prop );
    }

    @Override
    public void afterTest() {}

    @Test
    public void 注入() {
        Assert.assertNotNull( searchService );
    }

    @Test
    public void 搜索() {
        Project project = new Project();
        project.setName( "searchService" );
        Module module = new Module();
        module.setName( "sample" );
        module = searchService.search( project, module );
        Assert.assertNotNull( module );
    }
}
