package com.wayneleo.quickstart.configcenter.api;

import java.util.HashSet;
import java.util.Set;
import com.wayneleo.quickstart.framework.base.BaseEntity;

@SuppressWarnings( { "rawtypes", "serial" } )
public class ProjectDTO<MOD extends ModuleDTO> extends BaseEntity {
    protected String   name;
    protected Set<MOD> modules;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Set<MOD> getModules() {
        return null == modules ? modules = new HashSet<MOD>() : modules;
    }

    public void setModules( Set<MOD> modules ) {
        this.modules = modules;
    }
}
