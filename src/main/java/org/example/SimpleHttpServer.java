package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public void launchServerWithConfig(ServerConfig config) {
        try {
            HttpServer server = createServer();
            registerHandlerPath(server, config);
            startServer(server, config);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка запуска Http сервера", e);
        }
    }

    private HttpServer createServer() throws IOException {
        return HttpServer.create(new InetSocketAddress(8081), 0);
    }

    private void registerHandlerPath(HttpServer server, ServerConfig config) {
        server.createContext(config.getPath(), config.getHandler());
    }

    private void startServer(HttpServer server, ServerConfig config) {
        server.setExecutor(config.getExecutor());
        server.start();
        System.out.println("HTTP сервер запущен на порту 8081");
    }
}
