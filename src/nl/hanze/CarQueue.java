package nl.hanze;

import nl.hanze.cars.Car;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();
    private int maxCars = 10;

    public boolean addCar(Car car) {
        if (this.maxCars > this.carsInQueue()) {
            return queue.add(car);
        }
        return false;
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue() {
        return queue.size();
    }

    public boolean isFull() {
        return this.maxCars == this.carsInQueue();
    }

    public void removeCars() {
        Iterator<Car> carsIT = this.queue.iterator();

        LinkedList<Car> queue = new LinkedList<>();

        while (carsIT.hasNext()) {
            Car car = carsIT.next();
            car.incurrmentQenueTime();
            if (car.hasToLaveQenue()) {
                this.removeCar();
            }
            {
                this.queue.add(car);
            }
        }
        this.queue = queue;

    }

    public Car getFirstCar() {
        return this.queue.element();
    }
}
