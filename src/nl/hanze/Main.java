package nl.hanze;

import nl.hanze.Windows.MainWindow;
import nl.hanze.controllers.FloorController;
import nl.hanze.enums.FloorType;

import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();

        //Maak een controller
        FloorController floorController = new FloorController();
        //Maak models per floor aan en zet deze in de ArrayList in de controller
        floorController.addModel(FloorType.FLOOR_TYPE_MENBER,6);
        floorController.addModel(FloorType.FLOOR_TYPE_NONE,10);

        floorController.addModel(FloorType.FLOOR_TYPE_RESAVERED,6);

        mainWindow.add(floorController.getView(FloorType.FLOOR_TYPE_MENBER, Color.blue));
        mainWindow.add(floorController.getView(FloorType.FLOOR_TYPE_NONE, Color.green));
        mainWindow.add(floorController.getView(FloorType.FLOOR_TYPE_RESAVERED, Color.yellow));

        //views toevoegen aan MainWindow

        GridLayout experimentLayout = new GridLayout(0, 3);
        mainWindow.setLayout(experimentLayout);


        mainWindow.setMinimumSize(new Dimension(1200, 500));

        mainWindow.pack();
        mainWindow.setVisible(true);

        Simulator simulator = new Simulator(floorController,mainWindow);
       simulator.run();
    }
}