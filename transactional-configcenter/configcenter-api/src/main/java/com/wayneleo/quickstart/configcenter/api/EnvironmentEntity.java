package com.wayneleo.quickstart.configcenter.api;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import com.wayneleo.quickstart.framework.base.BaseEntity;

@MappedSuperclass
@SuppressWarnings( { "rawtypes", "serial" } )
public class EnvironmentEntity<MOD extends ModuleEntity, PRO extends ProjectEntity> extends BaseEntity {
    private String   name;
    private Set<MOD> modules;
    private PRO      project;

    @Column( name = "name", length = 50 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @OneToMany( mappedBy = "environment", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    public Set<MOD> getModules() {
        return modules;
    }

    public void setModules( Set<MOD> modules ) {
        this.modules = modules;
    }

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false )
    @JoinColumn( name = "project" )
    public PRO getProject() {
        return project;
    }

    public void setProject( PRO project ) {
        this.project = project;
    }
}
