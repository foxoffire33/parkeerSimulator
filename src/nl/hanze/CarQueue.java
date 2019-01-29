package nl.hanze;

import nl.hanze.cars.Car;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();
    private int maxCars = 10;
    private int passedCars = 0;

    private int minutesToStay = 20;
    private int countTicks = 1;

    public boolean addCar(Car car) {
        if (this.maxCars > this.carsInQueue()) {
            return queue.add(car);
        }
        return false;
    }

    public void tick() {
        if (this.minutesToStay == this.countTicks) {
            this.removeCar();
            countTicks = 1;
        }
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue() {
        return queue.size();
    }

    public int getMaxCars() {
        return maxCars;
    }

    public void setMaxCars(int maxCars) {
        this.maxCars = maxCars;
    }


    public void inCurremntPasedCars() {
        this.passedCars++;
    }

    public int getPassedCars() {
        return this.passedCars;
    }

    public boolean isFull() {
        return this.maxCars == this.carsInQueue();
    }
}
