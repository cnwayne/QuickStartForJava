package com.wayneleo.quickstart.configcenter.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.wayneleo.quickstart.configcenter.api.ProjectDTO;
import com.wayneleo.quickstart.framework.base.BaseModel;

@Entity( name = "configcenter_project" )
@SuppressWarnings( { "serial", "rawtypes", "unchecked" } )
public class Project extends ProjectDTO<Module> implements BaseModel<Project, ProjectDTO> {
    @Column( name = "name", length = 50, unique = true )
    @Override
    public String getName() {
        return super.getName();
    }

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinColumn( name = "project" )
    @Override
    public Set<Module> getModules() {
        return super.getModules();
    }

    @Override
    public Project wrap( ProjectDTO dto ) {
        if ( null == dto ) {
            return null;
        }
        this.setId( dto.getId() );
        this.setName( dto.getName() );
        this.setModules( Module.wrap( dto.getModules() ) );
        return this;
    }
}
