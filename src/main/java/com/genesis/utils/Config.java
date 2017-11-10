package com.genesis.utils;

import okhttp3.ConnectionPool;

/**
 * @author Terry
 */
public class Config {


    private long connectTimeout;

    private long readTimeout;

    private long writeTimeout;

    private ConnectionPool connectionPool;


    public static class Builder{

        private long connectTimeout;

        private long readTimeout;

        private long writeTimeout;

        private int maxConnection;

        private long keepAlive;

        public Builder() {
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
            this.maxConnection = 5;
            this.keepAlive = 300000;
        }

        public long getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public long getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
        }

        public long getWriteTimeout() {
            return writeTimeout;
        }

        public void setWriteTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
        }

        public int getMaxConnection() {
            return maxConnection;
        }

        public void setMaxConnection(int maxConnection) {
            this.maxConnection = maxConnection;
        }

        public long getKeepAlive() {
            return keepAlive;
        }

        public void setKeepAlive(long keepAlive) {
            this.keepAlive = keepAlive;
        }
    }

}
