package com.wayneleo.quickstart.framework.extensions.configuration;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.wayneleo.quickstart.publish.test.BaseTest;

public class Tester extends BaseTest {
    @Autowired
    private ConfigObject conf;

    @Override
    public void beforeTest() {}

    @Override
    public void afterTest() {}

    @Test
    public void read() {
        Assert.assertNotNull( conf );
        Assert.assertTrue( StringUtils.isNotEmpty( conf.getName() ) );
        System.out.println( "======> " + conf.getName() );
    }
}
