package client;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    static final int width = 525;
    static final int height = 700;
    public Window(){
        setTitle("فروشگاه زنجیره ای مهدی");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setIconImage((new ImageIcon("images/shop_logo.jpg")).getImage());
        getContentPane().setBackground(Color.BLACK);
    }
}
