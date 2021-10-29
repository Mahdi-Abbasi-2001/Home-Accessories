package client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    static final int width = 525;
    static final int height = 700;
    public Panel(){
        setPreferredSize(new Dimension(width, height));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(9, 199, 64));
        Container pane = new Container();
    }
}
