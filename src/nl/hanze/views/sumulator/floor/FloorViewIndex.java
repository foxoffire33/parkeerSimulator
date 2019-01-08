package nl.hanze.views.sumulator.floor;

import nl.hanze.models.FloorModel;

import javax.swing.*;
import java.awt.*;

public class FloorViewIndex extends JPanel {

    private FloorModel model;

    public FloorViewIndex() {
        this.setBackground(Color.BLUE);
        this.setMinimumSize(new Dimension(1000, 100));
    }

    public FloorViewIndex(FloorModel model) {
        this.model = model;
    }

//    public void paintComponent(Graphics g) {
//        //Recover Graphics2D
//        Graphics2D g2 = (Graphics2D) g;
//        for (int i = 0; i < model.getNumberOfRows(); i++) {
//            for (int ii = 0; ii < model.getNumberOfPlaces(); ii++) {
//                g.fillRect((ii + 20), (i + 20), 10, 10);
//                g.setColor(Color.orange);
//            }
//        }
//    }

}
