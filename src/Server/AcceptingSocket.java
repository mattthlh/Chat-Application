package Server;

import java.net.Socket;

public class AcceptingSocket extends Thread{

    private int currentUser = 0;

    public void run() {
        System.out.println("Thread 'Accepting Socket' is now open");
        while (true) {
            try {
                Socket socket = StartServer.serverSocket.accept();
                StartServer.userSockets.add(socket);

                ReceivingSocketMessages receivingSocketMessages = new ReceivingSocketMessages(socket, currentUser);
                receivingSocketMessages.num = currentUser;
                receivingSocketMessages.start();

                System.out.println(socket.getLocalAddress() + " is connected!");
                System.out.println("Arraylist size: " + StartServer.userSockets.size());

                currentUser++;
            } catch (Exception e) {
                // Throwing an exception
                e.printStackTrace();
            }
        }
    }
}
