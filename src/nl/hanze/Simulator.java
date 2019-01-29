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

import java.io.Serializable;
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


    private int timescale = 1;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;


    int weekDayArrivals = 50; // average number of arriving cars per hour was 100, 27%, piek + 67
    int weekendArrivals = 100; // average number of arriving cars per hour was 200, 55%, piek + 137
    int weekDayPassArrivals = 40; // average number of arriving cars per hour was 40, 10%, piek + 26
    int weekendPassArrivals = 30; // average number of arriving cars per hour was 30, 8%, piek + 20


    //Variabellen die de winst van de parkeergarage bijhouden
    double membersWinst = 0;
    double overigeWinst = 0;
    double reserverdWinst = 0;


    //deze variabelen staan los van de input variabellen hierboven en worden gebruikt om het origineel te behouden
    int orWeekdayArrival = weekDayArrivals;
    int orWeekendArrivals = weekendArrivals;
    int orWeekdayPassArrivals = weekDayPassArrivals;
    int orWeekendPassArrivals = weekendPassArrivals;

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
    int carsOutQenue = 0;
    int dubbelParkeren = 0;

    //running
    public static boolean isRunning = true;


    private MainWindow mainWindow;

    public Simulator(FloorController controller, MainWindow mainWindow) {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        entranceReserveredQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        this.floorController = controller;
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
                return "Sunday";
        }
        return "";
    }


    //todo voorbij rijden
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


    public void getReserved() {
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
        System.out.println("the Start");


        int i = 0;
        while (Simulator.isRunning && i < 10000) {
            i++;
            tick();
        }

        if (Simulator.isRunning == false) {
            this.stop();
        }

        System.out.println("the end");

    }

    public void stop() {
        this.isRunning = true;
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        entranceReserveredQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        this.hour = 0;
        this.minute = 0;
        this.day = 0;

        this.mainWindow.setVisible(false);

        this.floorController = MainWindow.getFloorController();
        this.mainWindow = new MainWindow();

        updateViews();
    }

    private void tick() {
        advanceTime();
        handleExit();
        getReserved();
        updateViews();
        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handleEntrance();
    }

    //functie om de drukte van de parkeergarage aan te passen aan de tijd.
    private void checkTime() {
        if (day == 5 || day == 4 || day == 3) {
            switch (this.hour) {
                case 17:
                    for (int i = 0; i < 68; i++) {
                        AdHocCar car = new AdHocCar();
                        entranceCarQueue.addCar(car);
                    }
                    for (int i = 0; i < 138; i++) {
                        ParkingPassCar car = new ParkingPassCar();
                        entrancePassQueue.addCar(car);
                    }
                case 23:
                    weekDayArrivals = orWeekdayPassArrivals;
                    weekendArrivals = orWeekendArrivals;
                    weekDayPassArrivals = orWeekdayPassArrivals;
                    weekendPassArrivals = orWeekendPassArrivals;
            }
        }

        if (day == 6) {
            switch (this.hour) {
                case 12:
                    for (int i = 0; i < 68; i++) {
                        AdHocCar csr = new AdHocCar();
                        entranceCarQueue.addCar(csr);

                    }
                    for (int i = 0; i < 138; i++) {
                        ParkingPassCar car = new ParkingPassCar();
                        entrancePassQueue.addCar(car);
                    }
                case 17:
                    weekDayArrivals = orWeekdayPassArrivals;
                    weekendArrivals = orWeekendArrivals;
                    weekDayPassArrivals = orWeekdayPassArrivals;
                    weekendPassArrivals = orWeekendPassArrivals;
            }
        }
    }

    private void advanceTime() {
        // Advance the time by one minute.
        minute += timescale;
//        checkTime();
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

    private void increaseSpeed() {
        timescale *= 2;
    }

    public void decreaseSpeed() {
        timescale /= 2;
    }

    private void handleEntrance() {
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);
        carsEntering(entranceReserveredQueue);
        queueCheck(entrancePassQueue);
        queueCheck(entranceCarQueue);
        queueCheck(entranceReserveredQueue);

        entranceCarQueue.tick();
        entrancePassQueue.tick();
        entranceReserveredQueue.tick();
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


        Main.label1.setText("Number of open spots: " + floorController.getNumberOfOpenSpots());
        Main.label2.setText("Member spots: " + floorController.getModel(FloorType.FLOOR_TYPE_MENBER.getValue()).getCurrentOpenSpots());
        Main.label3.setText("Total spots none: " + floorController.getModel(FloorType.FLOOR_TYPE_NONE.getValue()).getCurrentOpenSpots());
        Main.label4.setText("Total spots reservered: " + floorController.getModel(FloorType.FLOOR_TYPE_RESAVERED.getValue()).getCurrentOpenSpots());

        this.mainWindow.quene1.setText("Queue members:" + this.entrancePassQueue.carsInQueue());
        this.mainWindow.quene2.setText("Queue reservered:" + this.entranceReserveredQueue.carsInQueue());
        this.mainWindow.quene3.setText("Pass members:" + this.entranceCarQueue.carsInQueue());

        this.mainWindow.quene4.setText("Cars laved : " + this.carsOutQenue);


        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        this.mainWindow.statusBar.setMessage("Running... " + this.getDayString() + "  " + this.hour + ":" + this.minute);

    }

    private void carsArriving() {
        int numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, FloorType.FLOOR_TYPE_MENBER);

        numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
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
            int openMembers = openSpots = floorController.getModel(FloorType.FLOOR_TYPE_NONE.getValue()).getNumberOfOpenSpots();
            FloorModel extraMember = floorController.getModel(FloorType.FLOOR_TYPE_NONE.getValue());

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

                int deubelParkeren = (int) (Math.random() * 50 + 1);
                if (deubelParkeren == 1) {
                    freeLocation = model.getFirstFreeLocation();
                    Car clone = new AdHocCar();
                    clone.setDubelParkeren(true);
                    model.setCarAt(freeLocation, clone);
                    this.dubbelParkeren++;
                }


            }

            if (openMembers > 0 && openSpots <= 0 && car instanceof AdHocCar) {
                Location freeLocation = extraMember.getFirstFreeLocation();
                extraMember.setCarAt(freeLocation, car);
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
                    this.addCarToQunue(entranceCarQueue, new AdHocCar());
                }
                break;
            case FLOOR_TYPE_NONE:
                for (int i = 0; i < numberOfCars; i++) {
                    this.addCarToQunue(entranceCarQueue, new ParkingPassCar());
                }
                break;
            case FLOOR_TYPE_RESAVERED:
                for (int i = 0; i < numberOfCars; i++) {
                    this.addCarToQunue(entranceCarQueue, new ParkingReserveredCar());
                }
                break;
        }
    }

    private void carLeavesSpot(Car car) {
        Location carLOcation = car.getLocation();
        FloorModel model = this.floorController.getModel(carLOcation.getFloor());
        model.removeCarAt(carLOcation);
        if (car.isDubelParkeren()) {
            //eerst volgende car in het model.
            Car car2 = model.getNextCar();
            if (car2 != null) {
                model.removeCarAt(car2.getLocation());
                this.dubbelParkeren--;
            }
        }

        exitCarQueue.addCar(car);
    }

    private void addCarToQunue(CarQueue queue, Car car) {
        if (!queue.isFull()) {
            queue.addCar(car);
        }
    }

}
