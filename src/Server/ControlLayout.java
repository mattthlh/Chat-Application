package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlLayout extends JPanel {

    JButton stop;

    public ControlLayout() {
        setLayout(new FlowLayout());
        drawComponents();
    }

    public void drawComponents() {
        stop = new JButton("Stop Server");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StartServer.stop();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        add(stop);
    }
}
