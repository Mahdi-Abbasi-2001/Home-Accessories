package client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScrollPane extends JPanel {
    public ScrollPane(int x, int y, int width, int height, ArrayList<String> text){
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel grid = new JPanel(new GridLayout(text.size(), 1));
        for (String line: text)
            grid.add(new JLabel(String.format("<html><div align='right' width='%s'>",
                    width-20) + line + "</div></html>"));
        JScrollPane scrollableTextArea = new JScrollPane(grid);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        add(scrollableTextArea);
        setLocation(x, y);
    }
}
