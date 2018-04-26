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
import com.wayneleo.quickstart.transactional.sample.ddd.tag.Tag;

/**
 * 用户组模型
 * 
 * @author wayne
 */
@Entity
@Table( name = "test_group" )
@SuppressWarnings( "serial" )
public class UserGroup implements Serializable {
    private String     id;    // 主键
    private String     name;  // 用户组名称
    private List<User> users; // 用户组包含的所有用户
    private List<Tag>  tags;  // 用户组所属的标签

    @Id
    @Column( name = "group_id", nullable = false, length = 32 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Column( name = "group_name", nullable = false, length = 100 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @ManyToMany( cascade = CascadeType.REFRESH, targetEntity = User.class, fetch = FetchType.LAZY )
    @JoinTable(
        name = "test_group_and_user",
        joinColumns = @JoinColumn( name = "group_id" ),
        inverseJoinColumns = @JoinColumn( name = "user_id" ) )
    public List<User> getUsers() {
        return users;
    }

    public void setUsers( List<User> users ) {
        this.users = users;
    }

    @ManyToMany( cascade = CascadeType.REFRESH, targetEntity = Tag.class, fetch = FetchType.LAZY )
    @JoinTable(
        name = "test_group_and_tag",
        joinColumns = @JoinColumn( name = "group_id" ),
        inverseJoinColumns = @JoinColumn( name = "tag_id" ) )
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags( List<Tag> tags ) {
        this.tags = tags;
    }

    public UserGroup addUser( User user ) {
        users.add( user );
        return this;
    }

    public UserGroup addUsers( List<User> users ) {
        users.addAll( users );
        return this;
    }

    public UserGroup removeUser( String userId ) {
        User removeUser = null;
        for ( User user : users ) {
            if ( userId.equals( user.getId() ) ) {
                removeUser = user;
                break;
            }
        }
        users.remove( removeUser );
        return this;
    }

    public UserGroup removeUsers( String[] userIds ) {
        if ( null == userIds || userIds.length == 0 ) {
            return this;
        }
        List<User> removeUsers = new ArrayList<>( userIds.length );
        for ( User user : users ) {
            for ( String userId : userIds ) {
                if ( userId.equals( user.getId() ) ) {
                    removeUsers.add( user );
                    break;
                }
            }
        }
        for ( User removeUser : removeUsers ) {
            users.remove( removeUser );
        }
        return this;
    }
}
