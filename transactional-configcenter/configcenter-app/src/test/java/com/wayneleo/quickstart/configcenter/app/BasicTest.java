package com.wayneleo.quickstart.configcenter.app;

import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import com.alibaba.druid.pool.DruidDataSource;
import com.wayneleo.quickstart.configcenter.api.EnvironmentDTO;
import com.wayneleo.quickstart.configcenter.api.KeyDTO;
import com.wayneleo.quickstart.configcenter.api.ModuleDTO;
import com.wayneleo.quickstart.configcenter.api.ProjectDTO;
import com.wayneleo.quickstart.configcenter.api.PropertyDTO;
import com.wayneleo.quickstart.configcenter.model.Environment;
import com.wayneleo.quickstart.configcenter.model.Key;
import com.wayneleo.quickstart.configcenter.model.Module;
import com.wayneleo.quickstart.configcenter.model.Project;
import com.wayneleo.quickstart.configcenter.model.Property;
import com.wayneleo.quickstart.publish.test.BaseTest;

@FixMethodOrder( MethodSorters.JVM )
@SuppressWarnings( { "rawtypes", "unchecked" } )
public class BasicTest extends BaseTest {
    @Autowired
    private DataSource            dataSource;
    @Autowired
    private ProjectRepository     projectRepository;
    @Autowired
    private EnvironmentRepository environmentRepository;
    @Autowired
    private ModuleRepository      moduleRepository;
    @Autowired
    private KeyRepository         keyRepository;
    @Autowired
    private PropertyRepository    propertyRepository;

    @Override
    public void beforeTest() {}

    @Override
    public void afterTest() {
        /*
        try {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit( false );
            Statement statement = conn.createStatement();
            statement.execute( "DROP TABLE IF EXISTS test.configcenter_properties" );
            statement.execute( "DROP TABLE IF EXISTS test.configcenter_environment" );
            statement.execute( "DROP TABLE IF EXISTS test.configcenter_key" );
            statement.execute( "DROP TABLE IF EXISTS test.configcenter_module" );
            statement.execute( "DROP TABLE IF EXISTS test.configcenter_project" );
            conn.commit();
            conn.close();
        }
        catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
         */
    }

    @Test
    public void 注入() {
        Assert.assertNotNull( dataSource );
        Assert.assertEquals( DruidDataSource.class.getName(), dataSource.getClass().getName() );
        Assert.assertNotNull( projectRepository );
        Assert.assertNotNull( environmentRepository );
        Assert.assertNotNull( moduleRepository );
        Assert.assertNotNull( keyRepository );
        Assert.assertNotNull( propertyRepository );
    }

    @Test
    public void 直存() {
        Project project = new Project();
        project.setName( "quickstart" );
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

    @Test
    public void 转换() {
        ProjectDTO project = new ProjectDTO();
        project.setName( "configcenter" );
        project.getModules().add( new ModuleDTO() );
        ModuleDTO module = ( ModuleDTO ) project.getModules().iterator().next();
        module.setName( "test" );
        module.getKeys().add( new Key() );
        KeyDTO key = ( KeyDTO ) module.getKeys().iterator().next();
        key.setName( "key-2" );
        module.getEnvironments().add( new EnvironmentDTO() );
        EnvironmentDTO environment = ( EnvironmentDTO ) module.getEnvironments().iterator().next();
        environment.setName( "junit-wayne" );
        Project projectModel = projectRepository.save( new Project().wrap( project ) );
        PropertyDTO prop = new PropertyDTO<>();
        prop.setValue( "value-2" );
        prop.setKey( projectModel.getModules().iterator().next().getKeys().iterator().next() );
        prop.setEnvironment( projectModel.getModules().iterator().next().getEnvironments().iterator().next() );
        propertyRepository.save( new Property().wrap( prop ) );
    }

    @Test
    @Transactional
    public void 查询() {
        Project project = projectRepository.findAll().get( 0 );
        Module module = project.getModules().iterator().next();
        Key key = module.getKeys().iterator().next();
        Environment environment = module.getEnvironments().iterator().next();
        Property prop = new Property();
        prop.setKey( key );
        prop.setEnvironment( environment );
        List<Property> properties = propertyRepository.findAll( Example.of( prop ) );
        System.out.println( Arrays.toString( properties.toArray() ) );
    }
}
