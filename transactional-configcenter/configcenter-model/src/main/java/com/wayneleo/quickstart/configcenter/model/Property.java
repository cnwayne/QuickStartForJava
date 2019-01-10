package com.wayneleo.quickstart.configcenter.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.wayneleo.quickstart.configcenter.api.PropertyDTO;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_properties" )
@SuppressWarnings( { "serial", "rawtypes" } )
public class Property extends PropertyDTO<Key, Environment> implements BaseModel<Property, PropertyDTO> {
    @Column( name = "prop_value", length = 500 )
    @Override
    public String getValue() {
        return super.getValue();
    }

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH } )
    @JoinColumn( name = "prop_key" )
    @Override
    public Key getKey() {
        return super.getKey();
    }

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH } )
    @JoinColumn( name = "environment" )
    @Override
    public Environment getEnvironment() {
        return super.getEnvironment();
    }

    @Override
    public Property wrap( PropertyDTO dto ) {
        this.setId( dto.getId() );
        this.setValue( dto.getValue() );
        this.setKey( new Key().wrap( dto.getKey() ) );
        this.setEnvironment( new Environment().wrap( dto.getEnvironment() ) );
        return this;
    }

    public static Set<Property> wrap( Set<PropertyDTO> dtos ) {
        if ( null == dtos ) {
            return null;
        }
        Set<Property> set = new HashSet<>();
        Property property = null;
        Iterator<PropertyDTO> iterator = dtos.iterator();
        PropertyDTO entity = null;
        while ( iterator.hasNext() ) {
            entity = iterator.next();
            property = new Property().wrap( entity );
            set.add( property );
        }
        return set;
    }
}
