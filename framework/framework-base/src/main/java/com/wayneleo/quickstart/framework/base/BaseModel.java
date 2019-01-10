package com.wayneleo.quickstart.framework.base;

@SuppressWarnings( "rawtypes" )
public interface BaseModel<MODEL extends BaseModel, DTO extends BaseEntity> {
    public MODEL wrap( DTO dto );
}
