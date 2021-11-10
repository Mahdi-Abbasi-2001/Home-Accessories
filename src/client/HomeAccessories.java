package client;

import client.Button;
import client.Panel;
import client.Text;
import client.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        panelComps.add(new Text(450, 10, 100, 50, "باسمه تعالی", Color.WHITE, 25));
        panelComps.add(new Text(250, 50, 400,100,
                "به فروشگاه مهدی خوش آمدید."+
                "<br />"+
                "لطفا برای راه اندازی برنامه بر روی ادامه کلیک کنید."
                , new Color(247, 255, 212), 19));
        panelComps.add(new Line(700, 60, 450, Line.Direction.horizontal));
        Button enter = new Button(170, 435, 500, 30, "ادامه", Color.WHITE,
                new Color(146, 0, 255));
        CheckBox agreement = new CheckBox(450, 370, 300, 30,
                "قوانین و مقررات را مطالعه کردم و با آن موافقم.");
        enter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(agreement.isSelected())
                    mainPage();
            }
        });
        enter.setEnabled(false);
        panelComps.add(enter);
        try {
            ArrayList<String> text = (ArrayList<String>) Files.readAllLines(Paths.get("content/terms_of_service.txt"));
            panelComps.add(new ScrollPane(80, 150, 600, 200, text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        agreement.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                enter.setEnabled((e.getStateChange() == ItemEvent.SELECTED));
            }
        });
        panelComps.add(agreement);
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
