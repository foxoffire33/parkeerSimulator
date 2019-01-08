package nl.hanze.views.sumulator.floor;

import nl.hanze.Car;
import nl.hanze.Location;
import nl.hanze.models.FloorModel;

import javax.swing.*;
import java.awt.*;

public class FloorViewIndex extends JPanel {

    private FloorModel model;

    public FloorViewIndex() {
        this.setBackground(Color.BLUE);
    }

    public FloorViewIndex(FloorModel model) {
        this.model = model;
        this.setSize(new Dimension(300,600));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int row = 0; row < model.getNumberOfRows(); row++) {
            for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                g2.setColor(Color.gray);
                System.out.println("Model id is: " + model.getId());
                g2.fillRect(
                        model.getId() * 260 + (1 + (int) Math.floor(row * 0.5)) * 75 + (row % 2) * 20,
                        60 + place * 10,
                        20 - 1,
                        10 - 1);
            }
        }

    }


}
