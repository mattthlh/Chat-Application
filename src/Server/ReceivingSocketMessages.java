package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceivingSocketMessages extends Thread{

    boolean isRunning = true;
    public void run() {
        while (isRunning) {
            try {
                for (int i = 0; i < StartServer.userSockets.size(); i++) {
                    Socket s = StartServer.userSockets.get(i);
                    BufferedReader in = new BufferedReader(new InputStreamReader(StartServer.userInputStreams.get(i)));

                    String message = in.readLine();

                    System.out.println(message);
                }
                System.out.println("test 2");
            } catch (Exception e) {
                // Throwing an exception
                e.printStackTrace();
            }
        }
    }
}
