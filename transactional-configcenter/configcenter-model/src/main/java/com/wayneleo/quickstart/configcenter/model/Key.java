package com.wayneleo.quickstart.configcenter.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.Entity;
import com.wayneleo.quickstart.configcenter.api.KeyEntity;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_key" )
@SuppressWarnings( { "serial", "rawtypes" } )
public class Key extends KeyEntity<Module> implements BaseModel<Key, KeyEntity> {
    @Override
    public Key wrap( KeyEntity key ) {
        if ( null == key ) {
            return null;
        }
        this.setId( key.getId() );
        this.setName( key.getName() );
        return this;
    }

    public static Set<Key> wrap( Set<KeyEntity> keys ) {
        if ( null == keys ) {
            return null;
        }
        Set<Key> set = new HashSet<>();
        Key key = null;
        Iterator<KeyEntity> iterator = keys.iterator();
        KeyEntity entity = null;
        while ( iterator.hasNext() ) {
            entity = iterator.next();
            key = new Key();
            key.setId( entity.getId() );
            key.setName( entity.getName() );
            set.add( key );
        }
        return set;
    }

    public static Map<Key, String> wrap( Map<KeyEntity, String> keys ) {
        if ( null == keys ) {
            return null;
        }
        Map<Key, String> map = new HashMap<>();
        KeyEntity entity = null;
        Key model = null;
        for ( Entry<KeyEntity, String> entry : keys.entrySet() ) {
            entity = entry.getKey();
            model = new Key();
            model.setId( entity.getId() );
            model.setName( entity.getName() );
            map.put( model, entry.getValue() );
        }
        return map;
    }
}
