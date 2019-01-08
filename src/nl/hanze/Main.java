package nl.hanze;

import nl.hanze.controllers.FloorController;
import nl.hanze.models.FloorModel;
import nl.hanze.views.sumulator.floor.FloorViewIndex;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        Container container = frame.getContentPane();

        FloorController menbersFloor = new FloorController(0);
        FloorController noneController = new FloorController(1);
        FloorController registeredFloor = new FloorController(2);

        container.add(menbersFloor.getView());
        container.add(noneController.getView());


        frame.pack();
        frame.setVisible(true);

//        Simulator simulator = new Simulator();2
//        simulator.run();


    }
}