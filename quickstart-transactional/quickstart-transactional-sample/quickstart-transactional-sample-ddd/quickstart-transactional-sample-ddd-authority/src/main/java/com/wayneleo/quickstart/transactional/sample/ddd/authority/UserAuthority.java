package com.wayneleo.quickstart.transactional.sample.ddd.authority;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "test_authority" )
@SuppressWarnings( "serial" )
public class UserAuthority implements Serializable {
    private String         id;    // 主键
    private String         name;  // 角色名称
    private String         desc;  // 角色描述
    private String         mark;  // 角色标识
    private List<UserRole> roles; // 拥有该权限的所有角色

    @Id
    @Column( name = "authority_id", nullable = false, length = 32 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Column( name = "authority_name", nullable = false, length = 100 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Column( name = "authority_desc", length = 500 )
    public String getDesc() {
        return desc;
    }

    public void setDesc( String desc ) {
        this.desc = desc;
    }

    @Column( name = "authority_mark", length = 200 )
    public String getMark() {
        return mark;
    }

    public void setMark( String mark ) {
        this.mark = mark;
    }

    @ManyToMany(
        cascade = CascadeType.REFRESH,
        targetEntity = UserRole.class,
        fetch = FetchType.LAZY,
        mappedBy = "authorities" )
    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles( List<UserRole> roles ) {
        this.roles = roles;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( ( null == obj ) || ( !( obj instanceof UserAuthority ) ) ) {
            return false;
        }
        return ( ( UserAuthority ) obj ).getId().equals( id );
    }
}
