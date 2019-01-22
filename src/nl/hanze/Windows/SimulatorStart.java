package nl.hanze.Windows;


import javax.swing.*;
import java.awt.*;

public class SimulatorStart extends JFrame {

    public SimulatorStart(){
        super();
        this.setBounds(100,100,739,489);

        JTextField textField = new JTextField();
        textField.setBounds(180, 28, 25, 20);
        this.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblName = new JLabel("weekDayArrivals");
        lblName.setBounds(65, 31, 600, 14);
        this.getContentPane().add(lblName);

        JTextField textField2 = new JTextField();
        textField2.setBounds(180, 58, 25, 20);
        this.getContentPane().add(textField2);
        textField2.setColumns(10);

        JLabel lblName2 = new JLabel("weekendArrivals");
        lblName2.setBounds(65, 61, 600, 14);
        this.getContentPane().add(lblName2);

        JButton startSimulatie = new JButton("Strt simulatie");
        startSimulatie.addActionListener((e) -> {
            this.removeAll();
            //new MainWindow();
        });
        startSimulatie.setBounds(65, 61, 600, 14);
        this.add(startSimulatie);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.pack();
        this.setVisible(true);
    }



}
