package Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {

    static ScrollingTextPanel scrollingTextPanel;
    static ChatPanel chatPanel;
    static LoginPanel loginPanel;
    static JScrollPane scrollPane;

    static JPanel components;
    static JFrame frame;
    static Socket clientSocket;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        // Initialize Panels
        loginPanel = new LoginPanel();
        scrollingTextPanel = new ScrollingTextPanel();
        chatPanel = new ChatPanel(scrollingTextPanel);
        scrollPane = new JScrollPane(scrollingTextPanel);

        // Add all panel to one specific panel
        components = new JPanel();
        components.setLayout(new BoxLayout(components, BoxLayout.Y_AXIS));

        components.add(loginPanel);

        // Initialize scrolling panel
        scrollPane.setPreferredSize(new Dimension(500, 475));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Initialize frame
        frame = new JFrame("Chat");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.getContentPane().add(components);

        frame.requestFocus();
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    public static void login(String name) throws IOException {
        components.remove(loginPanel);
        components.add(scrollPane);
        components.add(chatPanel);

        frame.revalidate();

        sendMessage(name + " has joined the lobby.");

        chatPanel.name = name;
        System.out.println("Logged in");
    }

    public static void connectSocket(String address) throws IOException {
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(address, 5000));
    }

    // TODO: Change addNewMessage to send message to everyone.
    public static void sendMessage(String message) throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream());

        System.out.println("Test 1");
        out.println(message);
        out.flush();
    }
}