package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Запуск сервера");

            ServerSocket serverSocket = new ServerSocket(8081);
            System.out.println("Сервер запущен на порту 8081");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился");

            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(reader);

            String thisLine = bufferedReader.readLine();

            System.out.println("Это " + thisLine);

            OutputStream outputStream = clientSocket.getOutputStream();

            if (thisLine != null && thisLine.startsWith("GET /ping")) {

                String answer = "pong";
                String httpResponse =
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text; charset=UTF-8\r\n" +
                                "Content-Length: " + answer.length() + "\r\n" +
                                "\r\n" +
                                answer;

                outputStream.write(httpResponse.getBytes());
                outputStream.flush();
            }

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
