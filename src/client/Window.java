package client;

import javax.swing.*;

public class Window extends JFrame {
    Panel panel;
    public Window(){
        setUndecorated(true);
        panel = new Panel();
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
