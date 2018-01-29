package com.genesis.utils;

import okhttp3.ConnectionPool;

import java.util.concurrent.TimeUnit;

/**
 * @author Terry
 */
public class HttpConfig {


    public long connectTimeout;

    public long readTimeout;

    public long writeTimeout;

    public ConnectionPool connectionPool;

    public HttpConfig(Builder builder) {
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        connectionPool = new ConnectionPool(builder.maxConnection,builder.keepAlive, TimeUnit.MILLISECONDS);
    }

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

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }


        public Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }


        public Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }


        public Builder maxConnection(int maxConnection) {
            this.maxConnection = maxConnection;
            return this;
        }

        public Builder keepAlive(long keepAlive) {
            this.keepAlive = keepAlive;
            return this;
        }

        public HttpConfig build() {
            return new HttpConfig(this);
        }

    }

}
