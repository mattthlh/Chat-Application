package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ReceivingSocketMessages extends Thread{

    Socket socket;
    int num;
    ArrayList<Socket> userSockets = StartServer.userSockets;

    public ReceivingSocketMessages(Socket socket, int num) {
        this.socket = socket;
        this.num = num;
    }

    public void run() {
        try {
            Socket socket = userSockets.get(num);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String name = in.readLine();
            StartServer.addUsers(name);

            System.out.println("Thread 'Receiving Socket Messages' for user " + name + " is now open");

            while (true) {
                String message = in.readLine();


                if(message == null || message.equalsIgnoreCase("left")) {
                    socket.close();
                    userSockets.remove(num);
                    StartServer.userName.remove(num);
                    StartServer.removeUser(num);
                    break;
                }

                sendMessageToAllSockets(userSockets, message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToAllSockets(ArrayList<Socket> userSockets, String message) throws IOException {
        for (Socket s : userSockets) {
            PrintWriter out = new PrintWriter(s.getOutputStream());

            out.println(message);
            out.flush();
        }
    }
}
