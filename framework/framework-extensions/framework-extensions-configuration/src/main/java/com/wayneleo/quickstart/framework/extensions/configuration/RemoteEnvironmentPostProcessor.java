package com.wayneleo.quickstart.framework.extensions.configuration;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class RemoteEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final String SPRING_PRIMARY_CONF_NAME = ConfigFileApplicationListener.APPLICATION_CONFIGURATION_PROPERTY_SOURCE_NAME;

    @Override
    public void postProcessEnvironment( ConfigurableEnvironment environment, SpringApplication application ) {
        productional( environment );
    }

    private void productional( ConfigurableEnvironment environment ) {
        try {
            MutablePropertySources propertySources = environment.getPropertySources();
            PropertySource<?> propertySource = propertySources.get( SPRING_PRIMARY_CONF_NAME );
            Configuration configuration = readConfiguration( propertySource );
            InputStream in = null;
            Properties properties = null;
            for ( int i = 0; i < configuration.getNamespace().length; i++ ) {
                in = readRemote( configuration );
                properties = new Properties();
                properties.load( in );
                propertySource = new PropertiesPropertySource( "remote-" + ( i + 1 ), properties );
                propertySources.addLast( propertySource );
            }
        }
        catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    private Configuration readConfiguration( PropertySource<?> propertySource ) {
        Object tmp = propertySource.getProperty( Configuration.CENTRE_ADDRESS );
        String centreAddress = ( null == tmp ) ? "" : String.valueOf( tmp );
        tmp = propertySource.getProperty( Configuration.CENTER_SECRET );
        String secret = ( null == tmp ) ? "" : String.valueOf( tmp );
        tmp = propertySource.getProperty( Configuration.APP_NAMESPACE );
        String[] namespaces = ( null == tmp ) ? new String[] {} : String.valueOf( tmp ).split( ";" );
        Configuration configuration = new Configuration();
        configuration.setAddress( centreAddress );
        configuration.setSecret( secret );
        configuration.setNamespace( namespaces );
        return configuration;
    }

    private InputStream readRemote( Configuration configuration ) throws Exception {
        String params = "secret=" + configuration.getSecret() + "&namespace=" + configuration.getNamespace();
        HttpURLConnection connection = null;
        OutputStream os = null;
        try {
            URL url = new URL( configuration.getAddress() );
            connection = ( HttpURLConnection ) url.openConnection();
            connection.setRequestMethod( "POST" );
            connection.setConnectTimeout( 15000 );
            connection.setReadTimeout( 60000 );
            connection.setDoOutput( true );
            connection.setDoInput( true );
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
            os = connection.getOutputStream();
            os.write( params.getBytes() );
            if ( 200 == connection.getResponseCode() ) {
                return connection.getInputStream();
            }
            else {
                throw new Exception();
            }
        }
        finally {
            try {
                os.close();
            }
            catch ( Exception e ) {}
        }
    }
}
