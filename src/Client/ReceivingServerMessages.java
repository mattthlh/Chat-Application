package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceivingServerMessages extends Thread{

    ScrollingTextPanel scrollingTextPanel;
    Socket socket;
    BufferedReader in;

    public ReceivingServerMessages(Socket socket, ScrollingTextPanel scrollingTextPanel) throws IOException {
        this.scrollingTextPanel = scrollingTextPanel;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            System.out.println("Receiving message from server");

            while (true) {
                String message = in.readLine();

                if(message == null) {
                    Main.leave();
                    break;
                }

                System.out.println("Server: " + message);
                scrollingTextPanel.addNewMessage(message);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
