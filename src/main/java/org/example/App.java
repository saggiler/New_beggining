package org.example;

public class App
{
    public static void main( String[] args )
    {
        MyServer myServer = new MyServer();
        MyHandler myHandler = new MyHandler();

        myServer.start(myHandler);
    }
}
