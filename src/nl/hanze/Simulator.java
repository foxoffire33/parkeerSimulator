package nl.hanze;

import nl.hanze.Windows.MainWindow;
import nl.hanze.cars.AdHocCar;
import nl.hanze.cars.Car;
import nl.hanze.cars.ParkingPassCar;
import nl.hanze.cars.ParkingReserveredCar;
import nl.hanze.database.MysqlConnection;
import nl.hanze.enums.FloorType;
import nl.hanze.models.FloorModel;
import nl.hanze.controllers.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Simulator implements Runnable {

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

    int weekDayArrivals = 200; // average number of arriving cars per hour
    int weekendArrivals = 400; // average number of arriving cars per hour
    int weekDayPassArrivals = 80; // average number of arriving cars per hour
    int weekendPassArrivals = 60; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
    int carsOutQenue = 0;

    double priceMember = 30;
    double PriveNone = 1;
    //variable maken
    double PriceReservation = 0;

    //running
    private boolean isRunning = true;


    public double getLoss() {
        return this.carsOutQenue * this.PriveNone;
    }


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

    private String getDayString() {
        switch (this.day) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Saturday";
        }
        return "";
    }


    //todo voorbei rijden
    private void queueCheck(CarQueue queue) {
        if (queue.carsInQueue() > 10) {
            int rendomNumber = getRandomNumberInRange(3, 7);
            for (int i = 0; i < rendomNumber; i++) {
                this.carsOutQenue++;
            }
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void extraGuests() {
        if (this.day >= 4 && this.hour == 18) {
            //cars toevoegen
        }
        if (this.day == 3 && this.hour == 18) {
            //500 extra autos
        }
    }

    public void getReswervered() {
        if (this.minute == 1) {
            MysqlConnection mysql = new MysqlConnection();
            if (mysql.isDbConnected()) {
                ArrayList<ParkingReserveredCar> ir = mysql.reservations(this.hour);
                if (!ir.isEmpty()) {
                    for (ParkingReserveredCar model : ir) {
                        this.entranceReserveredQueue.addCar(model);
                    }
                }
            }
        }
    }

    public void run() {
        int i = 0;
        while (this.isRunning && i < 10000) {
            i++;
            tick();
        }
    }

    public void stop() {
        this.isRunning = false;
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        entranceReserveredQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        this.hour = 0;
        this.minute = 0;
        this.day = 0;

        this.floorController = MainWindow.getFloorController();

        updateViews();
        Main.mainWindow.repaint();
    }

    private void tick() {
        advanceTime();
        handleExit();
        getReswervered();
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
        queueCheck(entrancePassQueue);
        queueCheck(entranceCarQueue);
        queueCheck(entranceReserveredQueue);
    }

    private void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    private void updateViews() {
        this.floorController.tick();
        this.mainWindow.repaint();
        this.mainWindow.revalidate();


        Main.lebel1.setText("Number of openspots: " + floorController.getNumberOfOpenSpots());
        Main.label2.setText("Member spots: " + floorController.getModel(FloorType.FLOOR_TYPE_MENBER.getValue()).getCurrentOpenSpots());
        Main.label3.setText("Totaal spots none: " + floorController.getModel(FloorType.FLOOR_TYPE_NONE.getValue()).getCurrentOpenSpots());
        Main.label4.setText("Totaal spots reservered: " + floorController.getModel(FloorType.FLOOR_TYPE_RESAVERED.getValue()).getCurrentOpenSpots());

        this.mainWindow.quene1.setText("Quene members:" + this.entrancePassQueue.carsInQueue());
        this.mainWindow.quene2.setText("Quene reservered:" + this.entranceReserveredQueue.carsInQueue());
        this.mainWindow.quene3.setText("Pass members:" + this.entranceCarQueue.carsInQueue());

        this.mainWindow.quene4.setText("Cars laved : " + this.carsOutQenue);


        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        this.mainWindow.statusBar.setMessage("Running... " + this.getDayString() + "  " + this.hour + ":" + this.minute);

    }

    private void carsArriving() {
        int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, FloorType.FLOOR_TYPE_MENBER);

        numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, FloorType.FLOOR_TYPE_NONE);

        //  numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        //addArrivingCars(numberOfCars, FloorType.FLOOR_TYPE_RESAVERED);
    }

    private void carsEntering(CarQueue queue) {
        int i = 0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue() > 0 && i < enterSpeed) {

            Car car = queue.removeCar();

            int openSpots = 0;

            FloorModel model;
            if (car instanceof AdHocCar) {
                model = floorController.getModel(FloorType.FLOOR_TYPE_MENBER.getValue());
                openSpots = floorController.getModel(FloorType.FLOOR_TYPE_MENBER.getValue()).getNumberOfOpenSpots();
            } else if (car instanceof ParkingPassCar) {
                model = floorController.getModel(FloorType.FLOOR_TYPE_NONE.getValue());
                openSpots = floorController.getModel(FloorType.FLOOR_TYPE_NONE.getValue()).getNumberOfOpenSpots();
            } else {
                model = floorController.getModel(FloorType.FLOOR_TYPE_RESAVERED.getValue());
                openSpots = floorController.getModel(FloorType.FLOOR_TYPE_RESAVERED.getValue()).getNumberOfOpenSpots();
            }


            if (openSpots > 0) {
                Location freeLocation = model.getFirstFreeLocation();
                model.setCarAt(freeLocation, car);
            }
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
        int averageNumberOfCarsPerHour = day < 5 ? weekDay : weekend;

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
