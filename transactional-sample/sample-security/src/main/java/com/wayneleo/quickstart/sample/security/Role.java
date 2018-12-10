package com.wayneleo.quickstart.sample.security;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity( name = "sample_security_role" )
@SuppressWarnings( "serial" )
public class Role implements Serializable {
    private String           id;
    private String           name;
    private List<User>       users;
    private List<Permission> permissions;

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

    @ManyToMany( cascade = CascadeType.REFRESH, targetEntity = User.class, fetch = FetchType.LAZY )
    @JoinTable( name = "sample_security_role_user", joinColumns = @JoinColumn( name = "role_id" ), inverseJoinColumns = @JoinColumn( name = "user_id" ) )
    public List<User> getUsers() {
        return users;
    }

    public void setUsers( List<User> users ) {
        this.users = users;
    }

    @ManyToMany( cascade = CascadeType.REFRESH, targetEntity = Permission.class, fetch = FetchType.LAZY )
    @JoinTable( name = "sample_security_role_permission", joinColumns = @JoinColumn( name = "role_id" ), inverseJoinColumns = @JoinColumn( name = "permission_id" ) )
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions( List<Permission> permissions ) {
        this.permissions = permissions;
    }
}
