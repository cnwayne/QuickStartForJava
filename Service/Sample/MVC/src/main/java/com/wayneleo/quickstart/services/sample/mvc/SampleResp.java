package com.wayneleo.quickstart.services.sample.mvc;

import java.util.Date;
import com.wayneleo.quickstart.framework.entity.BaseResponse;

public class SampleResp extends BaseResponse {
    private String id;
    private String name;
    private Date   datetime;

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
