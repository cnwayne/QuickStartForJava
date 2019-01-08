package com.wayneleo.quickstart.configcenter.api;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import com.wayneleo.quickstart.framework.base.BaseEntity;

@MappedSuperclass
@SuppressWarnings( { "rawtypes", "serial" } )
public class ProjectEntity<ENV extends EnvironmentEntity> extends BaseEntity {
    private String   name;
    private Set<ENV> environments;

    @Column( name = "name", length = 50 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @OneToMany( mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    public Set<ENV> getEnvironments() {
        return environments;
    }

    public void setEnvironments( Set<ENV> environments ) {
        this.environments = environments;
    }
}
