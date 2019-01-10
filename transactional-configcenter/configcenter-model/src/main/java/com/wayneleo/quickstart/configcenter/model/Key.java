package com.wayneleo.quickstart.configcenter.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.wayneleo.quickstart.configcenter.api.KeyDTO;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_key" )
@SuppressWarnings( { "serial", "rawtypes" } )
public class Key extends KeyDTO<Module> implements BaseModel<Key, KeyDTO> {
    @Column( name = "name", length = 32 )
    @Override
    public String getName() {
        return super.getName();
    }

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH } )
    @JoinColumn( name = "module" )
    public Module getModule() {
        return super.getModule();
    }

    @Override
    public Key wrap( KeyDTO dto ) {
        if ( null == dto ) {
            return null;
        }
        this.setId( dto.getId() );
        this.setName( dto.getName() );
        return this;
    }

    public static Set<Key> wrap( Set<KeyDTO> dtos ) {
        if ( null == dtos ) {
            return null;
        }
        Set<Key> set = new HashSet<>();
        Key key = null;
        Iterator<KeyDTO> iterator = dtos.iterator();
        KeyDTO entity = null;
        while ( iterator.hasNext() ) {
            entity = iterator.next();
            key = new Key().wrap( entity );
            set.add( key );
        }
        return set;
    }

    public static Map<Key, String> wrap( Map<KeyDTO, String> dtos ) {
        if ( null == dtos ) {
            return null;
        }
        Map<Key, String> map = new HashMap<>();
        KeyDTO entity = null;
        Key model = null;
        for ( Entry<KeyDTO, String> entry : dtos.entrySet() ) {
            entity = entry.getKey();
            model = new Key().wrap( entity );
            map.put( model, entry.getValue() );
        }
        return map;
    }
}
