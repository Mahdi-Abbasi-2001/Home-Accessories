package client;

import server.Producer;
import server.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ManagerScrollPane extends JPanel {
    public ManagerScrollPane(int x, int y, int width, int height, ArrayList<Producer> producers) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel grid = new JPanel(new GridLayout(producers.size(), 2));
        for (Producer producer : producers) {
            JLabel edit = new JLabel("ویرایش");
            edit.setForeground(Color.BLACK);
            edit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    HomeAccessories.managerPanel_editProducer(producer);
                }
            });
            edit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            grid.add(edit);
            JLabel name = new JLabel(producer.getName());
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
    public ManagerScrollPane(int x, int y, int width, int height, ArrayList<Product> products, String s) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel grid = new JPanel(new GridLayout(products.size(), 1));
        for (Product product : products) {
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
