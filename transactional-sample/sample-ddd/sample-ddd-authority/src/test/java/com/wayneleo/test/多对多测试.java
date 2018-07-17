package com.wayneleo.test;

import com.wayneleo.quickstart.publish.test.BaseTest;
import com.wayneleo.quickstart.transactional.sample.ddd.authority.*;
import com.wayneleo.quickstart.transactional.sample.ddd.tag.Tag;
import com.wayneleo.quickstart.transactional.sample.ddd.tag.Tag.TagApprovalStatus;
import com.wayneleo.quickstart.transactional.sample.ddd.tag.Tag.TagGlobal;
import com.wayneleo.quickstart.transactional.sample.ddd.tag.Tag.TagStatus;
import com.wayneleo.quickstart.transactional.sample.ddd.tag.TagDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class 多对多测试 extends BaseTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserGroupDao groupDao;
    @Autowired
    private UserRoleDao roleDao;
    @Autowired
    private UserAuthorityDao authorityDao;
    @Autowired
    private TagDao tagDao;

    @Override
    public void beforeTest() {
        System.out.println( "\n\n====== 测试要开始咯...\n\n" );
    }

    @Override
    public void afterTest() {
        System.out.println( "\n\n====== 测试结束了呢...\n\n" );
    }

    @org.junit.Test
    public void 增() {
        User user = null;
        List<User> users = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            user = new User();
            user.setId( "user_id_" + i );
            user.setName( "user_" + i );
            users.add( userDao.save( user ) );
        }
        Tag tag = null;
        List<Tag> tags = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            tag = new Tag();
            tag.setId( "tag_id_" + i );
            tag.setName( "tag_name_" + i );
            tag.setGlobal( TagGlobal.YES );
            tag.setStatus( TagStatus.ENABLE );
            tag.setCreater( users.get( 0 ) );
            tag.setCreateDatetime( "2018-04-12 17:37" );
            tag.setLastModifier( users.get( 1 ) );
            tag.setLastModifyDatetime( "2018-04-12 17:37" );
            tag.setApprovalStatus( TagApprovalStatus.APPROVE );
            tag.setApprover( users.get( 2 ) );
            tag.setApprovalDatetime( "2018-04-12 17:37" );
            tags.add( tagDao.save( tag ) );
        }
        UserGroup group = null;
        List<UserGroup> groups = new ArrayList<>();
        for ( int i = 0; i < 2; i++ ) {
            group = new UserGroup();
            group.setId( "group_id_" + i );
            group.setName( "group_" + i );
            group.setUsers( users );
            group.setTags( tags );
            groups.add( groupDao.save( group ) );
        }
        UserAuthority authority = null;
        List<UserAuthority> authorities = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            authority = new UserAuthority();
            authority.setId( "authority_id_" + i );
            authority.setName( "authority_" + i );
            authority.setMark( "mark_" + i );
            authorityDao.save( authority );
            authorities.add( authorityDao.save( authority ) );
        }
        UserRole role = null;
        List<UserRole> roles = new ArrayList<>();
        for ( int i = 0; i < 2; i++ ) {
            role = new UserRole();
            role.setId( "role_id_" + i );
            role.setName( "role_" + i );
            role.setUsers( users );
            role.setAuthorities( authorities );
            roles.add( roleDao.save( role ) );
        }
    }

    @Transactional
    public void 查_通过用户模型查询出相应的其它模型() {
        User user = userDao.getOne( "user_id_0" );
        System.out.println( "组     " + user.getGroups() );
        System.out.println( "角色   " + user.getRoles() );
        System.out.println( "权限   " + user.allAuthorities() );
        System.out.println( "用户组 " + user.getGroups() );
        System.out.println( "标签   " + user.getTags() );
    }
}
