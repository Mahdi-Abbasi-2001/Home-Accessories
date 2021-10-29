package client;

import javax.swing.*;
import java.awt.*;

public class Text extends JLabel {
    public Text(int fontSize, Color color, String value){
        setText(value);
        setFont(new Font("Serif", Font.PLAIN, fontSize));
        setForeground(color);
        setHorizontalAlignment(CENTER);
        setAlignmentX(CENTER);
    }
}
