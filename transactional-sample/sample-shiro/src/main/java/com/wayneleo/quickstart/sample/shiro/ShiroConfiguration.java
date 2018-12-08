package com.wayneleo.quickstart.sample.shiro;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {
    @Bean
    public ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        sessionValidationScheduler.setInterval( 30 * 60 * 1000 );
        return sessionValidationScheduler;
    }

    @Bean
    public DefaultWebSessionManager sessionManager( SessionDAO sessionDAO, ExecutorServiceSessionValidationScheduler sessionValidationScheduler ) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout( 30 * 60 * 1000 );
        sessionManager.setDeleteInvalidSessions( true );
        sessionManager.setSessionValidationScheduler( sessionValidationScheduler );
        sessionManager.setSessionValidationSchedulerEnabled( true );
        sessionManager.setSessionDAO( sessionDAO );
        SimpleCookie sessionIdCookie = new SimpleCookie( ShiroHttpSession.DEFAULT_SESSION_ID_NAME );
        sessionIdCookie.setHttpOnly( true );
        sessionIdCookie.setMaxAge( -1 );
        sessionManager.setSessionIdCookie( sessionIdCookie );
        sessionManager.setSessionIdCookieEnabled( true );
        sessionManager.setSessionIdUrlRewritingEnabled( false );
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager( Collection<Realm> realms, DefaultWebSessionManager sessionManager ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms( realms );
        securityManager.setSessionManager( sessionManager );
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean( SecurityManager securityManager, PermissionDefinition permissionDefinition ) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager( securityManager );
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put( "/logout", "logout" );
        map.putAll( permissionDefinition );
        shiroFilterFactoryBean.setLoginUrl( "/" );
        shiroFilterFactoryBean.setFilterChainDefinitionMap( map );
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor( SecurityManager securityManager ) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager( securityManager );
        return authorizationAttributeSourceAdvisor;
    }
}
