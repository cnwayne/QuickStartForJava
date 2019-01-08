package com.wayneleo.quickstart.framework.extensions.configuration;

public class Configuration {
    public static final String CENTER_ADDRESS = "center.address";
    public static final String CENTER_SECRET  = "center.secret";
    public static final String APP_NAME       = "app.name";
    private String             address;
    private String             secret;
    private String[]           name;

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

    public String[] getName() {
        return name;
    }

    public void setName( String[] name ) {
        this.name = name;
    }
}
