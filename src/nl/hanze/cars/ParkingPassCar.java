package nl.hanze.cars;

import java.util.Random;
import java.awt.*;

//dit is de auto voor niet members;

public class
ParkingPassCar extends Car {
    public static double price = 1;
	private static final Color COLOR=Color.blue;
	
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    public Color getColor(){
    	return COLOR;
    }

    public static double getPrice() {
        return price;
    }

    public static void setPrice(double price) {
        ParkingPassCar.price = price;
    }
}
