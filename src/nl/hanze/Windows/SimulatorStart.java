package nl.hanze.Windows;


import nl.hanze.Simulator;

import javax.swing.*;
import java.awt.*;

public class SimulatorStart extends JFrame {

    JTextField textField, textField2, textField3, textField4, textField5, textField6;

    public SimulatorStart() {
        super();
        this.setBounds(100, 100, 739, 489);

        JLabel lblName = new JLabel("Abolament houders rijen");
        lblName.setBounds(65, 31, 600, 14);
        this.getContentPane().add(lblName);

        textField = new JTextField();
        textField.setBounds(300, 31, 25, 20);
        this.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblName2 = new JLabel("Regulere gebruikers rijen");
        lblName.setBounds(65, 51, 600, 14);
        this.getContentPane().add(lblName2);

        textField2 = new JTextField();
        textField2.setBounds(300, 51, 25, 20);
        this.getContentPane().add(textField2);
        textField2.setColumns(10);

        JLabel lblName3 = new JLabel("Online reservartions rijen");
        lblName3.setBounds(65, 71, 600, 14);
        this.getContentPane().add(lblName3);


        this.textField3 = new JTextField();
        this.textField3.setBounds(300, 71, 25, 20);
        this.getContentPane().add(this.textField3);
        this.textField3.setColumns(10);

        JLabel label4 = new JLabel("Members price");
        label4.setBounds(65, 91, 600, 14);
        this.add(label4);

        textField4 = new JTextField();
        textField4.setBounds(300, 91, 25, 20);
        this.getContentPane().add(textField4);
        textField4.setColumns(10);

        JLabel lblName5 = new JLabel("None Price");
        lblName5.setBounds(65, 111, 600, 14);
        this.getContentPane().add(lblName5);


        this.textField5 = new JTextField();
        this.textField5.setBounds(300, 111, 25, 20);
        this.getContentPane().add(this.textField5);
        this.textField5.setColumns(10);

        JLabel label6 = new JLabel("Reservatings price");
        label6.setBounds(65, 131, 600, 14);
        this.add(label6);

        this.textField6 = new JTextField();
        this.textField6.setBounds(300, 131, 25, 20);
        this.getContentPane().add(this.textField6);
        this.textField6.setColumns(10);


        JButton submit = new JButton("Start simulatie");
        submit.setBounds(65, 151, 260, 20);
        submit.addActionListener((e) -> changeSettings());
        this.getContentPane().add(submit);
        // submit.setColumns(10);


        this.setSize(new Dimension(400, 300));
        this.setTitle("Change settings");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        //   this.pack();
        this.setVisible(true);
    }

    private void changeSettings() {
        try {
            MainWindow.membersRows = Integer.parseInt(this.textField2.getText());
            MainWindow.noneRows = Integer.parseInt(this.textField.getText());
            MainWindow.reservationRows = Integer.parseInt(this.textField3.getText());

            Simulator.priceMember = Double.parseDouble(this.textField4.getText().replace(',', '.'));
            Simulator.PriceNone = Double.parseDouble(this.textField.getText().replace(',', '.'));
            Simulator.PriceReservation = Double.parseDouble(this.textField3.getText().replace(',', '.'));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "U kunt alleen Ints in vullen");
        }

        Simulator.isRunning = false;
    }


}
