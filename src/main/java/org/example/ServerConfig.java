package org.example;

import com.sun.net.httpserver.HttpHandler;

import java.util.concurrent.Executor;

public class ServerConfig {
    private final String path;
    private final HttpHandler handler;
    private final Executor executor;

    public ServerConfig(String path, HttpHandler handler, Executor executor) {
        this.path = path;
        this.handler = handler;
        this.executor = executor;
    }

    public String getPath() {
        return path;
    }

    public HttpHandler getHandler() {
        return handler;
    }

    public Executor getExecutor() {
        return executor;
    }
}
