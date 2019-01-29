package nl.hanze.cars;

import nl.hanze.Location;

import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    private boolean dubelParkeren;
    private int qneueTime = 20;
    private int countQuneTime = 1;

    /**
     * Constructor for objects of class Car
     */
    public Car() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public boolean isDubelParkeren() {
        return dubelParkeren;
    }

    public void setDubelParkeren(boolean dubelParkeren) {
        this.dubelParkeren = dubelParkeren;
    }

    public void tick() {
        minutesLeft--;
    }

    public abstract Color getColor();

    public int getQneueTime() {
        return qneueTime;
    }

    public void incurrmentQenueTime() {
        this.countQuneTime++;
    }

    public boolean hasToLaveQenue(){
        return this.qneueTime == this.countQuneTime;
    }
}