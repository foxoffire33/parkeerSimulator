package nl.hanze;

import nl.hanze.Windows.MainWindow;
import nl.hanze.enums.FloorType;
import nl.hanze.models.FloorModel;
import nl.hanze.controllers.*;

import javax.swing.*;
import java.util.Random;

public class Simulator {

    private static final int AD_HOC = 0;
    private static final int PASS = 1;
    private static final int RESERVERED = 2;


    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue entranceReserveredQueue;
    private CarQueue exitCarQueue;
    private FloorController floorController;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals = 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals = 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    private MainWindow mainWindow;

    public Simulator(FloorController floorController, MainWindow mainWindow) {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        entranceReserveredQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        this.floorController = floorController;
        this.mainWindow = mainWindow;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    private void tick() {
        advanceTime();
        handleExit();
        updateViews();
        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handleEntrance();
    }

    private void advanceTime() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }

    private void handleEntrance() {
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);
        carsEntering(entranceReserveredQueue);
    }

    private void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    private void updateViews() {
        this.floorController.tick();
        this.mainWindow.repaint();
    }

    private void carsArriving() {
        int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, FloorType.FLOOR_TYPE_MENBER);

        numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, FloorType.FLOOR_TYPE_NONE);

        numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, FloorType.FLOOR_TYPE_RESAVERED);
    }

    private void carsEntering(CarQueue queue) {
        int i = 0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue() > 0 &&
                floorController.getNumberOfOpenSpots() > 0 &&
                i < enterSpeed) {

            Car car = queue.removeCar();

            FloorModel model;
            if (car instanceof AdHocCar) {
                model = floorController.getModel(FloorType.FLOOR_TYPE_MENBER.getValue());
            } else if (car instanceof ParkingPassCar) {
                model = floorController.getModel(FloorType.FLOOR_TYPE_NONE.getValue());
            } else {
                model = floorController.getModel(FloorType.FLOOR_TYPE_RESAVERED.getValue());
            }

            Location freeLocation = model.getFirstFreeLocation();
            model.setCarAt(freeLocation, car);
            i++;
        }
    }

    private void carsReadyToLeave() {
//        // Add leaving cars to the payment queue.
        Car car = floorController.getFirstLeavingCar();
        while (car != null) {
            if (car.getHasToPay()) {
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            } else {
                carLeavesSpot(car);
            }
            car = floorController.getFirstLeavingCar();
        }
    }

    private void carsPaying() {
        // Let cars pay.
        int i = 0;
        while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed) {
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
        }
    }

    private void carsLeaving() {
        // Let cars leave.
        int i = 0;
        while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
            exitCarQueue.removeCar();
            i++;
        }
    }

    private int getNumberOfCars(int weekDay, int weekend) {
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int) Math.round(numberOfCarsPerHour / 60);
    }

    private void addArrivingCars(int numberOfCars, FloorType type) {
        // Add the cars to the back of the queue.
        switch (type) {
            case FLOOR_TYPE_MENBER:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar());
                }
                break;
            case FLOOR_TYPE_NONE:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                }
                break;
            case FLOOR_TYPE_RESAVERED:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceReserveredQueue.addCar(new ParkingReserveredCar());
                }
                break;
        }
    }

    private void carLeavesSpot(Car car) {
        Location carLOcation = car.getLocation();
        FloorModel model = this.floorController.getModel(carLOcation.getFloor());
        model.removeCarAt(carLOcation);

        exitCarQueue.addCar(car);
    }

}
