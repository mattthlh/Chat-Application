package Server;


import javax.swing.*;
import java.awt.*;

public class PlayerListPane extends JPanel {

    public PlayerListPane() {
        setLayout(new GridLayout(0, 3));
    }

    public void addPeople(String name) {
        add(new JLabel(name));
        add(new JButton("KICK"));
        add(new JButton("Make host"));
    }

    public void removePeople() {
    }
}
