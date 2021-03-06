package nl.hanze.cars;

import java.util.Random;
import java.awt.*;

//dit is de auto voor members

public class AdHocCar extends Car {
    public static double price = 30;
    private static final Color COLOR = Color.red;

    public AdHocCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public static double getPrice() {
        return price;
    }

    public static void setPrice(double price) {
        AdHocCar.price = price;
    }

    public Color getColor() {
        return COLOR;
    }
}
