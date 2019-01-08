package com.wayneleo.quickstart.configcenter.api;

import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import com.wayneleo.quickstart.framework.base.BaseEntity;

@MappedSuperclass
@SuppressWarnings( { "rawtypes", "serial" } )
public class ModuleEntity<KEY extends KeyEntity, ENV extends EnvironmentEntity> extends BaseEntity {
    private String           name;
    private Set<KEY>         keys;
    private Map<KEY, String> mapping;
    private ENV              environment;

    @Column( name = "name", length = 50 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @OneToMany( mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    public Set<KEY> getKeys() {
        return keys;
    }

    public void setKeys( Set<KEY> keys ) {
        this.keys = keys;
    }

    @ElementCollection
    @CollectionTable( name = "configcenter_properties" )
    @MapKeyJoinColumn( name = "col_key" )
    @Column( name = "value" )
    public Map<KEY, String> getMapping() {
        return mapping;
    }

    public void setMapping( Map<KEY, String> mapping ) {
        this.mapping = mapping;
    }

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false )
    @JoinColumn( name = "environment" )
    public ENV getEnvironment() {
        return environment;
    }

    public void setEnvironment( ENV environment ) {
        this.environment = environment;
    }
}
