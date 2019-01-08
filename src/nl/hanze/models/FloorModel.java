package nl.hanze.models;

import nl.hanze.enums.FloorEnum;

public class FloorModel {

    private FloorEnum type;
    private int id;
    private int numberOfRows = 3;
    private int numberOfPlaces = 30;

    public FloorModel(FloorEnum type, int id) {
        this.type = type;
        this.id = id;
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
}
