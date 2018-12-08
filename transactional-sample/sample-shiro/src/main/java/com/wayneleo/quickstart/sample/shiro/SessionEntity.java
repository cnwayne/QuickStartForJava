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
    private String session;
    private String username;

    public SessionEntity() {}

    public SessionEntity( String id, String session, String username ) {
        super();
        this.id = id;
        this.session = session;
        this.username = username;
    }

    public SessionEntity( Serializable id, String session, String username ) {
        super();
        this.id = String.valueOf( id );
        this.session = session;
        this.username = username;
    }

    @Id
    @Column( name = "id", length = 36 )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Lob
    @Basic( fetch = FetchType.LAZY )
    @Column( name = "session", length = 5000 )
    public String getSession() {
        return session;
    }

    public void setSession( String session ) {
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
