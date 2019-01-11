package nl.hanze.controllers;

import nl.hanze.Car;
import nl.hanze.Location;
import nl.hanze.enums.FloorEnum;
import nl.hanze.models.FloorModel;
import nl.hanze.views.sumulator.floor.FloorViewIndex;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FloorController {

    private ArrayList<FloorModel> models = new ArrayList<>();
    private FloorViewIndex view;

    public void addModel(FloorEnum type,int id) {
        this.models.add(new FloorModel(type,id));
    }

    public void tick() {
        for (FloorModel model : this.models) {
            for (int row = 0; row < model.getNumberOfRows(); row++) {
                for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(model.getId(), row, place);
                    Car car = model.getCarAt(location);
                    if (car != null) {
                        car.tick();
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

    public FloorModel getModel(int modelID) {
        try {
            return this.models.get(modelID);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Floor not found");
        }
        return null;
    }

    public int getNumberOfOpenSpots() {
        int totaal = 0;

        for (FloorModel model : this.models) {
            totaal = totaal + model.getNumberOfOpenSpots();
        }
        return totaal;
    }

    public Car getFirstLeavingCar() {

        for (FloorModel model : this.models) {
            Car car = model.getFirstLeavingCar();
            if (car != null) {
                return car;
            }
        }
        return null;
    }

    public void setCarAt(Location location, Car car) {
        FloorModel model = this.getModel(location.getFloor());
        model.setCarAt(location, car);
    }


}
