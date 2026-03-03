package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AirportHandler implements HttpHandler {

    private final AirportService service;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public AirportHandler(AirportService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange)
            throws IOException {

        if (!exchange.getRequestMethod()
                .equalsIgnoreCase("GET")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        List<Airport> airports =
                service.getAllAirports();

        String json = gson.toJson(airports);

        byte[] bytes =
                json.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders()
                .set("Content-Type",
                        "application/json; charset=UTF-8");

        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream os =
                     exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
