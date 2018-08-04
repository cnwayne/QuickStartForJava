package com.wayneleo.quickstart.transactional.sample.dubbo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.wayneleo.quickstart.transactional.sample.dubbo.TestResult.Node;

public class TestClusterInvoker<T> implements Invoker<T> {
    private static final Logger  logger    = LoggerFactory.getLogger( TestClusterInvoker.class );
    protected final Directory<T> directory;
    protected final boolean      availablecheck;
    private AtomicBoolean        destroyed = new AtomicBoolean( false );

    public TestClusterInvoker( Directory<T> directory ) {
        this( directory, directory.getUrl() );
    }

    public TestClusterInvoker( Directory<T> directory, URL url ) {
        if ( directory == null ) {
            throw new IllegalArgumentException( "service directory == null" );
        }
        this.directory = directory;
        this.availablecheck = url
                .getParameter( Constants.CLUSTER_AVAILABLE_CHECK_KEY, Constants.DEFAULT_CLUSTER_AVAILABLE_CHECK );
    }

    @Override
    public URL getUrl() {
        return directory.getUrl();
    }

    @Override
    public boolean isAvailable() {
        return directory.isAvailable();
    }

    @Override
    public void destroy() {
        if ( destroyed.compareAndSet( false, true ) ) {
            directory.destroy();
        }
    }

    @Override
    public Class<T> getInterface() {
        return directory.getInterface();
    }

    @SuppressWarnings( {
            "unchecked", "rawtypes"
    } )
    @Override
    public Result invoke( Invocation invocation ) throws RpcException {
        List<Invoker<T>> invokers = directory.list( invocation );
        checkInvokers( invokers, invocation );
        RpcContext.getContext().setInvokers( ( List ) invokers );
        Node node = null;
        List<Node> nodes = new ArrayList<>();
        for ( Invoker<T> invoker : invokers ) {
            try {
                doInvoke( invocation.getMethodName(), invoker, invocation );
            }
            catch ( Throwable e ) {
                node = new Node(
                        directory.getUrl().getAddress(),
                        getInterface().getName(),
                        Version.getVersion(),
                        invocation.getMethodName(),
                        NetUtils.getLocalHost(),
                        e );
                nodes.add( node );
            }
        }
        return new RpcResult( new TestResult( nodes ) );
    }

    protected Result doInvoke( String methodName, Invoker<T> invoker, Invocation invocation ) throws Exception {
        int len = getUrl().getMethodParameter( methodName, Constants.RETRIES_KEY, Constants.DEFAULT_RETRIES ) + 1;
        if ( len <= 0 ) {
            len = 1;
        }
        Throwable error = null;
        for ( int i = 0; i < len; i++ ) {
            checkWhetherDestroyed();
            try {
                Result result = invoker.invoke( invocation );
                if ( null != result.getException() ) {
                    throw result.getException();
                }
                return result;
            }
            catch ( Throwable e ) {
                error = e;
                logger.warn(
                        String.format(
                                "服务接口第%d次调用失败(%s -> %s/%s/v%s/%s):%s",
                                ( i + 1 ),
                                NetUtils.getLocalHost(),
                                directory.getUrl().getAddress(),
                                getInterface().getName(),
                                Version.getVersion(),
                                invocation.getMethodName(),
                                error.getMessage() ),
                        error );
            }
        }
        throw new Exception( "尝试调用了" + len + "次，但依然失败。最后一次调用的错误信息是:" + error.getMessage() );
    }

    protected void checkInvokers( List<Invoker<T>> invokers, Invocation invocation ) {
        if ( invokers == null || invokers.isEmpty() ) {
            throw new RpcException(
                    "Failed to invoke the method " +
                            invocation.getMethodName() +
                            " in the service " +
                            getInterface().getName() +
                            ". No provider available for the service " +
                            directory.getUrl().getServiceKey() +
                            " from registry " +
                            directory.getUrl().getAddress() +
                            " on the consumer " +
                            NetUtils.getLocalHost() +
                            " using the dubbo version " +
                            Version.getVersion() +
                            ". Please check if the providers have been started and registered." );
        }
    }

    protected void checkWhetherDestroyed() {
        if ( destroyed.get() ) {
            throw new RpcException(
                    "Rpc cluster invoker for " +
                            getInterface() +
                            " on consumer " +
                            NetUtils.getLocalHost() +
                            " use dubbo version " +
                            Version.getVersion() +
                            " is now destroyed! Can not invoke any more." );
        }
    }
}
