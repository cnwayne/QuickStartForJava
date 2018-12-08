package com.wayneleo.quickstart.sample.shiro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wayneleo.quickstart.framework.base.BaseCode;
import com.wayneleo.quickstart.framework.base.BaseException;
import com.wayneleo.quickstart.framework.base.IDGenerator;

@Component
public class SessionDao4Oracle extends EnterpriseCacheSessionDAO {
    private static final Logger      logger = LoggerFactory.getLogger( SessionDao4Oracle.class );
    @Autowired
    private SessionRepository4Oracle repo;

    public SessionDao4Oracle() {
        setSessionIdGenerator( new SessionIdGenerator() {
            @Override
            public Serializable generateId( Session session ) {
                return IDGenerator.gen();
            }
        } );
    }

    @Override
    protected void doDelete( Session session ) {
        repo.delete( String.valueOf( session.getId() ) );
    }

    @Override
    protected void doUpdate( Session session ) {
        if ( session instanceof ValidatingSession && !( ( ValidatingSession ) session ).isValid() ) {
            return;
        }
        String username = String.valueOf( session.getAttribute( DefaultSubjectContext.PRINCIPALS_SESSION_KEY ) );
        if ( !"null".equalsIgnoreCase( username ) ) {
            try {
                repo.save( new SessionEntity( session.getId(), username, serializ( session ) ) );
            }
            catch ( Exception e ) {
                logger.error( "序列化session时发生异常, ID是[{}]", session.getId(), e );
                throw new BaseException( BaseCode.FAILED, e );
            }
        }
    }

    @Override
    protected Serializable doCreate( Session session ) {
        Serializable sessionId = generateSessionId( session );
        assignSessionId( session, sessionId );
        String username = String.valueOf( session.getAttribute( DefaultSubjectContext.PRINCIPALS_SESSION_KEY ) );
        if ( !"null".equalsIgnoreCase( username ) ) {
            try {
                repo.save( new SessionEntity( sessionId, username, serializ( session ) ) );
            }
            catch ( Exception e ) {
                logger.error( "序列化session时发生异常, ID是[{}]", session.getId(), e );
                throw new BaseException( BaseCode.FAILED, e );
            }
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession( Serializable sessionId ) {
        SessionEntity entity = repo.findOne( String.valueOf( sessionId ) );
        if ( null == entity ) {
            return null;
        }
        try {
            return deserializ( entity.getSession() );
        }
        catch ( Exception e ) {
            logger.error( "反序列化session时发生异常, ID是[{}]", entity.getId(), e );
            throw new BaseException( BaseCode.FAILED, e );
        }
    }

    private byte[] serializ( Session session ) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream( bos );
        out.writeObject( session );
        return bos.toByteArray();
    }

    private Session deserializ( byte[] bytes ) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream( bytes );
        ObjectInputStream in = new ObjectInputStream( bis );
        return ( Session ) in.readObject();
    }
}
