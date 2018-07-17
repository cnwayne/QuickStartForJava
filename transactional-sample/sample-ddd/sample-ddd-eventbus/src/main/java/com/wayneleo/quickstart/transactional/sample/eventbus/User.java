package com.wayneleo.quickstart.transactional.sample.eventbus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "test_user" )
@SuppressWarnings( "serial" )
public class User extends ModelBase {
    private String id;

    @Id
    @Column( name = "id", length = 32 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }
}
