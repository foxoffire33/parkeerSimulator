package nl.hanze.controllers;

import nl.hanze.enums.FloorEnum;
import nl.hanze.models.FloorModel;
import nl.hanze.views.sumulator.floor.FloorViewIndex;

public class FloorController {

    private FloorModel model;
    private FloorViewIndex view;

    public FloorController() {
        this.model = new FloorModel(FloorEnum.FLOOR_TYPE_NONE, 0);
        this.view = new FloorViewIndex(this.model);
    }

    public FloorViewIndex getView() {
        return this.view;
    }

}
