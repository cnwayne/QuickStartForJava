package com.wayneleo.quickstart.transactional.sample.ddd.authority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 用户角色模型
 * 
 * @author wayne
 */
@Entity
@Table( name = "test_role" )
@SuppressWarnings( "serial" )
public class UserRole implements Serializable {
    private String              id;          // 主键
    private String              name;        // 角色名称
    private List<User>          users;       // 拥有该角色的所有用户
    private List<UserAuthority> authorities; // 角色所拥有的权限

    @Id
    @Column( name = "role_id", nullable = false, length = 32 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Column( name = "role_name", nullable = false, length = 100 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @ManyToMany( cascade = CascadeType.REFRESH, targetEntity = User.class, fetch = FetchType.LAZY )
    @JoinTable(
        name = "test_role_and_user",
        joinColumns = @JoinColumn( name = "role_id" ),
        inverseJoinColumns = @JoinColumn( name = "user_id" ) )
    public List<User> getUsers() {
        return users;
    }

    public void setUsers( List<User> users ) {
        this.users = users;
    }

    @ManyToMany( cascade = CascadeType.REFRESH, targetEntity = UserAuthority.class, fetch = FetchType.LAZY )
    @JoinTable(
        name = "test_role_and_authority",
        joinColumns = @JoinColumn( name = "role_id" ),
        inverseJoinColumns = @JoinColumn( name = "authority_id" ) )
    public List<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities( List<UserAuthority> authorities ) {
        this.authorities = authorities;
    }

    public UserRole addAuthority( UserAuthority authority ) {
        authorities.add( authority );
        return this;
    }

    public UserRole addAuthorities( List<UserAuthority> authorities ) {
        this.authorities.addAll( authorities );
        return this;
    }

    public UserRole removeAuthority( String authorityId ) {
        UserAuthority removeAuthority = null;
        for ( UserAuthority authority : authorities ) {
            if ( authorityId.equals( authority.getId() ) ) {
                removeAuthority = authority;
            }
        }
        authorities.remove( removeAuthority );
        return this;
    }

    public UserRole removeAuthorities( String[] authorityIds ) {
        List<UserAuthority> removeAuthorities = new ArrayList<>( authorityIds.length );
        for ( UserAuthority authority : authorities ) {
            for ( String authorityId : authorityIds ) {
                if ( authorityId.equals( authority.getId() ) ) {
                    removeAuthorities.add( authority );
                }
            }
        }
        for ( UserAuthority removeAuthority : removeAuthorities ) {
            authorities.remove( removeAuthority );
        }
        return this;
    }
}
