package com.wayneleo.quickstart.configcenter.api;

import java.util.List;
import java.util.Map;
import com.wayneleo.quickstart.framework.base.BaseInterface;

@SuppressWarnings( "rawtypes" )
public interface ConfigCenterService extends BaseInterface {
    public void save( ProjectEntity project );

    public void delete( ProjectEntity project );

    public List<ProjectEntity> fetch();

    public List<EnvironmentEntity> fetch( ProjectEntity project );

    public List<ModuleEntity> fetch( EnvironmentEntity environment );

    public Map<KeyEntity, String> fetch( ModuleEntity module );

    public List<ProjectEntity> searchProject( String name );

    public List<EnvironmentEntity> searchEnvironment( String name );

    public List<ModuleEntity> searchModule( String name );

    public Map<KeyEntity, String> searchKey( String name );
}
