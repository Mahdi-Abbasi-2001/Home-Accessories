package client;

import server.Customer;
import server.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomerScrollPane extends JPanel {
    public CustomerScrollPane(int x, int y, int width, int height, ArrayList<Product> products, Customer customer) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel grid = new JPanel(new GridLayout(products.size(), 3));
        for (Product product : products) {
            JLabel buy = new JLabel("خرید");
            buy.setForeground(Color.BLACK);
            buy.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    customer.getProducts().add(product);
                    product.setNumber(product.getNumber()-1);
                    HomeAccessories.customerPanel();
                }
            });
            buy.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(buy);
            JLabel price = new JLabel(product.getPrice()+"T");
            price.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(price);
            JLabel name = new JLabel(product.getName());
            name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(name);
        }
        JScrollPane scrollableTextArea = new JScrollPane(grid);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        add(scrollableTextArea);
        setLocation(x, y);
    }
    public CustomerScrollPane(int x, int y, int width, int height, ArrayList<Product> products) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel grid = new JPanel(new GridLayout(products.size(), 2));
        for (Product product : products) {
            JLabel price = new JLabel(product.getPrice()+"T");
            price.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(price);
            JLabel name = new JLabel(product.getName());
            name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(name);
        }
        JScrollPane scrollableTextArea = new JScrollPane(grid);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        add(scrollableTextArea);
        setLocation(x, y);
    }

}
