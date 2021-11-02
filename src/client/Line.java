package client;

import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {
    public Line(int x, int y, int length, Direction dir){
        setBackground(Color.BLACK);
        switch (dir) {
            case horizontal -> setBounds(x, y, 1, length);
            case vertical -> setBounds(x, y, length, 1);
        }
    }
    public enum Direction{
        horizontal,
        vertical
    }
}
