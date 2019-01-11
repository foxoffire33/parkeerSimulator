package nl.hanze.models;

import nl.hanze.Car;
import nl.hanze.Location;
import nl.hanze.enums.FloorType;

import java.awt.*;
import java.util.ArrayList;

public class FloorModel {

    private FloorType type;
    private int numberOfRows = 4;
    private int numberOfPlaces = 30;
    private int numberOfOpenSpots = (this.numberOfRows * this.numberOfPlaces);
    private Car[][] cars;
    ;

    public static int NUMBER_OF_MODELS = -1;

    public FloorModel(FloorType type) {
        this.type = type;
        this.initCarsArray(this.numberOfRows,this.numberOfPlaces);
    }

    public FloorModel(FloorType type, int rows) {
        this.type = type;
        this.numberOfRows = rows;
        this.initCarsArray(this.numberOfRows,this.numberOfPlaces);
    }

    private void initCarsArray(int rows, int places) {
        this.cars = new Car[rows][places];
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public int getNumberOfOpenSpots() {
        return this.numberOfOpenSpots;
    }

    public FloorType getType() {
        return this.type;
    }


    private boolean locationIsValid(Location location) {
        int row = location.getRow();
        int place = location.getPlace();
        if (row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }


    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        ;

        this.cars[location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        this.numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int row = 0; row < this.getNumberOfRows(); row++) {
            for (int place = 0; place < this.getNumberOfPlaces(); place++) {
                Location location = new Location(this.type.getValue(), row, place);
                if (this.getCarAt(location) == null) {
                    return location;
                }
            }
        }
        return new Location(0, 0, 0);
    }


    public Car getFirstLeavingCar() {
        for (int row = 0; row < this.getNumberOfRows(); row++) {
            for (int place = 0; place < this.getNumberOfPlaces(); place++) {
                Location location = new Location(this.type.getValue(), row, place);
                Car car = getCarAt(location);
                if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                    return car;
                }
            }
        }
        return null;
    }

}
