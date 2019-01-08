package nl.hanze;

import nl.hanze.controllers.SimulatorController;

public class Main {

    public static void main(String[] args) {

        SimulatorController simulatorController = new SimulatorController();

        Simulator simulator = new Simulator();
        simulator.run();


    }
}