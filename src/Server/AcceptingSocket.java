package Server;

import java.net.Socket;

public class AcceptingSocket extends Thread{

    boolean isRunning = true;
    public void run() {
        while (isRunning) {
            try {
                Socket socket = StartServer.serverSocket.accept();
                StartServer.userSockets.add(socket);
                System.out.println("test");
            } catch (Exception e) {
                // Throwing an exception
                e.printStackTrace();
            }
        }
    }
}
