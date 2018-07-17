package com.wayneleo.quickstart.transactional.sample.ddd.tag;

import com.wayneleo.quickstart.transactional.sample.ddd.authority.User;
import com.wayneleo.quickstart.transactional.sample.ddd.authority.UserGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table( name = "test_tag" )
@SuppressWarnings( "serial" )
public class Tag implements Serializable {
    private String id;                 // 主健
    private String name;               // 标签名称
    private TagGlobal global;             // 是否是全行可见;全行可见与只属于某些用户组不能同时满足
    private List<UserGroup> groups;             // 哪些用户组拥有这个标签
    private TagStatus status;             // 标签状态
    private User creater;            // 创建者
    private String createDatetime;     // 创建时间
    private User lastModifier;       // 最后修改者
    private String lastModifyDatetime; // 最后修改时间
    private TagApprovalStatus approvalStatus;     // 最终审批状态
    private User approver;           // 最后审批者
    private String approvalDatetime;   // 最后审批时间

    @Id
    @Column( name = "tag_id", length = 32, nullable = false )
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Column( name = "tag_name", length = 100, nullable = false )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Column( name = "tag_global", nullable = false )
    public TagGlobal getGlobal() {
        return global;
    }

    public void setGlobal( TagGlobal global ) {
        this.global = global;
    }

    @ManyToMany( cascade = CascadeType.REFRESH, targetEntity = UserGroup.class, mappedBy = "tags", fetch = FetchType.LAZY )
    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups( List<UserGroup> groups ) {
        this.groups = groups;
    }

    @Column( name = "tag_status", nullable = false )
    public TagStatus getStatus() {
        return status;
    }

    public void setStatus( TagStatus tagStatus ) {
        this.status = tagStatus;
    }

    @ManyToOne( cascade = CascadeType.REFRESH, fetch = FetchType.LAZY )
    @JoinColumn( name = "tag_creater_id" )
    public User getCreater() {
        return creater;
    }

    public void setCreater( User creater ) {
        this.creater = creater;
    }

    @Column( name = "tag_create_datetime", length = 19, nullable = false )
    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime( String createDatetime ) {
        this.createDatetime = createDatetime;
    }

    @ManyToOne( cascade = CascadeType.REFRESH, fetch = FetchType.LAZY )
    @JoinColumn( name = "tag_modifier_id" )
    public User getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier( User lastModifier ) {
        this.lastModifier = lastModifier;
    }

    @Column( name = "tag_last_modify_datetime", length = 19, nullable = false )
    public String getLastModifyDatetime() {
        return lastModifyDatetime;
    }

    public void setLastModifyDatetime( String lastModifyDatetime ) {
        this.lastModifyDatetime = lastModifyDatetime;
    }

    @Column( name = "tag_approval_status", nullable = false )
    public TagApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus( TagApprovalStatus approvalStatus ) {
        this.approvalStatus = approvalStatus;
    }

    @ManyToOne( cascade = CascadeType.REFRESH, fetch = FetchType.LAZY )
    @JoinColumn( name = "tag_approver_id" )
    public User getApprover() {
        return approver;
    }

    public void setApprover( User approver ) {
        this.approver = approver;
    }

    @Column( name = "tag_approval_datetime", length = 32, nullable = false )
    public String getApprovalDatetime() {
        return approvalDatetime;
    }

    public void setApprovalDatetime( String approvalDatetime ) {
        this.approvalDatetime = approvalDatetime;
    }

    public Tag addGroup( UserGroup group ) {
        this.groups.add( group );
        return this;
    }

    public static enum TagGlobal {
        YES, // 全行标签,不需要分配用户组
        NO; // 不是全行标签,必须分配用户组
    }

    public static enum TagStatus {
        ENABLE, // 启用
        PAUSE, // 暂停
        INVALID; // 失效
    }

    public static enum TagApprovalStatus {
        APPROVE, // 通过
        REJECT, // 驳回
        IN_APPROVAL; // 审批中
    }
}
