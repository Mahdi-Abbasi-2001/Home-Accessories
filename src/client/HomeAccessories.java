package client;

import client.Button;
import client.Panel;
import client.Text;
import client.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HomeAccessories {
    static Window window;
    static Panel panel;
    static ArrayList<JComponent> panelComps = new ArrayList<JComponent>();
    public static void main(String[] args) {
        window = new Window();
        panel = window.panel;
        startPage();
    }

    private static void startPage(){
        panelComps.add(new Text(450, 30, 100, 100, "باسمه تعالی", Color.WHITE, 25));
        panelComps.add(new Text(40, 100, 500,300,
                "به فروشگاه مهدی خوش آمدید"+
                "<br />"+
                "لطفا برای راه اندازی برنامه ابتدا به عنوان مدیر ثبت نام کنید."
                , new Color(247, 255, 212), 19));
        Button enter = new Button(100, 435, 300, 45, "ادامه", Color.WHITE,
                new Color(146, 0, 255));
        enter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPage();
            }
        });
        panelComps.add(enter);
        refreshPage();
    }

    private static void mainPage(){
        panelComps.add(new Text(100, 100, 100, 100, "hi", Color.BLACK, 20));
        refreshPage();
    }

    public static void refreshPage(){
        panel.removeAll();
        for(JComponent jc:panelComps)
            panel.add(jc);
        panelComps.clear();
        panel.revalidate();
        panel.repaint();
    }
}
