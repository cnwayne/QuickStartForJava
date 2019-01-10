package com.wayneleo.quickstart.configcenter.api;

import java.util.Properties;
import java.util.Set;
import com.wayneleo.quickstart.framework.base.BaseInterface;

/**
 * 注意 : 所有请求用的DTO都必须有ID!
 * 
 * @author wayne
 */
@SuppressWarnings( "rawtypes" )
public interface ConfigCenterService extends BaseInterface {
    public ProjectDTO save( ProjectDTO dto );

    public ModuleDTO save( ModuleDTO dto );

    public KeyDTO save( KeyDTO dto );

    public EnvironmentDTO save( EnvironmentDTO dto );

    public void save( Set<PropertyDTO> dtos );

    public void delete( ProjectDTO dto );

    public void delete( ModuleDTO dto );

    public void delete( KeyDTO dto );

    public void delete( EnvironmentDTO dto );

    public void delete( PropertyDTO dto );

    public void delete( Set<PropertyDTO> dtos );

    public Set<? extends ProjectDTO> fetchProjects();

    public Set<? extends ModuleDTO> fetchModules( ProjectDTO dto );

    public Set<? extends EnvironmentDTO> fetchEnvironments( ModuleDTO dto );

    public Set<? extends KeyDTO> fetchKeys( ModuleDTO dto );

    public Properties fetchProperties( ProjectDTO dto );
}
