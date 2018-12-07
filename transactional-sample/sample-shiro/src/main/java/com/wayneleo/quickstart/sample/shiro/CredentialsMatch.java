package com.wayneleo.quickstart.sample.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

@Component
public class CredentialsMatch extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch( AuthenticationToken token, AuthenticationInfo info ) {
        return true;
    }
}
