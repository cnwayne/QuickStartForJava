package com.wayneleo.quickstart.framework.common.cache;

public class CacheContent {
    final String namespace;
    final String key;
    final Object value;

    public CacheContent( String namespace, String key, Object value ) {
        super();
        this.namespace = namespace;
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "CacheContent {\"namespace\":\"" +
                namespace +
                "\", \"key\":\"" +
                key +
                "\", \"value\":\"" +
                value +
                "\"}";
    }
}
