package com.wayneleo.quickstart.transactional.sample.ddd.authority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.wayneleo.quickstart.transactional.sample.ddd.tag.Tag;

/**
 * 用户模型
 *
 * @author wayne
 */
@Entity
@Table( name = "test_user" )
@SuppressWarnings( "serial" )
public class User implements Serializable {
    private String          id;
    private String          name;
    private int             sex;
    private int             age;
    private List<UserGroup> groups;
    private List<UserRole>  roles;
    private List<Tag>       tags;

    @Id
    @Column( name = "user_id", nullable = false, length = 32 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Column( name = "user_name", nullable = false, length = 100 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Column( name = "user_sex" )
    public int getSex() {
        return sex;
    }

    public void setSex( int sex ) {
        this.sex = sex;
    }

    @Column( name = "user_age" )
    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    @ManyToMany(
        cascade = CascadeType.REFRESH,
        targetEntity = UserGroup.class,
        mappedBy = "users",
        fetch = FetchType.LAZY )
    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups( List<UserGroup> groups ) {
        this.groups = groups;
    }

    @ManyToMany(
        cascade = CascadeType.REFRESH,
        targetEntity = UserRole.class,
        mappedBy = "users",
        fetch = FetchType.LAZY )
    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles( List<UserRole> roles ) {
        this.roles = roles;
    }

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creater" )
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags( List<Tag> tags ) {
        this.tags = tags;
    }

    public User addGroup( UserGroup group ) {
        this.groups.add( group );
        return this;
    }

    public User addGroups( List<UserGroup> groups ) {
        this.groups.addAll( groups );
        return this;
    }

    public User addRole( UserRole role ) {
        this.roles.add( role );
        return this;
    }

    public User addRoles( List<UserRole> roles ) {
        this.roles.addAll( roles );
        return this;
    }

    public User removeGroup( String groupId ) {
        UserGroup removeGroup = null;
        for ( UserGroup group : groups ) {
            if ( groupId.equals( group.getId() ) ) {
                removeGroup = group;
                break;
            }
        }
        groups.remove( removeGroup );
        return this;
    }

    public User removeGroups( String[] groupIds ) {
        List<UserGroup> removeGroups = new ArrayList<>( groupIds.length + 2 );
        for ( UserGroup group : groups ) {
            for ( String groupId : groupIds ) {
                if ( groupId.equals( group.getId() ) ) {
                    removeGroups.add( group );
                    break;
                }
            }
        }
        for ( UserGroup removeGroup : removeGroups ) {
            groups.remove( removeGroup );
        }
        return this;
    }

    public User removeRole( String roleId ) {
        UserRole removeRole = null;
        for ( UserRole role : roles ) {
            if ( roleId.equals( role.getId() ) ) {
                removeRole = role;
                break;
            }
        }
        if ( null != removeRole ) {
            roles.remove( removeRole );
        }
        return this;
    }

    public User removeRoles( String[] roleIds ) {
        List<UserRole> removeRoles = new ArrayList<>( roleIds.length + 2 );
        for ( UserRole role : roles ) {
            for ( String roleId : roleIds ) {
                if ( roleId.equals( role.getId() ) ) {
                    removeRoles.add( role );
                    break;
                }
            }
        }
        for ( UserRole removeRole : removeRoles ) {
            roles.remove( removeRole );
        }
        return this;
    }

    public List<UserAuthority> allAuthorities() {
        Set<UserAuthority> authorities = new HashSet<>();
        for ( UserRole role : roles ) {
            authorities.addAll( role.getAuthorities() );
        }
        return new ArrayList<>( authorities );
    }

    public boolean hasAuthorities( String mark ) {
        List<UserAuthority> authorities = allAuthorities();
        for ( UserAuthority authority : authorities ) {
            if ( mark.equals( authority.getMark() ) ) {
                return true;
            }
        }
        return false;
    }
}
