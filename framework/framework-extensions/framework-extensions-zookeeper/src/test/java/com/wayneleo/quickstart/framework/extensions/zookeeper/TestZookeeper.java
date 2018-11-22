package com.wayneleo.quickstart.framework.extensions.zookeeper;

import java.util.List;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.wayneleo.quickstart.publish.test.BaseTest;

public class TestZookeeper extends BaseTest {
    @Autowired
    private ZkClient client;

    @Override
    public void beforeTest() {}

    @Override
    public void afterTest() {}

    @Test
    public void testZkClient() {
        List<String> list = client.getChildren( "/" );
        Assert.assertNotNull( list );
        Assert.assertTrue( 0 < list.size() );
        String parent = "/olmp/middleware";
        String node = parent + "/rightsreturn";
        client.createPersistent( parent, true );
        client.createPersistent( node, false );
        print4Json( list );
    }
}
