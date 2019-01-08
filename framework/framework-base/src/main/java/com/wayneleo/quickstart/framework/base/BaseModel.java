package com.wayneleo.quickstart.framework.base;

@SuppressWarnings( "rawtypes" )
public interface BaseModel<V extends BaseModel, E extends BaseEntity> {
    public V wrap( E entity );
}
