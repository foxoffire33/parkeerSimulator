package nl.hanze.Windows;

import javax.swing.*;
import java.awt.*;

public class LagendaBar extends JPanel {

    public LagendaBar() {
        this.add(this.coloredPanel(Color.red, "Members"));
        this.add(this.coloredPanel(Color.blue, "None members"));
        this.add(this.coloredPanel(Color.yellow, "Reservered"));

        this.setSize(new Dimension(30, 30));
        this.setVisible(true);
    }

    private JPanel coloredPanel(Color color, String text) {
        JPanel panel = new JPanel();

        JPanel coloredPanel = new JPanel();
        coloredPanel.setBackground(color);

        panel.setSize(new Dimension(20, 20));

        panel.add(new JLabel(text));
        panel.add(coloredPanel);

        return panel;
    }

}
