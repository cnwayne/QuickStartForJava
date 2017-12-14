package com.wayneleo.quickstart.services.sample.mvc;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 这是个实体类，应该在Framework-Entity工程中创建相应功能模块的包存放这个类。
 */
@Entity
@Table( name = "sample" )
public class SampleEntity {
    @Id
    @Column( name = "c_id" )
    private String id;
    @Column( name = "c_name" )
    private String name;
    @Column( name = "c_dt" )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
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
