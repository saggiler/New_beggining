package org.example;

import com.sun.net.httpserver.HttpHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class App
{
    public static void main( String[] args )
    {
        Executor executor = Executors.newFixedThreadPool(10);

        HttpHandler handler = new PingHandler();

        ServerConfig config = new ServerConfig("/ping", handler, executor);

        SimpleHttpServer server = new SimpleHttpServer();
        server.launchServerWithConfig(config);
    }
}
