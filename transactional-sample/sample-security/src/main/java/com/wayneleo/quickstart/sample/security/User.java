package com.wayneleo.quickstart.sample.security;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity( name = "sample_security_user" )
@SuppressWarnings( "serial" )
public class User implements Serializable {
    private String     id;
    private String     name;
    private List<Role> roles;

    @Id
    @Column( name = "id", length = 32 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Column( name = "name", length = 20 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @ManyToMany( cascade = CascadeType.REFRESH, targetEntity = Role.class, mappedBy = "users", fetch = FetchType.LAZY )
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles( List<Role> roles ) {
        this.roles = roles;
    }
}
