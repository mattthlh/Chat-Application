package Server;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class StartServer {
    static ArrayList<Socket> userSockets = new ArrayList<>();
    static ArrayList<String> userName = new ArrayList<>();
    static ArrayList<DataInputStream> userInputStreams = new ArrayList<>();

    static JFrame frame;
    static PlayerListPane playerListPane;
    static JScrollPane scrollPane;
    static ControlLayout controlLayout;
    static JPanel components;

    static ServerSocket serverSocket;

    static AcceptingSocket acceptingSocket;
    static ReceivingSocketMessages receivingSocketMessages;

    public static void main(String[] args) throws IOException {
        // Initialize Server
        serverSocket = new ServerSocket(5000);
        acceptingSocket = new AcceptingSocket();
        receivingSocketMessages = new ReceivingSocketMessages();

        // Start the threads that accepts and receives socket messages
        acceptingSocket.start();
        receivingSocketMessages.start();

        // Initialize Panels
        playerListPane = new PlayerListPane();
        controlLayout = new ControlLayout();
        scrollPane = new JScrollPane(playerListPane);

        // Add all panel to one specific panel
        components = new JPanel();
        components.setLayout(new BoxLayout(components, BoxLayout.Y_AXIS));

        scrollPane.setPreferredSize(new Dimension(500, 475));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        components.add(scrollPane);
        components.add(controlLayout);

        // Initialize frame
        frame = new JFrame("Chat Server");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.getContentPane().add(components);

        frame.requestFocus();
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        acceptingSocket = new AcceptingSocket();
    }

    public static void stop() throws IOException {
        acceptingSocket.isRunning = false;
        serverSocket.close();

        for (int i = 0; i < userSockets.size(); i++) {
            userSockets.get(i).close();
            userInputStreams.get(i).close();
        }

        System.exit(0);
    }
}
