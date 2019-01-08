package com.wayneleo.quickstart.configcenter.model;

import javax.persistence.Entity;
import com.wayneleo.quickstart.configcenter.api.ProjectEntity;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_project" )
@SuppressWarnings( { "serial", "rawtypes", "unchecked" } )
public class Project extends ProjectEntity<Environment> implements BaseModel<Project, ProjectEntity> {
    @Override
    public Project wrap( ProjectEntity project ) {
        if ( null == project ) {
            return null;
        }
        this.setId( project.getId() );
        this.setName( project.getName() );
        this.setEnvironments( Environment.wrap( project.getEnvironments() ) );
        return this;
    }
}
