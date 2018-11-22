package com.wayneleo.quickstart.sample.osgi.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

public class OSGiEngine {
    private static Framework framework;

    public static void stop() {
        try {
            framework.stop();
        }
        catch ( Exception e ) {
            throw new OSGiException( e );
        }
    }

    public static void start( Map<String, String> configuration ) {
        try {
            if ( null == framework ) {
                syncInit( configuration );
            }
        }
        catch ( Exception e ) {
            throw new OSGiException( e );
        }
    }

    private static synchronized void syncInit( Map<String, String> configuration ) throws Exception {
        if ( null != framework ) {
            return;
        }
        FrameworkFactory factory = getFrameworkFactory();
        framework = factory.newFramework( configuration );
        framework.init();
        framework.start();
    }

    private static FrameworkFactory getFrameworkFactory() throws Exception {
        String implName = "META-INF/services/org.osgi.framework.launch.FrameworkFactory";
        URL url = OSGiEngine.class.getClassLoader().getResource( implName );
        if ( null != url ) {
            BufferedReader br = new BufferedReader( new InputStreamReader( url.openStream() ) );
            try {
                for ( String line = br.readLine(); null != line; line = br.readLine() ) {
                    line = line.trim();
                    if ( line.length() > 0 ) {
                        return ( FrameworkFactory ) Class.forName( line ).newInstance();
                    }
                }
            }
            finally {
                if ( null != br ) {
                    br.close();
                }
            }
        }
        throw new OSGiException( "Could not find framework factory." );
    }
}
