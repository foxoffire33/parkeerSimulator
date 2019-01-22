package nl.hanze.database;

import nl.hanze.cars.ParkingPassCar;
import nl.hanze.cars.ParkingReserveredCar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;

public class MysqlConnection {

    private Connection con;
    private ArrayList<Integer> ids = new ArrayList<>();

    public MysqlConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java", "root", null);
        } catch (
                Exception e) {
            System.out.println(e);
        }
    }

    public boolean isDbConnected() {
        try {

            if (this.con != null && !this.con.isClosed()) {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }

    public ArrayList<ParkingReserveredCar> reservations(int hours) {
        ArrayList<ParkingReserveredCar> reservation = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *,round(TIME_TO_SEC(TIMEDIFF(to_time,from_time)) / 60) as 'minutesStay' FROM `reservations` where " + hours + " = hour(from_time)");
            // ResultSet rs = stmt.executeQuery("SELECT *,round(TIME_TO_SEC(TIMEDIFF(to_time,from_time)) / 60) as 'minutesStay' FROM `reservations`");
            while (rs.next()) {
                ParkingReserveredCar parkingReserveredCar = new ParkingReserveredCar();
                parkingReserveredCar.setMinutesLeft(rs.getInt("minutesStay"));
                reservation.add(parkingReserveredCar);
            }
            this.con.close();
        } catch (
                Exception e) {
            System.out.println(e);
        }

        return reservation;
    }
}
