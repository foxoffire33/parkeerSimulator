package nl.hanze.views.sumulator.floor;

import nl.hanze.cars.Car;
import nl.hanze.Location;
import nl.hanze.models.FloorModel;
import nl.hanze.mvc.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FloorViewIndex extends View {

    private FloorModel model;
    private Color g2Color = Color.black;

    public FloorViewIndex(FloorModel model) {
        this.model = model;
       this.setPreferredSize(new Dimension((60 * this.model.getNumberOfRows()),500));

    }

    public void paintComponent(Graphics g) {

        ArrayList<Location> locations = new ArrayList<>();


        Graphics2D g2 = (Graphics2D) g;
        for (int row = 0; row < model.getNumberOfRows(); row++) {
            for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                Car car = this.model.getCarAt(new Location(0,row,place));

                Color color = car == null ? Color.black : car.getColor();
                g2.setColor(color);

                g2.fillRect(
                        0 * 260 + (1 + (int) Math.floor(row * 0.5)) * 75 + (row % 2) * 20,
                        60 + place * 10,
                        20 - 1,
                        10 - 1);
            }
        }

    }


}
