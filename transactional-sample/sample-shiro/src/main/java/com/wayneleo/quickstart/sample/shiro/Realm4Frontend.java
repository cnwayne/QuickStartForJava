package com.wayneleo.quickstart.sample.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
public class Realm4Frontend extends AuthorizingRealm {
    @SuppressWarnings( "serial" )
    public static class Token extends UsernamePasswordToken {
        public Token( String username ) {
            setUsername( username );
        }
    }

    public Realm4Frontend( CredentialsMatch credentialsMatch ) {
        setCredentialsMatcher( credentialsMatch );
    }

    @Override
    public boolean supports( AuthenticationToken token ) {
        if ( token instanceof Token ) {
            return true;
        }
        return false;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals ) {
        String username = String.valueOf( principals.getPrimaryPrincipal() );
        if ( !"waynefrontend".equals( username ) ) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole( "role-frontend-A" );
        authorizationInfo.addRole( "role-frontend-B" );
        authorizationInfo.addStringPermission( "permission-frontend-A" );
        authorizationInfo.addStringPermission( "permission-frontend-B" );
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken token ) throws AuthenticationException {
        String username = String.valueOf( token.getPrincipal() );
        if ( !"waynefrontend".equals( username ) ) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo( username, "123456", getName() );
        return authenticationInfo;
    }
}
