package nl.hanze.Windows;

import nl.hanze.controllers.FloorController;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

     public MainWindow(){
         JFrame frame = new JFrame();
         frame.setJMenuBar(new MyMenuBar());

         Container container = frame.getContentPane();

         FloorController menbersFloor = new FloorController(0,Color.blue);
         frame.add(menbersFloor.getView());

         FloorController regulareFloor = new FloorController(1,Color.green);
         frame.add(regulareFloor.getView());

         FloorController regulareFloor2 = new FloorController(1,Color.green);
         frame.add(regulareFloor2.getView());

         FloorController reververed = new FloorController(2,Color.yellow);
         frame.add(reververed.getView());

         GridLayout experimentLayout = new GridLayout(1, 3);
         frame.setLayout(experimentLayout);

         frame.setMinimumSize(new Dimension(1200,500));

         frame.pack();
         frame.setVisible(true);
    }

}
