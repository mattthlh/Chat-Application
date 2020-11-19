package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPanel extends JPanel {

    JLabel name;
    JTextField fillName;
    JTextField address;
    JButton loginButton;

    public LoginPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        drawComponents();
    }

    private void drawComponents() {

        name = new JLabel("Name: ");
        name.setSize(new Dimension(100, 25));

        fillName = new JTextField("");
        fillName.setSize(new Dimension(100, 25));

        address = new JTextField("");
        address.setPreferredSize(new Dimension(100, 25));


        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = fillName.getText();
                String addressText = address.getText();
                System.out.println("Finding Host Address");

                try {
                    Main.connectSocket(addressText);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    reset();
                    return;
                }

                System.out.println("Connected");

                System.out.println("Logging in");

                System.out.println("LOADING...");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                try {
                    Main.login(username);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        loginButton.setPreferredSize(new Dimension(100, 25));

        add(name);
        add(fillName);
        add(address);
        add(loginButton);
    }

    public void reset() {
        address.setText("");
        loginButton.setText("Incorrect IP Address. Try again!");
        revalidate();
    }
}
