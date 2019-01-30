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

import javax.swing.*;
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


    int weekDayArrivals = 150; // average number of arriving cars per hour was 100, 27%, piek + 67
    int weekendArrivals = 300; // average number of arriving cars per hour was 200, 55%, piek + 137
    int weekDayPassArrivals = 40; // average number of arriving cars per hour was 40, 10%, piek + 26
    int weekendPassArrivals = 30; // average number of arriving cars per hour was 30, 8%, piek + 20


    //Variabellen die de (verloren) winst van de parkeergarage bijhouden
    private double membersWinst = 0; //Winst verkregen van gepaarkeerde members
    private double overigeWinst = 0; // Winst verkregen van overige parkeerders.
    private double reserverdWinst = 0; //Winst verkregen van reserveerders
    private double totaleWinst = 0; //totale winst van de 3 bovenstaande groepen.

    public static double verlorenqueuewinsttotaal = 0; //Verloren winst van ALLE mensen die uit de queue gaan
    public static double verlorenqueuewinstMembers = 0; //Verloren winst van members die uit de queue gaan
    public static double verlorenqueuewinstReserved = 0; //Verloren winst van alle reserveerders die uit de queue gaan
    public static double verlorenqueuewinstOverig = 0; //Verloren winst van alle overige parkeerders die uit de queue gaan

    private double verlorenwinstdubbel; //verloren winst door dubbelparkeerders (groepen meegereken maar niet getecorized)

    private double verlorenwinsttotaal; //De totale verloren winst (queue verlaters + dubbelparkeerders)

    //
    private int membersLevingQenue = 0;
    private int noneLevingQenue = 0;
    private int reserverdLevingQenue = 0;


    //deze variabelen staan los van de input variabellen hierboven en worden gebruikt om het origineel te behouden
    private int orWeekdayArrivals = weekDayArrivals;
    private int orWeekendArrivals = weekendArrivals;
    private int orWeekdayPassArrivals = weekDayPassArrivals;
    private int orWeekendPassArrivals = weekendPassArrivals;
    private boolean offtime = false;
    private boolean busyDay = false;

    private int enterSpeed = 3; // number of cars that can enter per minute
    private int paymentSpeed = 7; // number of cars that can pay per minute
    private int carsOutQenue = 0;
    private int exitSpeed = 5;

    private int dubbelParkOverige = 0; //houd het huidige aantal dubbelparkeerders van overige mensen bij
    private int dubbelParkMember = 0; //houd het huidge aantal dubbelpaarders bij van members
    private int dubbelParkReserved = 0; //houd het huidig aantal dubbelpaarkeerders bij van reserved;
    private int dubbelParkTotaal = 0; //houd het huidig aantal dubbelparkeerders bij

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
        checkTime();
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
        busyDay = false;

        //hieronder een paar variabellen bij houden
        verlorenqueuewinsttotaal = verlorenqueuewinstMembers + verlorenqueuewinstReserved + verlorenqueuewinstOverig;
        verlorenwinsttotaal = verlorenqueuewinsttotaal + verlorenwinstdubbel;

        if (this.hour == 1) {

            weekDayArrivals = orWeekdayArrivals / 2;
            weekendArrivals = orWeekendArrivals / 2;
            weekDayPassArrivals = orWeekdayPassArrivals / 2;
            weekendPassArrivals = orWeekendPassArrivals / 2;
            offtime = true;
        }

        if (this.hour == 7) {
            weekDayArrivals = orWeekdayArrivals;
            weekendArrivals = orWeekendArrivals;
            weekDayPassArrivals = orWeekdayPassArrivals;
            weekendPassArrivals = orWeekendPassArrivals;
            offtime = false;
        }

        if (day == 5 || day == 4 || day == 3) {
            busyDay = true;
            if (this.hour == 18) {
                weekDayArrivals = orWeekdayArrivals + 67;
                weekendArrivals = orWeekendArrivals + 137;
                weekDayPassArrivals = orWeekdayPassArrivals + 26 ;
                weekendPassArrivals = orWeekendPassArrivals + 20 ;
                offtime = false;}

            if (this.hour == 22) {
                weekDayArrivals = orWeekdayArrivals;
                weekendArrivals = orWeekendArrivals;
                weekDayPassArrivals = orWeekdayPassArrivals;
                weekendPassArrivals = orWeekendPassArrivals ;
            }

        }

        if (day == 6) {

            busyDay = true;
            if (this.hour == 12) {
                weekDayArrivals = orWeekdayArrivals + 67;
                weekendArrivals = orWeekendArrivals + 137;
                weekDayPassArrivals = orWeekdayPassArrivals + 26 ;
                weekendPassArrivals = orWeekendPassArrivals + 20 ;
                offtime = false;}

            if (this.hour == 17) {
                weekDayArrivals = orWeekdayArrivals;
                weekendArrivals = orWeekendArrivals ;
                weekDayPassArrivals = orWeekdayPassArrivals;
                weekendPassArrivals = orWeekendPassArrivals ;
                }


        }
    }
    //functie die kijkt of het een "off time" is
    private String checkOfftime()
    {
        if (offtime){return " (Offtime: 50% Less visitors!) ";}
        return " (No offtime)" ;
    }

    //functie die kijkt of het een drukke dag is
    private String checkBusyDay() {
        if (busyDay) {
            switch (this.day) {
                case 3:
                    return "(Thursday: more vistors expected from 18:00-22:00)";
                case 4:
                    return "(Friday: more vistors expected from 18:00-22:00)";
                case 5:
                    return "(Saturday: more vistors expected from 18:00-22:00)";
                case 6:
                    return "(Sunday: more vistors expected from 12:00-17:00)";
            }
        }
        return " (No busyday today)";
    }



    private void advanceTime() {
        // Advance the time by one minute.
        minute += timescale;
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

        int memberRemovedFromQune = entrancePassQueue.removedCars();
        this.membersLevingQenue += memberRemovedFromQune;

        int redom = new Random().nextInt((50 - 1) + 1) + 1;
        int memberLeving = (int) (memberRemovedFromQune / 100 * redom);

        this.noneLevingQenue += entranceCarQueue.removedCars();
        this.reserverdLevingQenue = entranceReserveredQueue.removedCars();

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
//
//        Main.label1.setText("Total amount of free spots: " + floorController.getNumberOfOpenSpots() + "/" + floorController.getNumberOfSpots() );
//        Main.label2.setText("Member " + floorController.getModel(FloorType.FLOOR_TYPE_MENBER.getValue()).getCurrentOpenSpots());
//        Main.label3.setText("Non-Member " + floorController.getModel(FloorType.FLOOR_TYPE_NONE.getValue()).getCurrentOpenSpots());
//        Main.label4.setText("Reserved " + floorController.getModel(FloorType.FLOOR_TYPE_RESAVERED.getValue()).getCurrentOpenSpots());
//        Main.label5.setText("Winst van Non-Members: €" + overigeWinst);
//        Main.label6.setText("Winst van Members: €" + membersWinst);
//        Main.label7.setText("Winst van Reserveerders: €" + reserverdWinst);
//        Main.label8.setText("Totale winst: €" + totaleWinst);
//
//        this.mainWindow.quene1.setText("Queue members:" + this.entrancePassQueue.carsInQueue());
//        this.mainWindow.quene2.setText("Queue reservered:" + this.entranceReserveredQueue.carsInQueue());
//        this.mainWindow.quene3.setText("Pass members:" + this.entranceCarQueue.carsInQueue());
//
//        this.mainWindow.quene4.setText("Cars left: " + this.carsOutQenue);



        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        this.mainWindow.statusBar.setMessage(" Running... " + this.getDayString() + "  " + this.hour + ":" + this.minute + " " + checkOfftime() + checkBusyDay());

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
                    Car clone = null;
                  if (car instanceof AdHocCar){clone = new AdHocCar(); System.out.println("Een member heeft dubbelparkeerd."); membersWinst -= AdHocCar.getPrice(); totaleWinst -= AdHocCar.getPrice(); dubbelParkMember ++; verlorenwinstdubbel += AdHocCar.getPrice();}
                  if (car instanceof ParkingPassCar){clone = new ParkingPassCar();System.out.println("Een overige parkeerder heeft dubbelparkeerd."); overigeWinst -= ParkingPassCar.getPrice(); totaleWinst -= ParkingPassCar.getPrice(); dubbelParkOverige ++; verlorenwinstdubbel += ParkingPassCar.getPrice();}
                  if (car instanceof ParkingReserveredCar){clone = new ParkingReserveredCar();System.out.println("Een reserveerder heeft dubbelparkeerd."); reserverdWinst -= ParkingReserveredCar.getPrice();totaleWinst -= ParkingReserveredCar.getPrice();dubbelParkReserved ++; verlorenwinstdubbel += ParkingReserveredCar.getPrice(); }
                    clone.setDubelParkeren(true);
                    model.setCarAt(freeLocation, clone);
                    this.dubbelParkTotaal++;
                }


            }
            //todo jari dit fixen. "Wat? -Jari"
            if (openMembers > 0 && openSpots <= 0 && car instanceof AdHocCar) {
                Location freeLocation = extraMember.getFirstFreeLocation();
                extraMember.setCarAt(freeLocation, car);
            }

            i++;
        }
    }

    private void carsReadyToLeave() {
        // Add leaving cars to the payment queue.
        Car car = floorController.getFirstLeavingCar();
        while (car != null) {
            if (car.getHasToPay()) {
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            } else {
                if (car instanceof ParkingPassCar) {overigeWinst += ((ParkingPassCar) car).getPrice();
                    totaleWinst += ((ParkingPassCar) car).getPrice();}
                if (car instanceof ParkingReserveredCar) {reserverdWinst += ((ParkingReserveredCar) car).getPrice();
                    totaleWinst += ((ParkingReserveredCar) car).getPrice();}

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


            if (car instanceof AdHocCar) {membersWinst += ((AdHocCar) car).getPrice();
                totaleWinst += ((AdHocCar) car).getPrice();}

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
                    if (!this.entrancePassQueue.addCar(new AdHocCar())) {
                        this.membersLevingQenue++;
                    }
                }
                break;
            case FLOOR_TYPE_NONE:
                for (int i = 0; i < numberOfCars; i++) {
                    if (!this.entranceCarQueue.addCar(new ParkingPassCar())) {
                        this.noneLevingQenue++;
                    }
                }
                break;
            case FLOOR_TYPE_RESAVERED:
                for (int i = 0; i < numberOfCars; i++) {
                    if (!this.entranceReserveredQueue.addCar(new ParkingReserveredCar())) {
                        this.reserverdLevingQenue++;
                    }
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
                this.dubbelParkTotaal--;
                if (car instanceof AdHocCar){dubbelParkMember --;}
                if (car instanceof ParkingPassCar){dubbelParkOverige --;}
                if (car instanceof ParkingReserveredCar){dubbelParkReserved --;}

            }
        }

        exitCarQueue.addCar(car);
    }

}
