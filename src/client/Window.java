package client;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    Panel panel;
    public Window(){
        setUndecorated(true);
        panel = new Panel();
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setIconImage((new ImageIcon("images/shop_logo.jpg")).getImage());
        setVisible(true);
    }
}
