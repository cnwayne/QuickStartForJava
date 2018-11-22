package com.wayneleo.quickstart.sample.osgi.test;

import com.wayneleo.quickstart.framework.base.BaseResponse;

@SuppressWarnings( "serial" )
public class SimpleResp extends BaseResponse {
    private String datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime( String datetime ) {
        this.datetime = datetime;
    }
}
