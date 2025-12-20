package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyServer {

    public void start(MyHandler myHandler) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

            server.createContext("/ping", myHandler);

            server.setExecutor(null);
            server.start();

            System.out.println("Сервер запущен на порту 8081");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
