package nl.hanze;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

     MainWindow(){

        Container container = this.getContentPane();

        JLabel label = new JLabel("Numbers of cars");
        container.add(label);

        this.pack();
        this.setVisible(true);
    }

}
