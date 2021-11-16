package client;

import server.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        agreement.addItemListener(e -> enter.setEnabled((e.getStateChange() == ItemEvent.SELECTED)));
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
                if (password.getText().equals(password_again.getText()) &&
                        ! username.getText().isEmpty() && ! password.getText().isEmpty()) {
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
        Button login = new Button(250, 150, 500, 80, "ورود", Color.WHITE,
                new Color(146, 0, 255));
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginPage();
            }
        });
        panelComps.add(login);
        Button signup = new Button(250, 270, 500, 80, "ثبت نام", Color.WHITE,
                new Color(146, 0, 255));
        signup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signupPage();
            }
        });
        panelComps.add(signup);
        Button exit = new Button(250, 390, 500, 80, "خروج", Color.WHITE,
                new Color(146, 0, 255));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        panelComps.add(exit);
        refreshPage();
    }

    public static void loginPage(){
        panelComps.add(new Text(650, 120, 100, 50, "نام کاربری", Color.WHITE, 16));
        TextField username = new TextField(300, 180, 400, 30);
        panelComps.add(username);
        panelComps.add(new Text(650, 220, 100, 50, "رمز عبور", Color.WHITE, 16));
        TextField password = new TextField(300, 280, 400, 30);
        panelComps.add(password);
        String[] roles = {"مشتری", "تولیدکننده", "مدیر"};
        ComboBox combo = new ComboBox(600, 435, 100, 30, roles);
        panelComps.add(combo);
        Button submit = new Button(250, 510, 500, 30, "ورود", Color.WHITE,
                new Color(146, 0, 255));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(combo.getSelectedIndex()){
                    case 0 ->{
                        if((ctUser = Users.customerExist(username.getText(), password.getText())) != null)
                            customerPanel();
                        else{
                            panelComps.add(new Text(390, 470, 300, 30,
                                    "نام کاربری یا رمز عبور نادرست است.", new Color(255, 0, 0), 17));
                            loginPage();
                        }
                    }
                    case 1 ->{
                        if((pdUser = Users.producerExist(username.getText(), password.getText())) != null)
                            producerPanel();
                        else{
                            panelComps.add(new Text(390, 470, 300, 30,
                                    "نام کاربری یا رمز عبور نادرست است.", new Color(255, 0, 0), 17));
                            loginPage();
                        }
                    }
                    case 2 ->{
                        if(mgUser.getUsername().equals(username.getText()) && mgUser.getPassword().equals(password.getText()))
                            managerPanel();
                        else{
                            panelComps.add(new Text(390, 470, 300, 30,
                                    "نام کاربری یا رمز عبور نادرست است.", new Color(255, 0, 0), 17));
                            loginPage();
                        }
                    }
                }
            }
        });
        panelComps.add(submit);
        Button cancel = new Button(50, 510, 150, 30, "انصراف", Color.WHITE,
                Color.RED);
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPage();
            }
        });
        panelComps.add(cancel);
        refreshPage();
    }

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
                if(password.getText().equals(password_again.getText()) && Users.signupCheck(name.getText(), username.getText(), password.getText()))
                    switch(combo.getSelectedIndex()){
                        case 0 ->{
                            ctUser = new Customer(name.getText(), username.getText(), password.getText());
                            customerPanel();
                        }
                        case 1 ->{
                            pdUser = new Producer(name.getText(), username.getText(), password.getText());
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
        Button cancel = new Button(50, 510, 150, 30, "انصراف", Color.WHITE,
                Color.RED);
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPage();
            }
        });
        panelComps.add(cancel);
        refreshPage();
    }

    public static void customerPanel(){
        Button buyProduct = new Button(250, 100, 500, 30, "خرید کالا", Color.WHITE,
                new Color(146, 0, 255));
        buyProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerPanel_buyProduct();
            }
        });
        panelComps.add(buyProduct);
        Button myProducts = new Button(250, 160, 500, 30, "کالاهای من", Color.WHITE,
                new Color(146, 0, 255));
        myProducts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerPanel_myProducts();
            }
        });
        panelComps.add(myProducts);
        Button logout = new Button(250, 220, 500, 30, "خروج از حساب کاربری", Color.WHITE,
                new Color(146, 0, 255));
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPage();
            }
        });
        panelComps.add(logout);
        refreshPage();
    }

    public static void customerPanel_buyProduct(){
        panelComps.add(new CustomerScrollPane(100, 80, 800, 400, mgUser.getProducts(), ctUser));
        Button back = new Button(250, 510, 500, 30, "بازگشت", Color.WHITE,
                new Color(146, 0, 255));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerPanel();
            }
        });
        panelComps.add(back);
        refreshPage();
    }

    public static void customerPanel_myProducts(){
        panelComps.add(new CustomerScrollPane(100, 80, 800, 400, ctUser.getProducts()));
        Button back = new Button(250, 510, 500, 30, "بازگشت", Color.WHITE,
                new Color(146, 0, 255));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerPanel();
            }
        });
        panelComps.add(back);
        refreshPage();
    }

    public static void producerPanel(){
        Button addProduct = new Button(250, 100, 500, 30, "افزودن کالا", Color.WHITE,
                new Color(146, 0, 255));
        addProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                producerPanel_addProduct();
            }
        });
        panelComps.add(addProduct);
        Button myProducts = new Button(250, 160, 500, 30, "کالاهای من", Color.WHITE,
                new Color(146, 0, 255));
        myProducts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                producerPanel_myProducts();
            }
        });
        panelComps.add(myProducts);
        Button logout = new Button(250, 220, 500, 30, "خروج از حساب کاربری", Color.WHITE,
                new Color(146, 0, 255));
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPage();
            }
        });
        panelComps.add(logout);
        refreshPage();
    }

    public static void producerPanel_addProduct(){
        String[] productTypes = {"نوع کالا", "کولر", "تلویزیون", "یخچال"};
        ComboBox combo = new ComboBox(800, 70, 100, 30, productTypes);
        combo.addActionListener(e -> {
            switch (combo.getSelectedIndex()){
                case 1 ->{
                    panelComps.add(new Text(670, 345, 100, 30,
                            "توان", Color.WHITE, 16));
                    producerPanel_addProduct();
                }
                case 2 ->{
                    panelComps.add(new Text(670, 345, 100, 30,
                            "اندازه", Color.WHITE, 16));
                    producerPanel_addProduct();
                }
                case 3 ->{
                    panelComps.add(new Text(670, 345, 100, 30,
                            "فضا", Color.WHITE, 16));
                    producerPanel_addProduct();
                }
                default -> producerPanel_addProduct();
            }
        });
        panelComps.add(combo);
        panelComps.add(new Text(650, 50, 100, 30, "نام کالا", Color.WHITE, 16));
        TextField productName = new TextField(300, 100, 400, 30);
        panelComps.add(productName);
        panelComps.add(new Text(650, 150, 100, 30, "قیمت کالا", Color.WHITE, 16));
        TextField productPrice = new TextField(300, 200, 400, 30);
        panelComps.add(productPrice);
        panelComps.add(new Text(650, 250, 100, 30, "تعداد کالا", Color.WHITE, 16));
        TextField productNumber = new TextField(300, 300, 400, 30);
        panelComps.add(productNumber);
        TextField productSpecial = new TextField(300, 390, 400, 30);
        panelComps.add(productSpecial);
        Button submit = new Button(250, 475, 500, 50, "افزودن کالا", Color.WHITE,
                new Color(146, 0, 255));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Product(productName.getText(), Integer.parseInt(productPrice.getText()),
                        Integer.parseInt(productNumber.getText()),
                        combo.getSelectedIndex(), Integer.parseInt(productSpecial.getText()), pdUser);
                producerPanel();
            }
        });
        panelComps.add(submit);
        refreshPage();
    }

    public static void producerPanel_myProducts(){
        panelComps.add(new ProducerScrollPane(100, 80, 800, 400, pdUser.getProducts()));
        Button back = new Button(250, 510, 500, 30, "بازگشت", Color.WHITE,
                new Color(146, 0, 255));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                producerPanel();
            }
        });
        panelComps.add(back);
        refreshPage();
    }

    public static void producerPanel_editProduct(Product product){
        panelComps.add(new Text(650, 70, 100, 50, "نام کالا", Color.WHITE, 16));
        TextField name = new TextField(300, 130, 400, 50);
        name.setText(product.getName());
        panelComps.add(name);
        panelComps.add(new Text(650, 190, 100, 50, "تعداد موجود", Color.WHITE, 16));
        TextField number = new TextField(300, 250, 400, 50);
        number.setText(String.valueOf(product.getNumber()));
        panelComps.add(number);
        panelComps.add(new Text(620, 310, 100, 50, "قیمت کالا", Color.WHITE, 16));
        TextField price = new TextField(300, 370, 400, 50);
        price.setText(String.valueOf(product.getPrice()));
        panelComps.add(price);
        Button submit = new Button(250, 430, 500, 30, "ویرایش", Color.WHITE,
                new Color(146, 0, 255));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                product.setName(name.getText());
                product.setNumber(Integer.parseInt(number.getText()));
                product.setPrice(Integer.parseInt(price.getText()));
                producerPanel_myProducts();
            }
        });
        panelComps.add(submit);
        Button cancel = new Button(250, 470, 500, 30, "انصراف", Color.WHITE,
                new Color(146, 0, 255));
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                producerPanel_myProducts();
            }
        });
        panelComps.add(cancel);
        Button delete = new Button(250, 510, 500, 30, "حذف کالا", Color.WHITE,
                Color.red);
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                product.remove();
                producerPanel_myProducts();
            }
        });
        panelComps.add(delete);
        refreshPage();
    }

    public static void managerPanel(){
        Button producers = new Button(250, 100, 500, 30, "تولید کنندگان", Color.WHITE,
                new Color(146, 0, 255));
        producers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                managerPanel_Producers();
            }
        });
        panelComps.add(producers);
        Button products = new Button(250, 160, 500, 30, "کالاها", Color.WHITE,
                new Color(146, 0, 255));
        products.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                managerPanel_products();
            }
        });
        panelComps.add(products);
        Button discount = new Button(250, 210, 500, 30, "تخفیف", Color.WHITE,
                new Color(146, 0, 255));
        discount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                managerPanel_discount();
            }
        });
        panelComps.add(discount);
        Button logout = new Button(250, 260, 500, 30, "خروج از حساب کاربری", Color.WHITE,
                new Color(146, 0, 255));
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPage();
            }
        });
        panelComps.add(logout);
        refreshPage();
    }

    public static void managerPanel_Producers(){
        panelComps.add(new ManagerScrollPane(100, 80, 800, 400, mgUser.getProducers()));
        Button back = new Button(250, 510, 500, 30, "بازگشت", Color.WHITE,
                new Color(146, 0, 255));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                managerPanel();
            }
        });
        panelComps.add(back);
        refreshPage();
    }

    public static void managerPanel_editProducer(Producer producer){
        panelComps.add(new Text(650, 70, 100, 50, "نام تولیدکننده", Color.WHITE, 16));
        TextField name = new TextField(300, 130, 400, 50);
        name.setText(producer.getName());
        panelComps.add(name);
        Button submit = new Button(250, 430, 500, 30, "ویرایش", Color.WHITE,
                new Color(146, 0, 255));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mgUser.changeProducerName(producer, name.getText());
                managerPanel_Producers();
            }
        });
        panelComps.add(submit);
        Button cancel = new Button(250, 470, 500, 30, "انصراف", Color.WHITE,
                new Color(146, 0, 255));
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                managerPanel_Producers();
            }
        });
        panelComps.add(cancel);
        refreshPage();
    }

    public static void managerPanel_products(){
        panelComps.add(new ManagerScrollPane(100, 80, 800, 400, mgUser.getProducts(), "s"));
        Button back = new Button(250, 510, 500, 30, "بازگشت", Color.WHITE,
                new Color(146, 0, 255));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                managerPanel();
            }
        });
        panelComps.add(back);
        refreshPage();
    }

    public static void managerPanel_discount(){
        panelComps.add(new Text(650, 70, 300, 50, "میزان تخفیف(بر حسب درصد)", Color.WHITE, 16));
        TextField discount = new TextField(300, 130, 400, 50);
        panelComps.add(discount);
        Button submit = new Button(250, 430, 500, 30, "ویرایش", Color.WHITE,
                new Color(146, 0, 255));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mgUser.setDiscount(Integer.parseInt(discount.getText()));
                managerPanel();
            }
        });
        panelComps.add(submit);
        Button cancel = new Button(250, 470, 500, 30, "انصراف", Color.WHITE,
                new Color(146, 0, 255));
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                managerPanel();
            }
        });
        panelComps.add(cancel);
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
