package com.wayneleo.quickstart.configcenter.api;

import com.wayneleo.quickstart.framework.base.BaseEntity;

@SuppressWarnings( { "rawtypes", "serial" } )
public class KeyDTO<MOD extends ModuleDTO> extends BaseEntity {
    protected String name;
    protected MOD    module;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public MOD getModule() {
        return module;
    }

    public void setModule( MOD module ) {
        this.module = module;
    }
}
