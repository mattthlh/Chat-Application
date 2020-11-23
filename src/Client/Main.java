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
    static ReceivingServerMessages receivingServerMessages;

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
        frame.repaint();

        System.out.println(name);
        sendMessage(name);

        sendMessage(name + " has joined the lobby.");

        chatPanel.name = name;
        System.out.println("Logged in");
    }

    public static void leave() throws IOException {
        sendMessage("left");

        clientSocket.close();

        components.add(loginPanel);
        components.remove(scrollPane);
        components.remove(chatPanel);

        frame.repaint();
        frame.revalidate();
    }

    public static void connectSocket(String address) throws IOException {

        System.out.println("Address: " + address);
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(address, 5000));

        System.out.println("Did connect?");

        receivingServerMessages = new ReceivingServerMessages(clientSocket, scrollingTextPanel);
        receivingServerMessages.start();
    }

    public static void sendMessage(String message) throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream());

        out.println(message);
        out.flush();
    }
}