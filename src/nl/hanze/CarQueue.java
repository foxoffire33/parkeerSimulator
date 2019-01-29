package nl.hanze;

import nl.hanze.cars.Car;

import java.util.*;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();
    private int maxCars = 10;

   public boolean addCar(Car car) {
       if (this.maxCars > this.carsInQueue()) {
            return queue.add(car);
        }
        System.out.println("Het is weer eens tien");
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

    public int removedCars() {
        Iterator<Car> carsIT = this.queue.iterator();
        int count = 0;

        while (carsIT.hasNext()) {
            Car car = carsIT.next();
            car.incurrmentQenueTime();
            if (car.hasToLaveQenue()) {
                carsIT.remove();
            } else {
                count++;
            }
        }

        this.queue = new LinkedList<>();
        carsIT.forEachRemaining(((LinkedList<Car>) this.queue)::add);

        return count;
    }

    public Car getFirstCar() {
        return this.queue.element();
    }
}
