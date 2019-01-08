package nl.hanze;

import nl.hanze.controllers.FloorController;
import nl.hanze.models.FloorModel;
import nl.hanze.views.sumulator.floor.FloorViewIndex;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        //SimulatorController simulatorController = new SimulatorController();
//        JFrame frame = new JFrame();
//
//        Container container = frame.getContentPane();
//
//        FloorController controller = new FloorController();
//
//        container.add(controller.getView());
//
//        frame.pack();
//        frame.setVisible(true);

        Simulator simulator = new Simulator();
        simulator.run();


    }
}