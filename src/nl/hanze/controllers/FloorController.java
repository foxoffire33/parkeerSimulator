package nl.hanze.controllers;

import nl.hanze.cars.Car;
import nl.hanze.Location;
import nl.hanze.enums.FloorType;
import nl.hanze.models.FloorModel;
import nl.hanze.mvc.Controller;
import nl.hanze.views.sumulator.floor.FloorViewIndex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FloorController extends Controller {

    private ArrayList<FloorModel> models = new ArrayList<>();
    private FloorViewIndex view;

    public void addModel(FloorType type) {
        this.models.add(new FloorModel(type));
    }

    public void addModel(FloorType type, int rows) {
        this.models.add(new FloorModel(type, rows));
    }

    public void tick() {
        for (FloorModel model : this.models) {
            for (int row = 0; row < model.getNumberOfRows(); row++) {
                for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(model.getType().getValue(), row, place);
                    Car car = model.getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    public FloorViewIndex getView(FloorType floorType) {
        try {
            FloorModel model = this.models.get(floorType.getValue());

            return new FloorViewIndex(model);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Floor not found");
        }
        return null;
    }

    public JPanel getViews() {

        JPanel panel = new JPanel();

        for (FloorModel model : this.models) {
            panel.add(new FloorViewIndex(model));
        }

        GridLayout experimentLayout = new GridLayout(0, 4);
        panel.setLayout(experimentLayout);

        return panel;
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

    public int getNumberOfSpots() {
        int totaal = 0;

        for (FloorModel model : this.models) {
            totaal = totaal + model.getTotalspots();
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

    public void reset() {
        for (FloorModel model : this.models) {
            model.reset();
        }
    }

}
