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
    public JLabel quene4 = new JLabel("Totaal leaved");

    public static int membersRows = 4;
    public static int noneRows = 6;
    public static int reservationRows = 4;

    public StatusBar statusBar;
    private static FloorController floorController;

    public MainWindow() {
        this.setJMenuBar(new MyMenuBar());

        Container container = this.getContentPane();
        container = new Container();

        floorController = getFloorController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panel.add(floorController.getView(FloorType.FLOOR_TYPE_MENBER),BorderLayout.WEST);
        panel.add(floorController.getView(FloorType.FLOOR_TYPE_NONE),BorderLayout.CENTER);
        panel.add(floorController.getView(FloorType.FLOOR_TYPE_RESAVERED),BorderLayout.EAST);


        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
        Main.label1 = new JLabel("Totaal spots: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label1);

        Main.label2 = new JLabel("Member spots: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label2);

        Main.label3 = new JLabel("Totaal spots none: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label3);

        Main.label4 = new JLabel("Totaal spots reservered: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label4);

        Main.label5 = new JLabel("Mem lived: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label1);

        Main.label6 = new JLabel("None lived: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label2);

        Main.label7 = new JLabel("Reservations none: " + floorController.getNumberOfOpenSpots());
        informationPanel.add(Main.label3);

        Main.label8 = new JLabel();

        informationPanel.add(this.quene1);
        informationPanel.add(this.quene2);
        informationPanel.add(this.quene3);
        informationPanel.add(this.quene4);
        informationPanel.add(Main.label5);
        informationPanel.add(Main.label6);
        informationPanel.add(Main.label7);

        informationPanel.setMaximumSize(new Dimension(100,100));

        scrollPane.getViewport().addChangeListener((e) -> { this.repaint(); } );

        container.add(scrollPane,BorderLayout.EAST);
        container.add(informationPanel,BorderLayout.WEST);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        scrollPane.setMinimumSize(new Dimension(600,500));
        p.add(scrollPane);
        p.add(informationPanel);
        this.add(p);


        setSize(800, 600);



        this.statusBar = new StatusBar();
        this.getContentPane().add(this.statusBar, java.awt.BorderLayout.SOUTH);
//
//        this.setSize(800,600);
//        scrollPane.setSize(400,300);

        this.setVisible(true);

        Simulator simulator = new Simulator(floorController, this);
        simulator.run();

    }

    public static Simulator startSimulator(MainWindow frame) {
        Simulator simulator = new Simulator(MainWindow.getFloorController(), frame);
        return simulator;
    }


    public static FloorController getFloorController() {
        //Maak een controller
        floorController = new FloorController();
        //Maak models per floor aan en zet deze in de ArrayList in de controller
        floorController.addModel(FloorType.FLOOR_TYPE_MENBER, MainWindow.membersRows);
        floorController.addModel(FloorType.FLOOR_TYPE_NONE, MainWindow.noneRows);
        floorController.addModel(FloorType.FLOOR_TYPE_RESAVERED, MainWindow.reservationRows);
        return floorController;
    }

}