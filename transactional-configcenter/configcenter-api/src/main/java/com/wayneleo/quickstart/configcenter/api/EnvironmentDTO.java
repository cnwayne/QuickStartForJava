package com.wayneleo.quickstart.configcenter.api;

import java.util.HashSet;
import java.util.Set;
import com.wayneleo.quickstart.framework.base.BaseEntity;

@SuppressWarnings( { "rawtypes", "serial" } )
public class EnvironmentDTO<PROP extends PropertyDTO, MOD extends ModuleDTO> extends BaseEntity {
    protected String    name;
    protected Set<PROP> props;
    protected MOD       module;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Set<PROP> getProps() {
        return null == props ? props = new HashSet<PROP>() : props;
    }

    public void setProps( Set<PROP> props ) {
        this.props = props;
    }

    public MOD getModule() {
        return module;
    }

    public void setModule( MOD module ) {
        this.module = module;
    }
}
