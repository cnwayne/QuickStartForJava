package com.wayneleo.quickstart.sample.osgi.demo;

public class OSGiException extends RuntimeException {
    private static final long serialVersionUID = -5113690962529441438L;

    public OSGiException( Exception e ) {
        super( e );
    }

    public OSGiException( String msg ) {
        super( msg );
    }
}
