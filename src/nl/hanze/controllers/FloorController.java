package nl.hanze.controllers;

import nl.hanze.Car;
import nl.hanze.Location;
import nl.hanze.enums.FloorEnum;
import nl.hanze.models.FloorModel;
import nl.hanze.views.sumulator.floor.FloorViewIndex;

import java.awt.*;

public class FloorController {

    private FloorModel model;
    private FloorViewIndex view;

    public FloorController(int id, Color color) {
        this.model = new FloorModel(FloorEnum.FLOOR_TYPE_NONE, id);
        this.model.setId(id);
        this.view = new FloorViewIndex(this.model, color);
        this.model.setNumberOfRows(6);
    }

    public void tick() {
        for (int row = 0; row < this.model.getNumberOfRows(); row++) {
            for (int place = 0; place < this.model.getNumberOfPlaces(); place++) {
                Location location = new Location(this.model.getId(), row, place);
                Car car = this.model.getCarAt(location);
                if (car != null) {
                    car.tick();
                }
            }
        }
    }

    public FloorViewIndex getView() {
        return this.view;
    }

}
