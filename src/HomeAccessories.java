import client.Button;
import client.Panel;
import client.Text;
import client.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HomeAccessories {
    private static Window window;
    public static void main(String[] args) {
        window = new Window();
        startPage();
    }
    private static void startPage(){
        Panel start = new Panel();
        start.add(new Text(25, Color.BLACK, "باسمه تعالی"));
        start.add(new Text(19, Color.BLACK, "لطفا ابتدا به عنوان مدیر ثبت نام کنید."));
        start.add(new Button("ثبت نام"));
        window.add(start);
        window.setVisible(true);
    }
}
