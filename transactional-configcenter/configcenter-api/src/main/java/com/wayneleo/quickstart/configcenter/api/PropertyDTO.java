package com.wayneleo.quickstart.configcenter.api;

import com.wayneleo.quickstart.framework.base.BaseEntity;

@SuppressWarnings( { "rawtypes", "serial" } )
public class PropertyDTO<KEY extends KeyDTO, ENV extends EnvironmentDTO> extends BaseEntity {
    protected String value;
    protected KEY    key;
    protected ENV    environment;

    public String getValue() {
        return value;
    }

    public void setValue( String value ) {
        this.value = value;
    }

    public KEY getKey() {
        return key;
    }

    public void setKey( KEY key ) {
        this.key = key;
    }

    public ENV getEnvironment() {
        return environment;
    }

    public void setEnvironment( ENV environment ) {
        this.environment = environment;
    }
}
