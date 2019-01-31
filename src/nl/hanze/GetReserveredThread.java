package nl.hanze;

import nl.hanze.cars.ParkingReserveredCar;
import nl.hanze.database.MysqlConnection;

import java.util.ArrayList;

public class GetReserveredThread implements Runnable {

    private CarQueue carQueue = new CarQueue();

    public void run() {
        System.out.println("start mysql thread");
        MysqlConnection mysql = new MysqlConnection();
        if (mysql.isDbConnected()) {
            ArrayList<ParkingReserveredCar> ir = mysql.reservations(Simulator.hour);
            if (!ir.isEmpty()) {
                for (ParkingReserveredCar model : ir) {
                    this.carQueue.addCar(model);
                }
            }
        }
    }

    public CarQueue getCarQueue() {
        return this.carQueue;
    }

}
