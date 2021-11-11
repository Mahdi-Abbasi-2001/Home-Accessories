package client;

import javax.swing.*;
import java.awt.*;

public class ComboBox extends JComboBox {
    public ComboBox(int x, int y, int width, int height, String[] items){
        setBounds(x, y, width, height);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        for(String item: items)
            addItem(item);
    }
}
