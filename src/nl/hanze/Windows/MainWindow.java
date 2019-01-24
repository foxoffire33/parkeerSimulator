package nl.hanze.Windows;

import nl.hanze.Main;
import nl.hanze.Simulator;
import nl.hanze.controllers.FloorController;
import nl.hanze.enums.FloorType;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public JLabel quene1 = new JLabel();
    public JLabel quene2 = new JLabel();
    public JLabel quene3 = new JLabel();
    public JLabel quene4 = new JLabel("Totaal laved");

    public StatusBar statusBar;
    private static FloorController floorController;

    public MainWindow() {
        this.setJMenuBar(new MyMenuBar());

        Container container = this.getContentPane();

        floorController = getFloorController();

        JPanel panel = new JPanel();

        panel.add(floorController.getView(FloorType.FLOOR_TYPE_MENBER));
        panel.add(floorController.getView(FloorType.FLOOR_TYPE_NONE));
        panel.add(floorController.getView(FloorType.FLOOR_TYPE_RESAVERED));


        JPanel informationPanel = new JPanel();
        Main.label1 = new JLabel("Totaal spots: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label1);

        Main.label2 = new JLabel("Member spots: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label2);

        Main.label3 = new JLabel("Totaal spots none: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label3);

        Main.label4 = new JLabel("Totaal spots reservered: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label4);

        informationPanel.add(this.quene1);
        informationPanel.add(this.quene2);
        informationPanel.add(this.quene3);
        informationPanel.add(this.quene4);

        informationPanel.setLayout(new FlowLayout());

        panel.add(informationPanel);

        //views toevoegen aan MainWindow

        GridLayout experimentLayout = new GridLayout(0, 4);
        panel.setLayout(experimentLayout);


        this.setMinimumSize(new Dimension(1200, 500));

        this.add(panel, BorderLayout.CENTER);

        this.statusBar = new StatusBar();
        this.getContentPane().add(this.statusBar, java.awt.BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);

        //    Simulator simulator = new Simulator(floorController, this);
        //  simulator.run();

        startSimulator(this);

    }

    public static Simulator startSimulator(MainWindow frame) {
        Simulator simulator = new Simulator(MainWindow.floorController, frame);
        return simulator;
    }

    public static FloorController getFloorController() {
        //Maak een controller
        floorController = new FloorController();
        //Maak models per floor aan en zet deze in de ArrayList in de controller
        floorController.addModel(FloorType.FLOOR_TYPE_MENBER, 4);
        floorController.addModel(FloorType.FLOOR_TYPE_NONE, 8);
        floorController.addModel(FloorType.FLOOR_TYPE_RESAVERED, 2);
        return floorController;
    }

}