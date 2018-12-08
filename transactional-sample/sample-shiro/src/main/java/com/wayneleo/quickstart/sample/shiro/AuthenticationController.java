package com.wayneleo.quickstart.sample.shiro;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wayneleo.quickstart.framework.base.BaseResponse;
import com.wayneleo.quickstart.sample.shiro.AuthenticationController.Response.EndType;

@RestController
@RequestMapping( "/sample/shiro" )
public class AuthenticationController {
    @SuppressWarnings( "serial" )
    public static class Response extends BaseResponse {
        public static enum EndType {
            FRONTEND, BACKEND;
        }

        private String  username;
        private EndType type;

        public String getUsername() {
            return username;
        }

        public void setUsername( String username ) {
            this.username = username;
        }

        public EndType getType() {
            return type;
        }

        public void setType( EndType type ) {
            this.type = type;
        }
    }

    @GetMapping( "/frontend/login" )
    public Response frontendLogin( String username ) {
        if ( StringUtils.isEmpty( username ) ) {
            return buildResponse( HttpServletResponse.SC_BAD_REQUEST );
        }
        try {
            AuthenticationToken token = new Realm4Frontend.Token( username );
            SecurityUtils.getSubject().login( token );
            return buildResponse( HttpServletResponse.SC_OK, EndType.FRONTEND, username );
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return buildResponse( HttpServletResponse.SC_FORBIDDEN );
        }
    }

    @GetMapping( "/backend/login" )
    public Response backendLogin( String username ) {
        if ( StringUtils.isEmpty( username ) ) {
            return buildResponse( HttpServletResponse.SC_BAD_REQUEST );
        }
        try {
            AuthenticationToken token = new Realm4Backend.Token( username );
            SecurityUtils.getSubject().login( token );
            return buildResponse( HttpServletResponse.SC_OK, EndType.BACKEND, username );
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return buildResponse( HttpServletResponse.SC_FORBIDDEN );
        }
    }

    @GetMapping( "/frontend" )
    public Response frontendAccess() {
        return buildResponse( HttpServletResponse.SC_OK, EndType.FRONTEND, SecurityUtils.getSubject().getPrincipal().toString() );
    }

    @GetMapping( "/backend" )
    public Response backendAccess() {
        return buildResponse( HttpServletResponse.SC_OK, EndType.BACKEND, SecurityUtils.getSubject().getPrincipal().toString() );
    }

    private Response buildResponse( int code, EndType type, String username ) {
        Response resp = new Response();
        resp.setCode( code );
        resp.setType( type );
        resp.setUsername( username );
        return resp;
    }

    private Response buildResponse( int code ) {
        Response resp = new Response();
        resp.setCode( code );
        return resp;
    }
}
