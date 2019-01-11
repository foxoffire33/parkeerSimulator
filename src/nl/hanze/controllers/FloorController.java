package nl.hanze.controllers;

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

    public FloorViewIndex getView() {
        return this.view;
    }

}
