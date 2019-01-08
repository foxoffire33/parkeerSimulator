package nl.hanze.models;

import nl.hanze.enums.FloorEnum;

public class FloorModel {

    private FloorEnum type;
    private int id;
    private int numberOfRows = 2;
    private int numberOfPlaces = 30;

    public FloorModel(FloorEnum type, int id) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
