package com.wayneleo.quickstart.configcenter.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Entity;
import com.wayneleo.quickstart.configcenter.api.ModuleEntity;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_module" )
@SuppressWarnings( { "serial", "rawtypes", "unchecked" } )
public class Module extends ModuleEntity<Key, Environment> implements BaseModel<Module, ModuleEntity> {
    @Override
    public Module wrap( ModuleEntity module ) {
        if ( null == module ) {
            return null;
        }
        this.setId( module.getId() );
        this.setName( module.getName() );
        this.setKeys( Key.wrap( module.getKeys() ) );
        this.setMapping( Key.wrap( module.getMapping() ) );
        return this;
    }

    public static Set<Module> wrap( Set<ModuleEntity> modules ) {
        if ( null == modules ) {
            return null;
        }
        Set<Module> set = new HashSet<>();
        Iterator<ModuleEntity> iterator = modules.iterator();
        Module module = null;
        ModuleEntity entity = null;
        while ( iterator.hasNext() ) {
            entity = iterator.next();
            module = new Module();
            module.setId( entity.getId() );
            module.setName( entity.getName() );
            module.setKeys( Key.wrap( entity.getKeys() ) );
            module.setMapping( Key.wrap( entity.getMapping() ) );
            set.add( module );
        }
        return set;
    }
}
