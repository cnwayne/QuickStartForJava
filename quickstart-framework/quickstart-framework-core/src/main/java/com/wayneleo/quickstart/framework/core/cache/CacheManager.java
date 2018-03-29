package com.wayneleo.quickstart.framework.core.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManager {
    private static final Map<String, Map<String, Object>> REPOSITORIES_0          = new HashMap<>();
    private static final Map<String, Map<String, Object>> REPOSITORIES_1          = new HashMap<>();
    private static int                                    currentRepositoriesFlag = 0;
    private static ThreadLocal<Integer>                   localRepositoriesFlag   = new ThreadLocal<>();

    public static void refresh( List<CacheInitializer> initializers ) {
        List<CacheContent> cacheContents = null;
        Map<String, Map<String, Object>> repositories = getRepositores( noCurrentFlag() );
        Map<String, Object> repository = null;
        for ( CacheInitializer initializer : initializers ) {
            cacheContents = initializer.doInit();
            if ( ( null == cacheContents ) || ( cacheContents.size() < 1 ) ) {
                continue;
            }
            for ( CacheContent cacheContent : cacheContents ) {
                repository = repositories.get( cacheContent.namespace );
                if ( null == repository ) {
                    repository = new HashMap<>();
                    repositories.put( cacheContent.namespace, repository );
                }
                repository.put( cacheContent.key, cacheContent.value );
            }
        }
        currentRepositoriesFlag = noCurrentFlag();
    }

    public static boolean contains( String namespace, String key ) {
        Map<String, Object> repository = getRepositores( currentFlag() ).get( namespace );
        if ( null == repository ) {
            return false;
        }
        return repository.containsKey( key );
    }

    @SuppressWarnings( "unchecked" )
    public static <T> T get( String namespace, String key ) {
        Map<String, Object> repository = getRepositores( currentFlag() ).get( namespace );
        if ( null != repository ) {
            return ( T ) repository.get( key );
        }
        return null;
    }

    private static Map<String, Map<String, Object>> getRepositores( int flag ) {
        if ( 0 == flag ) {
            return REPOSITORIES_0;
        }
        else {
            return REPOSITORIES_1;
        }
    }

    private static int currentFlag() {
        if ( null == localRepositoriesFlag.get() ) {
            localRepositoriesFlag.set( currentRepositoriesFlag );
        }
        return localRepositoriesFlag.get();
    }

    private static int noCurrentFlag() {
        if ( 0 == currentFlag() ) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
