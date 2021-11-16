package client;

import javax.swing.*;
import java.awt.*;

public class CheckBox extends JCheckBox {
    public CheckBox(int x, int y, int width, int height, String value){
        setBounds(x, y, width, height);
        setText(value);
        setOpaque(false);
        setHorizontalTextPosition(SwingConstants.LEFT);
        setForeground(Color.WHITE);
    }
}
