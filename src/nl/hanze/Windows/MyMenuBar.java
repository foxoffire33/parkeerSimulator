package nl.hanze.Windows;

import nl.hanze.Main;
import nl.hanze.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends JMenuBar {


    public MyMenuBar() {
        super();

        JMenu fileMenu = new JMenu("File");


        JMenuItem restartItem = new JMenuItem("Start Simulation");
        restartItem.addActionListener((e) -> {
           // Main.Simulatie();
           // new SimulatorStart();
            Main.simulator.stop();
        //    Main.simulator.run();
        });
        fileMenu.add(restartItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener((e) -> System.exit(0));
        fileMenu.add(quitItem);



        this.add(fileMenu);

        JMenu helpItem = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About ImageViewer");
        helpItem.add(aboutItem);

        this.add(helpItem);
    }
}
