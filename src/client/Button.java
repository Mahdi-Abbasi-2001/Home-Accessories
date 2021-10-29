package client;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String value){
        setFocusable(false);
        setText(value);
        setBorder(BorderFactory.createSoftBevelBorder(20));
        setBackground(Color.lightGray);
        setHorizontalAlignment(CENTER);
    }

}
