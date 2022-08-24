package tcpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Server {

    private final static int SERVER_PORT = 3000;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);        

        while (true) {                                                    //cycle is getting connection, opening thread for it and  continue listening incoming messages
            Socket socket = serverSocket.accept();                        //waiting tcp connection
            Runnable serverTask = new ServerTask(socket);                 
            new Thread(serverTask).start();                               //starting process and immediatly go back to waiting new connection
        }


    }

}
