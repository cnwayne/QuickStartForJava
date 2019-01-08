package com.wayneleo.quickstart.configcenter.api;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.wayneleo.quickstart.framework.base.BaseEntity;

@MappedSuperclass
@SuppressWarnings( { "rawtypes", "serial" } )
public class KeyEntity<MOD extends ModuleEntity> extends BaseEntity {
    private String name;
    private MOD    module;

    @Column( name = "name", length = 32 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false )
    @JoinColumn( name = "module" )
    public MOD getModule() {
        return module;
    }

    public void setModule( MOD module ) {
        this.module = module;
    }
}
