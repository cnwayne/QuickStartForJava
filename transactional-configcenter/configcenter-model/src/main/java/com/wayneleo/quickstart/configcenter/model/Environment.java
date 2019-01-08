package com.wayneleo.quickstart.configcenter.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Entity;
import com.wayneleo.quickstart.configcenter.api.EnvironmentEntity;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_environment" )
@SuppressWarnings( { "serial", "rawtypes", "unchecked" } )
public class Environment extends EnvironmentEntity<Module, Project> implements BaseModel<Environment, EnvironmentEntity> {
    @Override
    public Environment wrap( EnvironmentEntity environment ) {
        if ( null == environment ) {
            return null;
        }
        this.setId( environment.getId() );
        this.setName( environment.getName() );
        this.setModules( Module.wrap( environment.getModules() ) );
        return this;
    }

    public static Set<Environment> wrap( Set<EnvironmentEntity> environments ) {
        if ( null == environments ) {
            return null;
        }
        Set<Environment> set = new HashSet<>();
        Environment environment = null;
        Iterator<EnvironmentEntity> iterator = environments.iterator();
        EnvironmentEntity entity = null;
        while ( iterator.hasNext() ) {
            environment = new Environment();
            entity = iterator.next();
            environment.setId( entity.getId() );
            environment.setName( entity.getName() );
            environment.setModules( Module.wrap( entity.getModules() ) );
            set.add( environment );
        }
        return set;
    }
}
