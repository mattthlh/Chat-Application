package Client;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyListener implements java.awt.event.KeyListener {

    ChatPanel chatPanel;
    ScrollingTextPanel scrollingTextPanel;

    public KeyListener(ChatPanel chatPanel, ScrollingTextPanel scrollingTextPanel) {
        this.chatPanel = chatPanel;
        this.scrollingTextPanel = scrollingTextPanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        char enterChar = 10;

        String name = chatPanel.name + ": ";
        JTextArea currentText = chatPanel.textArea;
        String message = name + currentText.getText();

        // Checks if the user clicked enter
        if(e.getKeyChar() == enterChar) {
            if(currentText.getText().equalsIgnoreCase("/leave")) {
                try {
                    Main.sendMessage(name + " has left the chat.");
                    Main.leave();
                    return;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            // TODO: Check if the line contains at least one character
            try {
                Main.sendMessage(message);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            currentText.setText("");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
