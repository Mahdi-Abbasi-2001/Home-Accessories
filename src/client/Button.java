package client;

import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {
    public Button(int x, int y, int width, int height, String value, Color fgClr, Color bgClr){
        setBounds(x, y, width, height);
        setText(value);
        setForeground(fgClr);
        setBackground(bgClr);
        setBorder(BorderFactory.createSoftBevelBorder(0, bgClr, Color.BLACK));
    }
}
