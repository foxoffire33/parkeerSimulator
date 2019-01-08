package nl.hanze.controllers;


import nl.hanze.models.SimulatorModel;
import nl.hanze.views.sumulator.SimulatorView;

public class SimulatorController {

    private SimulatorView simulatorView;
    private SimulatorModel simulatorModel;

    public SimulatorController() {
        this.simulatorView = new SimulatorView(8, 6, 30);
        this.simulatorModel = new SimulatorModel();
    }

    public SimulatorView getSimulatorView() {
        return simulatorView;
    }

    public void setSimulatorView(SimulatorView simulatorView) {
        this.simulatorView = simulatorView;
    }
}
