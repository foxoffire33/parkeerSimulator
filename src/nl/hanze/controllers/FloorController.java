package nl.hanze.controllers;

import nl.hanze.Car;
import nl.hanze.Location;
import nl.hanze.enums.FloorEnum;
import nl.hanze.models.FloorModel;
import nl.hanze.views.sumulator.floor.FloorViewIndex;

import java.awt.*;
import java.util.ArrayList;

public class FloorController {

    private ArrayList<FloorModel> models = new ArrayList<>();
    private FloorViewIndex view;

    public void addModel(FloorEnum type) {
        this.models.add(new FloorModel(type));
    }

    public void tick() {


        for (int floor = 0; floor < this.models.size(); floor++) {
            FloorModel model = this.models.get(floor);
            for (int row = 0; row < model.getNumberOfRows(); row++) {
                for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(model.getId(), row, place);
                    Car car = model.getCarAt(location);
                    if (car != null) {
                        car.tick();
                        System.out.println("Ticks");
                    }
                }
            }
        }
    }

    public FloorViewIndex getView(int modelID, Color color) {


        try {
            FloorModel model = this.models.get(modelID);

            return new FloorViewIndex(model, color);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Floor not found");
        }
        return null;
    }

}
