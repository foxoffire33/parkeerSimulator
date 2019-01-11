package nl.hanze.Windows;

import nl.hanze.Simulator;
import nl.hanze.controllers.FloorController;
import nl.hanze.enums.FloorEnum;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        this.setJMenuBar(new MyMenuBar());

        Container container = this.getContentPane();

        //Maak een controller
        FloorController floorController = new FloorController();
        //Maak models per floor aan en zet deze in de ArrayList in de controller
        floorController.addModel(FloorEnum.FLOOR_TYPE_MENBER,0);
        floorController.addModel(FloorEnum.FLOOR_TYPE_NONE,1);
        floorController.addModel(FloorEnum.FLOOR_TYPE_NONE,2);
        floorController.addModel(FloorEnum.FLOOR_TYPE_RESAVERED,3);

        this.add(floorController.getView(0, Color.blue));
        this.add(floorController.getView(1, Color.green));
        this.add(floorController.getView(2, Color.green));
        this.add(floorController.getView(3, Color.yellow));

        //views toevoegen aan MainWindow

        GridLayout experimentLayout = new GridLayout(1, 4);
        this.setLayout(experimentLayout);

        this.setMinimumSize(new Dimension(1200, 500));

        this.pack();
        this.setVisible(true);

        Simulator simulator = new Simulator(floorController);
        simulator.run();

    }

}
