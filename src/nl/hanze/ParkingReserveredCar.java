package nl.hanze;

import java.awt.*;
import java.util.Random;

public class ParkingReserveredCar extends Car {
	private static final Color COLOR=Color.YELLOW;

    public ParkingReserveredCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}