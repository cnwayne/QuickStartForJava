package com.wayneleo.quickstart.configcenter.api;

import java.util.HashSet;
import java.util.Set;
import com.wayneleo.quickstart.framework.base.BaseEntity;

@SuppressWarnings( { "rawtypes", "serial" } )
public class ModuleDTO<KEY extends KeyDTO, ENV extends EnvironmentDTO, PRO extends ProjectDTO> extends BaseEntity {
    protected String   name;
    protected Set<KEY> keys;
    protected Set<ENV> environments;
    protected PRO      project;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Set<KEY> getKeys() {
        return null == keys ? keys = new HashSet<KEY>() : keys;
    }

    public void setKeys( Set<KEY> keys ) {
        this.keys = keys;
    }

    public Set<ENV> getEnvironments() {
        return null == environments ? environments = new HashSet<ENV>() : environments;
    }

    public void setEnvironments( Set<ENV> environments ) {
        this.environments = environments;
    }

    public PRO getProject() {
        return project;
    }

    public void setProject( PRO project ) {
        this.project = project;
    }
}
