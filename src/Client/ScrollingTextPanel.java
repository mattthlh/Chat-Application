package Client;

import javax.swing.*;

public class ScrollingTextPanel extends JPanel {

    public ScrollingTextPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addNewMessage(String message) {
//        message.setBackground(Color.LIGHT_GRAY);
//        message.setOpaque(true);

        add(new JLabel(message));
        revalidate();
    }
}
