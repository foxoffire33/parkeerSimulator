package nl.hanze;

import nl.hanze.Windows.MainWindow;
import nl.hanze.controllers.FloorController;
import nl.hanze.enums.FloorEnum;

import java.awt.*;


public class Main {

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();

        //Maak een controller
        FloorController floorController = new FloorController();
        //Maak models per floor aan en zet deze in de ArrayList in de controller
        floorController.addModel(FloorEnum.FLOOR_TYPE_MENBER,0);
        floorController.addModel(FloorEnum.FLOOR_TYPE_NONE,1);
        floorController.addModel(FloorEnum.FLOOR_TYPE_NONE,2);
        floorController.addModel(FloorEnum.FLOOR_TYPE_RESAVERED,3);

        mainWindow.add(floorController.getView(0, Color.blue));
        mainWindow.add(floorController.getView(1, Color.green));
        mainWindow.add(floorController.getView(2, Color.green));
        mainWindow.add(floorController.getView(3, Color.yellow));

        //views toevoegen aan MainWindow

        GridLayout experimentLayout = new GridLayout(1, 4);
        mainWindow.setLayout(experimentLayout);


        mainWindow.setMinimumSize(new Dimension(1200, 500));

        mainWindow.pack();
        mainWindow.setVisible(true);

        Simulator simulator = new Simulator(floorController,mainWindow);
       simulator.run();
    }
}