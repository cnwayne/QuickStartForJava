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
import com.wayneleo.quickstart.configcenter.api.EnvironmentDTO;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_environment" )
@SuppressWarnings( { "serial", "rawtypes", "unchecked" } )
public class Environment extends EnvironmentDTO<Property, Module> implements BaseModel<Environment, EnvironmentDTO> {
    @Column( name = "name", length = 50 )
    public String getName() {
        return super.getName();
    }

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinColumn( name = "environment" )
    @Override
    public Set<Property> getProps() {
        return super.getProps();
    }

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH } )
    @JoinColumn( name = "module" )
    @Override
    public Module getModule() {
        return super.getModule();
    }

    @Override
    public Environment wrap( EnvironmentDTO dto ) {
        if ( null == dto ) {
            return null;
        }
        this.setId( dto.getId() );
        this.setName( dto.getName() );
        this.setProps( Property.wrap( dto.getProps() ) );
        return this;
    }

    public static Set<Environment> wrap( Set<EnvironmentDTO> dtos ) {
        if ( null == dtos ) {
            return null;
        }
        Set<Environment> set = new HashSet<>();
        Environment environment = null;
        Iterator<EnvironmentDTO> iterator = dtos.iterator();
        EnvironmentDTO entity = null;
        while ( iterator.hasNext() ) {
            entity = iterator.next();
            environment = new Environment().wrap( entity );
            set.add( environment );
        }
        return set;
    }
}
