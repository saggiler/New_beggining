package org.example;

import java.util.List;
import java.util.concurrent.Executor;

public class ServerConfig {
    private final int port;
    private final Executor executor;
    private final List<Route> routes;

    public ServerConfig(int port, Executor executor, List<Route> routes) {
        this.executor = executor;
        this.port = port;
        this.routes = routes;
    }

    public int getPort() {
        return port;
    }

    public Executor getExecutor() {
        return executor;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
