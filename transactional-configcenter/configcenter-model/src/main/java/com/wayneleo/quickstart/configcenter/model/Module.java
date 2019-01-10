package com.wayneleo.quickstart.configcenter.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.wayneleo.quickstart.configcenter.api.ModuleDTO;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_module" )
@SuppressWarnings( { "serial", "rawtypes", "unchecked" } )
public class Module extends ModuleDTO<Key, Environment, Project> implements BaseModel<Module, ModuleDTO> {
    @Column( name = "name", length = 50 )
    @Override
    public String getName() {
        return super.getName();
    }

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinColumn( name = "module" )
    @Override
    public Set<Key> getKeys() {
        return super.getKeys();
    }

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinColumn( name = "module" )
    @Override
    public Set<Environment> getEnvironments() {
        return super.getEnvironments();
    }

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH } )
    @JoinColumn( name = "project" )
    @Override
    public Project getProject() {
        return super.getProject();
    }

    @Override
    public Module wrap( ModuleDTO dto ) {
        if ( null == dto ) {
            return null;
        }
        this.setId( dto.getId() );
        this.setName( dto.getName() );
        this.setKeys( Key.wrap( dto.getKeys() ) );
        this.setEnvironments( Environment.wrap( dto.getEnvironments() ) );
        return this;
    }

    public static Set<Module> wrap( Set<ModuleDTO> dtos ) {
        if ( null == dtos ) {
            return null;
        }
        Set<Module> set = new HashSet<>();
        Iterator<ModuleDTO> iterator = dtos.iterator();
        Module module = null;
        ModuleDTO entity = null;
        while ( iterator.hasNext() ) {
            entity = iterator.next();
            module = new Module().wrap( entity );
            set.add( module );
        }
        return set;
    }
}
