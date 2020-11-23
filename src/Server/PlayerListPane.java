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

    public void removePeople(int index) {
        int startingPoint = index * 3;

        remove(startingPoint);
        remove(startingPoint + 1);
        remove(startingPoint + 2);
    }
}
