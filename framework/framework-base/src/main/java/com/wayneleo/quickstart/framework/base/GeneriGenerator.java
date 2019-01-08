package com.wayneleo.quickstart.framework.base;

import java.io.Serializable;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class GeneriGenerator implements Configurable, IdentifierGenerator {
    @Override
    public void configure( Type type, Properties params, ServiceRegistry serviceRegistry ) throws MappingException {}

    @Override
    public Serializable generate( SessionImplementor session, Object object ) throws HibernateException {
        return com.wayneleo.quickstart.framework.base.IDGenerator.gen();
    }
}
