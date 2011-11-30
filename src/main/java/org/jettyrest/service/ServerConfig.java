package org.jettyrest.service;

import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.ssl.SslConnector;
import org.eclipse.jetty.util.thread.ThreadPool;

public class ServerConfig {

    private int port = 8080;

    private ThreadPool threadPool;

    // default values taken from org.eclipse.jetty.http.HttpBuffers
    private int requestHeaderSize = 6 * 1024;
    private int requestBufferSize = 12 * 1024;
    private int responseHeaderSize = 6 * 1024;
    private int responseBufferSize = 32 * 1024;

    private boolean enableRequestLog;
    private RequestLog requestLog;
    private boolean enableGzipCompression;
    private SslConnector sslConnector;
    private boolean enableSSL;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public int getRequestHeaderSize() {
        return requestHeaderSize;
    }

    public void setRequestHeaderSize(int requestHeaderSize) {
        this.requestHeaderSize = requestHeaderSize;
    }

    public int getRequestBufferSize() {
        return requestBufferSize;
    }

    public void setRequestBufferSize(int requestBufferSize) {
        this.requestBufferSize = requestBufferSize;
    }

    public int getResponseHeaderSize() {
        return responseHeaderSize;
    }

    public void setResponseHeaderSize(int responseHeaderSize) {
        this.responseHeaderSize = responseHeaderSize;
    }

    public int getResponseBufferSize() {
        return responseBufferSize;
    }

    public void setResponseBufferSize(int responseBufferSize) {
        this.responseBufferSize = responseBufferSize;
    }

    public boolean isEnableRequestLog() {
        return enableRequestLog;
    }

    public void setEnableRequestLog(boolean enableRequestLog) {
        this.enableRequestLog = enableRequestLog;
    }

    public RequestLog getRequestLog() {
        return requestLog;
    }

    public void setRequestLog(RequestLog requestLog) {
        this.requestLog = requestLog;
    }


    public SslConnector getSslConnector() {
        return sslConnector;
    }

    public void setSslConnector(SslConnector sslConnector) {
        this.sslConnector = sslConnector;
    }

    public boolean isEnableGzipCompression() {
        return enableGzipCompression;
    }

    public void setEnableGzipCompression(boolean enableGzipCompression) {
        this.enableGzipCompression = enableGzipCompression;
    }


    public boolean isEnableSSL() {
        return enableSSL;
    }

    public void setEnableSSL(boolean enableSSL) {
        this.enableSSL = enableSSL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerConfig)) return false;

        ServerConfig that = (ServerConfig) o;

        if (enableGzipCompression != that.enableGzipCompression) return false;
        if (enableRequestLog != that.enableRequestLog) return false;
        if (enableSSL != that.enableSSL) return false;
        if (port != that.port) return false;
        if (requestBufferSize != that.requestBufferSize) return false;
        if (requestHeaderSize != that.requestHeaderSize) return false;
        if (responseBufferSize != that.responseBufferSize) return false;
        if (responseHeaderSize != that.responseHeaderSize) return false;
        if (requestLog != null ? !requestLog.equals(that.requestLog) : that.requestLog != null) return false;
        if (sslConnector != null ? !sslConnector.equals(that.sslConnector) : that.sslConnector != null) return false;
        if (threadPool != null ? !threadPool.equals(that.threadPool) : that.threadPool != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = port;
        result = 31 * result + (threadPool != null ? threadPool.hashCode() : 0);
        result = 31 * result + requestHeaderSize;
        result = 31 * result + requestBufferSize;
        result = 31 * result + responseHeaderSize;
        result = 31 * result + responseBufferSize;
        result = 31 * result + (enableRequestLog ? 1 : 0);
        result = 31 * result + (requestLog != null ? requestLog.hashCode() : 0);
        result = 31 * result + (enableGzipCompression ? 1 : 0);
        result = 31 * result + (sslConnector != null ? sslConnector.hashCode() : 0);
        result = 31 * result + (enableSSL ? 1 : 0);
        return result;
    }
}