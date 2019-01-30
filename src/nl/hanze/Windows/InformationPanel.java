package nl.hanze.Windows;

import nl.hanze.Main;
import nl.hanze.controllers.FloorController;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    public static JLabel quene1, quene2, quene3, quene4, quene5, quene6, quene7, quene8;

    public InformationPanel(FloorController controller) {
        super();

        JPanel informationPanel = new JPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        InformationPanel.quene1 = new JLabel("Totaal spots: " + controller.getNumberOfOpenSpots());
        this.add(InformationPanel.quene1);

        InformationPanel.quene2 = new JLabel("Member spots: " + controller.getNumberOfOpenSpots());
        this.add(InformationPanel.quene2);

        InformationPanel.quene3 = new JLabel("Totaal spots none: " + controller.getNumberOfOpenSpots());
        this.add(InformationPanel.quene3);

        InformationPanel.quene4 = new JLabel("Totaal spots reservered: " + controller.getNumberOfOpenSpots());
        this.add(InformationPanel.quene4);

        InformationPanel.quene5 = new JLabel("Mem lived: " + controller.getNumberOfOpenSpots());
        this.add(InformationPanel.quene5);

        InformationPanel.quene6 = new JLabel("None lived: " + controller.getNumberOfOpenSpots());
        this.add(InformationPanel.quene6);

        InformationPanel.quene7 = new JLabel("Reservations none: " + controller.getNumberOfOpenSpots());
        this.add(InformationPanel.quene7);

        setVisible(true);

        informationPanel.setMinimumSize(new Dimension(250,500));
        informationPanel.setPreferredSize(new Dimension(100, 100));
    }
}
