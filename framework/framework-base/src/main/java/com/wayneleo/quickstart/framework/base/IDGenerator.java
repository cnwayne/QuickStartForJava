package com.wayneleo.quickstart.framework.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class IDGenerator {
    public static final String gen() {
        return UUID.randomUUID().toString().replaceAll( "-", "" ).toUpperCase();
    }

    public static String gen( String source ) {
        StringBuffer buf = new StringBuffer( "" );
        try {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            md.update( source.getBytes() );
            byte b[] = md.digest();
            int i;
            for ( int offset = 0; offset < b.length; offset++ ) {
                i = b[offset];
                if ( i < 0 )
                    i += 256;
                if ( i < 16 )
                    buf.append( "0" );
                buf.append( Integer.toHexString( i ) );
            }
        }
        catch ( NoSuchAlgorithmException e ) {
            e.printStackTrace();
        }
        return buf.toString().toUpperCase();
    }
}
