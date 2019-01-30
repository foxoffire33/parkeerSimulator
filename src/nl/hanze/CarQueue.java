package nl.hanze;

import nl.hanze.cars.AdHocCar;
import nl.hanze.cars.Car;
import nl.hanze.cars.ParkingPassCar;
import nl.hanze.cars.ParkingReserveredCar;

import java.util.*;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();
    private int maxCars = 10;

   public boolean addCar(Car car) {
       if (this.maxCars > this.carsInQueue()) {
            return queue.add(car);
        }
        System.out.println("Het is weer eens tien");
       if (car instanceof AdHocCar){Simulator.verlorenqueuewinstMembers += AdHocCar.getPrice();}
       if (car instanceof ParkingPassCar){Simulator.verlorenqueuewinstOverig += ParkingPassCar.getPrice();}
       if (car instanceof ParkingReserveredCar){Simulator.verlorenqueuewinstReserved += ParkingReserveredCar.getPrice();}
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
