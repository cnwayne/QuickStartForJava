package com.wayneleo.quickstart.sample.shiro;

import java.io.IOException;
import java.io.Serializable;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wayneleo.quickstart.framework.base.BaseCode;
import com.wayneleo.quickstart.framework.base.BaseException;

@Component
public class SessionDao4Oracle extends EnterpriseCacheSessionDAO {
    private static final Logger      logger = LoggerFactory.getLogger( SessionDao4Oracle.class );
    @Autowired
    private ObjectMapper             objectMapper;
    @Autowired
    private SessionRepository4Oracle repo;

    public SessionDao4Oracle() {
        setSessionIdGenerator( new JavaUuidSessionIdGenerator() );
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
                repo.save( new SessionEntity( session.getId(), objectMapper.writeValueAsString( session ), username ) );
            }
            catch ( JsonProcessingException e ) {
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
                repo.save( new SessionEntity( sessionId, objectMapper.writeValueAsString( session ), username ) );
            }
            catch ( JsonProcessingException e ) {
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
            return objectMapper.readValue( entity.getSession(), Session.class );
        }
        catch ( IOException e ) {
            logger.error( "反序列化session时发生异常, ID是[{}]", entity.getId(), e );
            throw new BaseException( BaseCode.FAILED, e );
        }
    }
}
