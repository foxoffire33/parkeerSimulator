package nl.hanze.models;

import nl.hanze.Car;
import nl.hanze.Location;
import nl.hanze.enums.FloorEnum;

import java.awt.*;
import java.util.ArrayList;

public class FloorModel {

    private FloorEnum type;
    private int id;
    private int numberOfRows = 4;
    private int numberOfPlaces = 30;
    private int numberOfOpenSpots = (this.numberOfRows * this.numberOfPlaces);
    private ArrayList<Location> locations;
    private Car[][] cars;

    public static int NUMBER_OF_MODELS = 0;

    public FloorModel(FloorEnum type) {
        this.type = type;
        this.id = id;

        this.locations = new ArrayList<>();

        NUMBER_OF_MODELS++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public Car getCarAt(Location location) {
        //location valied checken
        if (!locationIsValid(location)) {
            return null;
        }
//        return this.cars[location.getRow()][location.getPlace()];
        return new Car() {
            @Override
            public Color getColor() {
                return Color.green;
            }
        };
    }

    private boolean locationIsValid(Location location) {
        int row = location.getRow();
        int place = location.getPlace();
        if (row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            this.numberOfOpenSpots--;
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
        cars[location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        this.numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int place = 0; place < getNumberOfPlaces(); place++) {
                Location location = new Location(this.getId(), row, place);
                if (getCarAt(location) == null) {
                    return location;
                }
            }
        }
        return null;
    }


    public Car getFirstLeavingCar() {
        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int place = 0; place < getNumberOfPlaces(); place++) {
                Location location = new Location(this.getId(), row, place);
                Car car = getCarAt(location);
                if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                    return car;
                }
            }
        }
        return null;
    }

}
