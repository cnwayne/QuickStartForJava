package com.wayneleo.quickstart.framework.extensions.configuration;

public class Configuration {
    public static final String CENTER_ADDRESS = "center.address";
    public static final String CENTER_SECRET  = "center.secret";
    public static final String APP_NAMESPACE  = "app.namespace";
    private String             address;
    private String             secret;
    private String[]           namespace;

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret( String secret ) {
        this.secret = secret;
    }

    public String[] getNamespace() {
        return namespace;
    }

    public void setNamespace( String[] namespace ) {
        this.namespace = namespace;
    }
}
