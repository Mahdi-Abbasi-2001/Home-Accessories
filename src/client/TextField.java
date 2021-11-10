package client;

import javax.swing.*;
import java.awt.*;

public class TextField extends JTextField {
    public TextField(int x, int y, int width, int height){
        setBounds(x, y, width, height);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }
}
