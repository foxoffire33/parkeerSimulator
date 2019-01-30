package nl.hanze.Windows;


import nl.hanze.Simulator;
import nl.hanze.cars.AdHocCar;
import nl.hanze.cars.ParkingPassCar;
import nl.hanze.cars.ParkingReserveredCar;

import javax.swing.*;
import java.awt.*;

public class SimulatorStart extends JFrame {

    JTextField textField, textField2, textField3, textField4, textField5, textField6;

    public SimulatorStart() {
        super();
        this.setBounds(100, 100, 739, 489);

        JLabel lblName = new JLabel("Member rows");
        lblName.setBounds(65, 31, 600, 14);
        this.getContentPane().add(lblName);

        textField = new JTextField("" + MainWindow.membersRows);
        textField.setBounds(300, 31, 35, 20);
        this.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblName2 = new JLabel("Regular rows");
        lblName2.setBounds(65, 51, 600, 14);
        this.getContentPane().add(lblName2);

        textField2 = new JTextField("" + MainWindow.noneRows);
        textField2.setBounds(300, 51, 35, 20);
        this.getContentPane().add(textField2);
        textField2.setColumns(10);

        JLabel lblName3 = new JLabel("Online reservation rows");
        lblName3.setBounds(65, 71, 600, 14);
        this.getContentPane().add(lblName3);


        this.textField3 = new JTextField("" + MainWindow.reservationRows);
        this.textField3.setBounds(300, 71, 35, 20);
        this.getContentPane().add(this.textField3);
        this.textField3.setColumns(10);

        JLabel label4 = new JLabel("Members price");
        label4.setBounds(65, 91, 600, 14);
        this.add(label4);

        textField4 = new JTextField("" + ParkingPassCar.getPrice());
        textField4.setBounds(300, 91, 35, 20);
        this.getContentPane().add(textField4);
        textField4.setColumns(10);

        JLabel lblName5 = new JLabel("Regular Price");
        lblName5.setBounds(65, 111, 600, 14);
        this.getContentPane().add(lblName5);


        this.textField5 = new JTextField("" + AdHocCar.getPrice());
        this.textField5.setBounds(300, 111, 35, 20);
        this.getContentPane().add(this.textField5);
        this.textField5.setColumns(10);

        JLabel label6 = new JLabel("Reserveers price");
        label6.setBounds(65, 131, 600, 14);
        this.add(label6);

        this.textField6 = new JTextField("" + ParkingReserveredCar.getPrice());
        this.textField6.setBounds(300, 131, 35, 20);
        this.getContentPane().add(this.textField6);
        this.textField6.setColumns(10);


        JButton submit = new JButton("Start simulation");
        submit.setBounds(65, 151, 260, 20);
        submit.addActionListener((e) -> changeSettings());
        this.getContentPane().add(submit);


        this.setSize(new Dimension(400, 300));
        this.setTitle("Change settings");

        this.getContentPane().setLayout(null);

        //   this.pack();
        this.setVisible(true);
    }

    private void changeSettings() {
        try {
            MainWindow.membersRows = Integer.parseInt(this.textField.getText());
            MainWindow.noneRows = Integer.parseInt(this.textField2.getText());
            MainWindow.reservationRows = Integer.parseInt(this.textField3.getText());

            ParkingPassCar.setPrice(Double.parseDouble(this.textField4.getText().replace(',', '.')));
            AdHocCar.setPrice(Double.parseDouble(this.textField.getText().replace(',', '.')));
            ParkingReserveredCar.setPrice(Double.parseDouble(this.textField3.getText().replace(',', '.')));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "The form hasn't been filled in correctly!", "Error" , 0);
            return;
        }

        Simulator.isRunning = false;
    }


}
