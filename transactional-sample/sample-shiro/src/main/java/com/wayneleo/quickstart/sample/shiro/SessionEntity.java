package com.wayneleo.quickstart.sample.shiro;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import com.wayneleo.quickstart.framework.base.BaseEntity;

@SuppressWarnings( "serial" )
@Entity( name = "sample_shiro_session" )
public class SessionEntity extends BaseEntity {
    private String id;
    private String username;
    private byte[] session;

    public SessionEntity() {}

    public SessionEntity( String id, String username, byte[] session ) {
        super();
        this.id = id;
        this.username = username;
        this.session = session;
    }

    public SessionEntity( Serializable id, String username, byte[] session ) {
        super();
        this.id = String.valueOf( id );
        this.username = username;
        this.session = session;
    }

    @Id
    @Column( name = "id", length = 32 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Lob
    @Basic( fetch = FetchType.LAZY )
    public byte[] getSession() {
        return session;
    }

    public void setSession( byte[] session ) {
        this.session = session;
    }

    @Column( name = "username", length = 200 )
    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }
}
