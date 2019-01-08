package com.wayneleo.quickstart.configcenter.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.transaction.Transactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.wayneleo.quickstart.configcenter.api.EnvironmentEntity;
import com.wayneleo.quickstart.configcenter.api.KeyEntity;
import com.wayneleo.quickstart.configcenter.api.ModuleEntity;
import com.wayneleo.quickstart.configcenter.api.ProjectEntity;
import com.wayneleo.quickstart.configcenter.model.Environment;
import com.wayneleo.quickstart.configcenter.model.Key;
import com.wayneleo.quickstart.configcenter.model.Module;
import com.wayneleo.quickstart.configcenter.model.Project;
import com.wayneleo.quickstart.publish.test.BaseTest;

public class BasicTest extends BaseTest {
    @Autowired
    private EnvironmentRepository environmentRepository;
    @Autowired
    private KeyRepository         keyRepository;
    @Autowired
    private ModuleRepository      moduleRepository;
    @Autowired
    private ProjectRepository     projectRepository;

    @Override
    public void beforeTest() {
        if ( !projectRepository.findAll().isEmpty() ) {
            return;
        }
        Project project = new Project();
        project.setName( "quickstart" );
        projectRepository.save( project );
        Environment environment = new Environment();
        environment.setName( "dev-wayne" );
        environment.setProject( project );
        environmentRepository.save( environment );
        Module module = new Module();
        module.setName( "sample" );
        module.setEnvironment( environment );
        moduleRepository.save( module );
        Key key = new Key();
        key.setName( "key-1" );
        key.setModule( module );
        keyRepository.save( key );
        Map<Key, String> map = new HashMap<>();
        map.put( key, "value-1" );
        module.setMapping( map );
        moduleRepository.save( module );
    }

    @Override
    public void afterTest() {
        /*-- drop database tables 
          DROP TABLE IF EXISTS test.configcenter_properties;
          DROP TABLE IF EXISTS test.configcenter_key;
          DROP TABLE IF EXISTS test.configcenter_module;
          DROP TABLE IF EXISTS test.configcenter_environment;
          DROP TABLE IF EXISTS test.configcenter_project;
         */
    }

    @Test
    @Transactional
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    public void testWrapping() {
        ProjectEntity project = new ProjectEntity();
        project.setName( "configcenter" );
        EnvironmentEntity environment = new EnvironmentEntity<>();
        environment.setName( "junit-wayne" );
        environment.setProject( project );
        ModuleEntity module = new ModuleEntity<>();
        module.setName( "test" );
        module.setEnvironment( environment );
        KeyEntity key = new KeyEntity<>();
        key.setName( "key-2" );
        key.setModule( module );
        Map<KeyEntity, String> mapping = new HashMap<>();
        mapping.put( key, "value-2" );
        module.setMapping( mapping );
        projectRepository.save( new Project().wrap( project ) );
    }

    @Test
    @Transactional
    public void tsetFinding() {
        Project project = projectRepository.findAll().get( 0 );
        Iterator<Environment> envIterator = project.getEnvironments().iterator();
        Environment environment = envIterator.next();
        Iterator<Module> modIterator = environment.getModules().iterator();
        Module module = modIterator.next();
        System.out.println( Arrays.toString( module.getKeys().toArray() ) );
        Map<Key, String> mapping = module.getMapping();
        System.out.println( mapping );
    }
}
