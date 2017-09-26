package com.wayneleo.quickstart.services.sample.mvc;

import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wayneleo.quickstart.framework.base.exception.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api( tags = "样例接口" )
@RestController( "W_SampleController" )
@RequestMapping( value = "/api/v1/sample" )
public class SampleController extends BaseController {

    @ApiOperation( value = "接口01", notes = "返回正常结果" )
    @ApiImplicitParams( {
            @ApiImplicitParam( paramType = "query", name = "name", value = "用户名", required = true ),
    } )
    @RequestMapping( method = RequestMethod.POST )
    public SampleResp post( @RequestParam( name = "name" ) String name ) {
        SampleResp resp = new SampleResp();
        resp.setName( name );
        resp.setDatetime( new Date() );
        return resp;
    }

    @ApiOperation( value = "接口02", notes = "返回正常结果" )
    @ApiImplicitParams( {
            @ApiImplicitParam( paramType = "query", name = "name", value = "用户名", required = true ),
    } )
    @RequestMapping( method = RequestMethod.GET )
    public SampleResp get( @RequestParam( name = "name" ) String name ) {
        SampleResp resp = new SampleResp();
        resp.setName( name );
        resp.setDatetime( new Date() );
        return resp;
    }

}
