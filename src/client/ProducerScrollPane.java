package client;

import server.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProducerScrollPane extends JPanel {
    public ProducerScrollPane(int x, int y, int width, int height, ArrayList<Product> products) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel grid = new JPanel(new GridLayout(products.size(), 4));
        for (Product product : products) {
            JLabel edit = new JLabel("ویرایش");
            edit.setForeground(Color.BLACK);
            edit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    HomeAccessories.producerPanel_editProduct(product);
                }
            });
            edit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(edit);
            JLabel price = new JLabel(product.getPrice()+"T");
            price.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(price);
            JLabel number = new JLabel(String.valueOf(product.getNumber()));
            number.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(number);
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
