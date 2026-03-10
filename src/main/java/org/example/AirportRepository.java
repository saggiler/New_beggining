package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportRepository {
    private final String url;
    private final String user;
    private final String password;

    public AirportRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public List<Airport> findAll() {
        List<Airport> airports = new ArrayList<>();

        String sql = """
                SELECT airport_code, airport_name, city
                FROM bookings.airports
                """;

        try (Connection connection = DriverManager.getConnection(url, user, password);

             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                airports.add(
                        new Airport(
                                resultSet.getString("airport_code"),
                                resultSet.getString("airport_name"),
                                resultSet.getString("city")

                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при чтении списка аиропортов", e);
        }
        return airports;
    }
}
