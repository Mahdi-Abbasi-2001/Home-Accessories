package client;

import server.Customer;
import server.Manager;
import server.Producer;
import server.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HomeAccessories {
    static Window window;
    static Panel panel;
    static Manager mgUser;
    static Producer pdUser;
    static Customer ctUser;
    static ArrayList<JComponent> panelComps = new ArrayList<>();
    public static void main(String[] args) {
        window = new Window();
        panel = window.panel;
        welcomePage();
    }

    private static void welcomePage(){
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
                    startPage();
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

    private static void startPage(){
        panelComps.add(new Text(650, 70, 100, 50, "نام کاربری", Color.WHITE, 16));
        TextField username = new TextField(300, 130, 400, 50);
        panelComps.add(username);
        panelComps.add(new Text(650, 190, 100, 50, "رمز عبور", Color.WHITE, 16));
        TextField password = new TextField(300, 250, 400, 50);
        panelComps.add(password);
        panelComps.add(new Text(620, 310, 100, 50, "تکرار رمز عبور", Color.WHITE, 16));
        TextField password_again = new TextField(300, 370, 400, 50);
        panelComps.add(password_again);
        Button submit = new Button(250, 475, 500, 50, "ساخت مدیر", Color.WHITE,
                new Color(146, 0, 255));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (password.getText().equals(password_again.getText())) {
                    mgUser = new Manager(username.getText(), password.getText());
                    Users.manager = mgUser;
                    panelComps.add(new Text(420, 100, 250, 50,
                            "ساخت برنامه با موفقیت تکمیل شد.", Color.GREEN, 16));
                    mainPage();
                }
                else{
                    panelComps.add(new Text(390, 430, 300, 50,
                            "رمز عبور و تکرار رمز عبور همخوانی ندارند.", new Color(255, 0, 0), 17));
                    startPage();
                }
            }
        });
        panelComps.add(submit);
        refreshPage();
    }

    public static void mainPage(){
        Button login = new Button(250, 200, 500, 80, "ورود", Color.WHITE,
                new Color(146, 0, 255));
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginPage();
            }
        });
        panelComps.add(login);
        Button signup = new Button(250, 320, 500, 80, "ثبت نام", Color.WHITE,
                new Color(146, 0, 255));
        signup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signupPage();
            }
        });
        panelComps.add(signup);
        refreshPage();
    }

    public static void loginPage(){}

    public static void signupPage(){
        panelComps.add(new Text(610, 20, 100, 50, "نام و نام خانوادگی", Color.WHITE, 16));
        TextField name = new TextField(300, 80, 400, 30);
        panelComps.add(name);
        panelComps.add(new Text(650, 120, 100, 50, "نام کاربری", Color.WHITE, 16));
        TextField username = new TextField(300, 180, 400, 30);
        panelComps.add(username);
        panelComps.add(new Text(650, 220, 100, 50, "رمز عبور", Color.WHITE, 16));
        TextField password = new TextField(300, 280, 400, 30);
        panelComps.add(password);
        panelComps.add(new Text(620, 320, 100, 50, "تکرار رمز عبور", Color.WHITE, 16));
        TextField password_again = new TextField(300, 380, 400, 30);
        panelComps.add(password_again);
        String[] roles = {"مشتری", "تولیدکننده"};
        ComboBox combo = new ComboBox(600, 435, 100, 30, roles);
        panelComps.add(combo);
        Button submit = new Button(250, 510, 500, 30, "ثبت نام", Color.WHITE,
                new Color(146, 0, 255));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(password.getText().equals(password_again.getText()))
                    switch(combo.getSelectedIndex()){
                        case 0 ->{
                            ctUser = new Customer(name.getText(), username.getText(), password.getText());
                            Users.customers.add(ctUser);
                            customerPanel();
                        }
                        case 1 ->{
                            pdUser = new Producer(name.getText(), username.getText(), password.getText());
                            Users.producers.add(pdUser);
                            producerPanel();
                        }
                    }
                else{
                    panelComps.add(new Text(390, 470, 300, 30,
                            "رمز عبور و تکرار رمز عبور همخوانی ندارند.", new Color(255, 0, 0), 17));
                    signupPage();
                }
            }
        });
        panelComps.add(submit);
        refreshPage();
    }

    public static void customerPanel(){}

    public static void producerPanel(){}

    public static void refreshPage(){
        panel.removeAll();
        for(JComponent jc:panelComps)
            panel.add(jc);
        panelComps.clear();
        panel.revalidate();
        panel.repaint();
    }
}
