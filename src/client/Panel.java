package client;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    static final int width = 1000;
    static final int height = width*9/16;
    public Panel(){
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(25, 120, 149));
        setLayout(null);
    }
}
