package org.example;

import com.sun.net.httpserver.HttpHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;


public class App
{
    public static void main( String[] args )
    {
        Executor executor = ForkJoinPool.commonPool();

        HttpHandler handler = new PingHandler();

        ServerConfig config = new ServerConfig("/ping", handler, executor, 8081);

        SimpleHttpServer server = new SimpleHttpServer();
        server.launchServerWithConfig(config);
    }
}
