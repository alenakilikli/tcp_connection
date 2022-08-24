package tcpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ServerTask implements Runnable {
    private final Socket socket;

    public ServerTask(Socket socket) {
        this.socket = socket;
    }

    private static String createRandomArray(int num) {
        String randomIntsArray = Arrays.toString(IntStream.generate(() -> new Random().nextInt(100)).limit(num).toArray());
        return randomIntsArray;
    }

    @Override
    public void run() {
        try {
            PrintStream dataOut = null;
            dataOut = new PrintStream(socket.getOutputStream());
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = dataIn.readLine()) != null) {
                String response = createRandomArray(Integer.parseInt(line));
                dataOut.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
