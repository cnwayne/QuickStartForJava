package com.wayneleo.quickstart.transactional.sample.mvc;

import com.wayneleo.quickstart.framework.base.BaseResponse;

import java.util.Date;

public class SampleResp extends BaseResponse {
    private static final long serialVersionUID = 2358402166414827347L;
    private String id;
    private String name;
    private Date datetime;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime( Date datetime ) {
        this.datetime = datetime;
    }
}
