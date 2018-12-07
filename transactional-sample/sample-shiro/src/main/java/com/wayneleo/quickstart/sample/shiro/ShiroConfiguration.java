package com.wayneleo.quickstart.sample.shiro;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {
    @Bean
    public SecurityManager securityManager( Collection<Realm> realms ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms( realms );
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean( SecurityManager securityManager, PermissionDefinition permissionDefinition ) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager( securityManager );
        Map<String, String> map = new HashMap<String, String>();
        map.put( "/sample/shiro/frontend/logout", "logout" );
        //map.put( "/**", "authc" );
        map.put( "/sample/shiro/backend/login", "anon" );
        map.putAll( permissionDefinition );
        shiroFilterFactoryBean.setLoginUrl( "/sample/shiro/frontend/login" );
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
