package com.wayneleo.quickstart.transactional.sample.dubbo;

import java.io.Serializable;
import java.util.List;

public class TestResult implements Serializable {
    private static final long serialVersionUID = -5128102006832034137L;

    public TestResult() {}

    public TestResult( List<Node> nodes ) {
        super();
        this.nodes = nodes;
    }

    public static class Node implements Serializable {
        private static final long serialVersionUID = -723169047992213433L;
        private String            address;
        private String            service;
        private String            version;
        private String            method;
        private String            consumer;
        private Throwable         error;

        public Node() {}

        public Node( String address, String service, String version, String method, String consumer, Throwable error ) {
            super();
            this.address = address;
            this.service = service;
            this.version = version;
            this.method = method;
            this.consumer = consumer;
            this.error = error;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress( String address ) {
            this.address = address;
        }

        public String getService() {
            return service;
        }

        public void setService( String service ) {
            this.service = service;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion( String version ) {
            this.version = version;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod( String method ) {
            this.method = method;
        }

        public String getConsumer() {
            return consumer;
        }

        public void setConsumer( String consumer ) {
            this.consumer = consumer;
        }

        public Throwable getError() {
            return error;
        }

        public void setError( Throwable error ) {
            this.error = error;
        }

        @Override
        public String toString() {
            return "Node [address=" +
                    address +
                    ", service=" +
                    service +
                    ", version=" +
                    version +
                    ", method=" +
                    method +
                    ", consumer=" +
                    consumer +
                    ", error=" +
                    error +
                    "]";
        }
    }

    private List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes( List<Node> nodes ) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "TestResult [nodes=" + nodes + "]";
    }
}
