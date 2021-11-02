package client;

import javax.swing.*;

public class Picture extends JLabel {
    public Picture(int x, int y, ImageIcon img){
        setLocation(x, y);
        setIcon(img);
    }
}
