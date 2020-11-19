package Testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",5000));

        System.out.println(socket.isConnected());

        Scanner in = new Scanner(socket.getInputStream());

        PrintWriter out = new PrintWriter(socket.getOutputStream());

        out.println("Hello");
        out.flush();

        out.println("How are you?");
        out.flush();
    }
}
