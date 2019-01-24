package nl.hanze;

import nl.hanze.Windows.MainWindow;
import nl.hanze.Windows.StatusBar;
import nl.hanze.cars.ParkingReserveredCar;
import nl.hanze.controllers.FloorController;
import nl.hanze.database.MysqlConnection;
import nl.hanze.enums.FloorType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Main {

    public static JLabel label1, label2, label3, label4;
    public static MainWindow mainWindow;

    public static void main(String[] args) {
        Main.mainWindow = new MainWindow();
    }

}