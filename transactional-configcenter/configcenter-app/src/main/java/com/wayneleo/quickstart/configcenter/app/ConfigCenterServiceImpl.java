package com.wayneleo.quickstart.configcenter.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.wayneleo.quickstart.configcenter.api.ConfigCenterService;
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
import com.wayneleo.quickstart.framework.base.BaseService;

@Service
@SuppressWarnings( "rawtypes" )
public class ConfigCenterServiceImpl extends BaseService implements ConfigCenterService {
    private final ProjectRepository     projectRepository;
    private final EnvironmentRepository environmentRepository;
    private final ModuleRepository      moduleRepository;
    private final KeyRepository         keyRepository;
    private final PropertyRepository    propertyRepository;
    private final SearchService         searchService;

    public ConfigCenterServiceImpl( ProjectRepository projectRepository, EnvironmentRepository environmentRepository, ModuleRepository moduleRepository, KeyRepository keyRepository, PropertyRepository propertyRepository, SearchService searchService ) {
        super();
        this.projectRepository = projectRepository;
        this.environmentRepository = environmentRepository;
        this.moduleRepository = moduleRepository;
        this.keyRepository = keyRepository;
        this.propertyRepository = propertyRepository;
        this.searchService = searchService;
    }

    @Override
    public ProjectDTO save( ProjectDTO dto ) {
        if ( null == dto ) {
            return null;
        }
        return projectRepository.save( new Project().wrap( dto ) );
    }

    @Override
    public ModuleDTO save( ModuleDTO dto ) {
        if ( null == dto ) {
            return null;
        }
        return moduleRepository.save( new Module().wrap( dto ) );
    }

    @Override
    public KeyDTO save( KeyDTO dto ) {
        if ( null == dto ) {
            return null;
        }
        return keyRepository.save( new Key().wrap( dto ) );
    }

    @Override
    public EnvironmentDTO save( EnvironmentDTO dto ) {
        if ( null == dto ) {
            return null;
        }
        return environmentRepository.save( new Environment().wrap( dto ) );
    }

    @Override
    public void save( Set<PropertyDTO> dtos ) {
        if ( ( null == dtos ) || ( dtos.size() < 1 ) ) {
            return;
        }
        propertyRepository.save( Property.wrap( dtos ) );
    }

    @Override
    public void delete( ProjectDTO dto ) {
        projectRepository.delete( new Project().wrap( dto ) );
    }

    @Override
    public void delete( ModuleDTO dto ) {
        moduleRepository.delete( new Module().wrap( dto ) );
    }

    @Override
    public void delete( KeyDTO dto ) {
        keyRepository.delete( new Key().wrap( dto ) );
    }

    @Override
    public void delete( EnvironmentDTO dto ) {
        environmentRepository.delete( new Environment().wrap( dto ) );
    }

    @Override
    public void delete( PropertyDTO dto ) {
        propertyRepository.delete( new Property().wrap( dto ) );
    }

    @Override
    public void delete( Set<PropertyDTO> dtos ) {
        propertyRepository.deleteInBatch( Property.wrap( dtos ) );
    }

    @Override
    public Set<Project> fetchProjects() {
        return new HashSet<>( projectRepository.findAll() );
    }

    @Override
    public Set<? extends ModuleDTO> fetchModules( ProjectDTO dto ) {
        Project project = projectRepository.findOne( Example.of( new Project().wrap( dto ) ) );
        if ( null == project ) {
            return null;
        }
        return project.getModules();
    }

    @Override
    public Set<? extends KeyDTO> fetchKeys( ModuleDTO dto ) {
        Module module = moduleRepository.findOne( Example.of( new Module().wrap( dto ) ) );
        if ( null == module ) {
            return null;
        }
        return module.getKeys();
    }

    @Override
    public Set<? extends EnvironmentDTO> fetchEnvironments( ModuleDTO dto ) {
        Module module = moduleRepository.findOne( Example.of( new Module().wrap( dto ) ) );
        return module.getEnvironments();
    }

    @Override
    public Properties fetchProperties( ProjectDTO dto ) {
        Properties properties = new Properties();
        if ( ( null == dto ) || ( dto.getModules().size() < 1 ) ) {
            return properties;
        }
        Project project = new Project().wrap( dto );
        Set<Module> modules = project.getModules();
        if ( modules.size() < 1 ) {
            return properties;
        }
        Environment environment = null;
        List<Property> props = new ArrayList<>();
        Property example = null;
        for ( Module module : modules ) {
            module = searchService.search( project, module );
            Set<Key> keys = module.getKeys();
            environment = searchService.search( project, module, environment );
            for ( Key key : keys ) {
                example = new Property();
                example.setKey( key );
                example.setEnvironment( environment );
                props.add( propertyRepository.findOne( Example.of( example ) ) );
            }
        }
        for ( Property prop : props ) {
            properties.put( prop.getKey().getName(), prop.getValue() );
        }
        return properties;
    }
}
