package com.wayneleo.quickstart.framework.base;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
@SuppressWarnings( "serial" )
public abstract class BaseEntity implements Serializable {
    private String id;

    @Id
    @GenericGenerator( name = "generator", strategy = "com.wayneleo.quickstart.framework.base.GeneriGenerator" )
    @GeneratedValue( generator = "generator" )
    @Column( name = "id", length = 32 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }
}
