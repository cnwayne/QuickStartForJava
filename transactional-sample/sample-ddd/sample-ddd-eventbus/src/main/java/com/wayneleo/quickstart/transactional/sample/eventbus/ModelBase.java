package com.wayneleo.quickstart.transactional.sample.eventbus;

import java.io.Serializable;
import javax.persistence.Column;

@SuppressWarnings( "serial" )
public class ModelBase implements Serializable {
    private String name;

    @Column( name = "name", length = 100 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
