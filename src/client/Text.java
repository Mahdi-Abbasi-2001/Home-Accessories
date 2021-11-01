package client;

import javax.swing.*;
import java.awt.*;

public class Text extends JLabel {
    public Text(int x, int y, int width, int height, String value, Color fgClr, int fontSize){
        setBounds(x, y, width, height);
        if (value.contains("<br />"))
            setText("<html><div align='right'>" + value + "</div></html>");
        else
            setText(value);
        setFont(new Font("Serif", Font.PLAIN, fontSize));
        setForeground(fgClr);
    }
}
