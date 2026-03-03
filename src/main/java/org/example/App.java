package org.example;

import com.sun.net.httpserver.HttpHandler;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;


public class App
{
    public static void main( String[] args )
    {
        Executor executor = ForkJoinPool.commonPool();

        HttpHandler handler = new PingHandler();

        SimpleHttpServer server = new SimpleHttpServer();

        AirportRepository repository = new AirportRepository(
                "jdbc:postgresql://localhost:5432/demo",
                "postgres",
                "scroll335"
        );
        AirportService service = new AirportService(repository);

        ServerConfig config = new ServerConfig(8081, executor, List.of(
                new Route("/ping",
                        new PingHandler()),
                new Route("/airports",
                        new AirportHandler(service))
        )
        );

        server.launchServerWithConfig(config);
    }
}
