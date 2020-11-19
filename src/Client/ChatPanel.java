package Client;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    String name;

    ScrollingTextPanel scrollingTextPanel;
    JTextArea textArea;

    public ChatPanel(ScrollingTextPanel scrollingTextPanel) {
        setVisible(true);
        this.scrollingTextPanel = scrollingTextPanel;
        drawComponents();
    }

    private void drawComponents() {
        textArea = new JTextArea();

        textArea.setPreferredSize(new Dimension(500, 25));
        textArea.addKeyListener(new KeyListener(this, scrollingTextPanel));

        add(textArea);
    }
}
