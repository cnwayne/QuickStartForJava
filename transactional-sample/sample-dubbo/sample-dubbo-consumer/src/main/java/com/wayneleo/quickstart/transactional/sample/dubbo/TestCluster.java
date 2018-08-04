package com.wayneleo.quickstart.transactional.sample.dubbo;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.cluster.Directory;

public class TestCluster implements Cluster {
    @Override
    public <T> Invoker<T> join( Directory<T> directory ) throws RpcException {
        return new TestClusterInvoker<T>( directory );
    }
}
