package nl.hanze.Windows;

import nl.hanze.Simulator;
import nl.hanze.controllers.FloorController;
import nl.hanze.enums.FloorEnum;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        JFrame frame = new JFrame();
        frame.setJMenuBar(new MyMenuBar());

        Container container = frame.getContentPane();

        //Maak een controller
        FloorController florrController = new FloorController();
        //Maak models per floor aan en zet deze in de ArrayList in de controller
        florrController.addModel(FloorEnum.FLOOR_TYPE_MENBER);
        florrController.addModel(FloorEnum.FLOOR_TYPE_NONE);
        florrController.addModel(FloorEnum.FLOOR_TYPE_NONE);
        florrController.addModel(FloorEnum.FLOOR_TYPE_RESAVERED);

        //views toevoegen aan MainWindow
        frame.add(florrController.getView(0, Color.blue));
        frame.add(florrController.getView(1, Color.green));
        frame.add(florrController.getView(2, Color.green));
        frame.add(florrController.getView(3, Color.yellow));

        GridLayout experimentLayout = new GridLayout(1, 4);
        frame.setLayout(experimentLayout);

        frame.setMinimumSize(new Dimension(1200, 500));

        frame.pack();
        frame.setVisible(true);
    }

}
