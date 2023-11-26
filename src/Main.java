import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        ParkingLevel parkingLevel = new ParkingLevel();
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("1.Enter seat requirements of parking lot.");
                System.out.println("2.Vehicle Entry.");
                System.out.println("3.Vehicle Exit.");
                System.out.println("4.Exit ");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter number of levels to add");
                        int levels = scanner.nextInt();
                        for (int i = 1; i <= levels; i++) {
                            System.out.print("Enter Bike rows: ");
                            int bikeRows = scanner.nextInt();
                            System.out.print("Enter Bike columns: ");
                            int bikeColumns = scanner.nextInt();
                            System.out.print("Enter Car rows: ");
                            int carRows = scanner.nextInt();
                            System.out.print("Enter car columns: ");
                            int carColumns = scanner.nextInt();
                            System.out.print("Enter van rows: ");
                            int vanRows = scanner.nextInt();
                            System.out.print("Enter van columns: ");
                            int vanColumns = scanner.nextInt();

                            parkingLevel.addSpots(i, carRows, carColumns, bikeRows, bikeColumns, vanRows, vanColumns);
                        }



                        break;

                    case 2:
                        System.out.println("Select vehicle type: ");
                        System.out.println("1.Car");
                        System.out.println("2.Van");
                        System.out.println("3.Bike");
                        int option = scanner.nextInt();
                        String duration="";

                        if (option == 1) {
                            if(parkingLevel.areCarSpotsAvailable()) {
                                System.out.print("Enter vehicle number");
                                String vehicleNUmber = scanner.next();
                                ArrayList<Object> data = duration(duration);
                                duration = (String) data.get(0);
                                long first = (long) data.get(1);
                                long second=(long)data.get(2);
                                if(first!=0) {
                                    parkVehicles(parkingLevel, "Car", vehicleNUmber, first, duration);
                                }else{
                                    parkVehicles(parkingLevel, "Car", vehicleNUmber, second, duration);
                                }
                            }else {
                                System.out.println("Car spots not available");
                            }

                        } else if (option == 2) {
                            if(parkingLevel.areVanSpotsAvailable()) {
                                System.out.print("Enter your vehicle number: ");
                                String vehicleNUmber = scanner.next();
                                ArrayList<Object> data = duration(duration);
                                duration = (String) data.get(0);
                                long first=(long)data.get(1);
                                long second = (long) data.get(2);
                                if(first!=0) {
                                    parkVehicles(parkingLevel, "Van", vehicleNUmber, first, duration);
                                }
                                else{
                                    parkVehicles(parkingLevel, "Van", vehicleNUmber, second, duration);
                                }
                            }else {
                                System.out.println("Vans spots not available.");
                            }
                        } else if (option == 3) {
                            if(parkingLevel.areBikeSpotsAvailable()) {
                                System.out.print("Enter your vehicle number: ");
                                String vehicleNUmber = scanner.next();
                                ArrayList<Object> data = duration(duration);
                                duration = (String) data.get(0);
                                long second = (long) data.get(1);

                                parkVehicles(parkingLevel, "Bike", vehicleNUmber, second, duration);
                            }else {
                                System.out.println("Bikes spots are not available.");
                            }
                        }
                        break;
                    case 3:
                        ArrayList<Integer>time=new ArrayList<>();
                        System.out.println("Enter vehicle type: ");
                        System.out.println("1.Car");
                        System.out.println("2.Bike");
                        System.out.println("3.Vans");
                        int userChoice=scanner.nextInt();
                        String vehicleType="";
                        switch (userChoice){
                            case 1:
                                vehicleType="Car";
                                break;
                            case 2:
                                vehicleType="Bike";
                                break;
                            case 3:
                                vehicleType="Van";
                                break;
                            default:
                                System.out.println("Please choose right option");
                        }
                        System.out.println("Enter your spot number");
                        int spotNumber = scanner.nextInt();
                        System.out.println("Enter your vehicle number");
                        String vehicleNumber= scanner.next();
                        System.out.println("Enter duration type: ");
                        System.out.println("1.Short duration");
                        System.out.println("2.Long duration");
                        int user= scanner.nextInt();
                        switch (user){
                            case 1:
                                String durationType="Short";
                                System.out.println("Enter Hour(HH): ");
                                int hour= scanner.nextInt();
                                time.add(hour);
                                System.out.println("Enter minute(MM): ");
                                int minute= scanner.nextInt();
                                time.add(minute);
                                retrieve(parkingLevel, vehicleType, spotNumber,time,durationType,vehicleNumber);
                                time.clear();
                                break;
                            case 2:
                                String durationType1="Long";
                                System.out.println("Enter day: ");
                                int day= scanner.nextInt();
                                time.add(day);
                                System.out.println("Enter month");
                                int month=scanner.nextInt();
                                time.add(month);
                                retrieve(parkingLevel, vehicleType, spotNumber,time,durationType1,vehicleNumber);
                                time.clear();
                                break;
                        }

                        break;

                    case 4:
                        return;


                }
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Enter correct value");
        }
    }

    private static void parkVehicles(ParkingLevel parkingLevel, String vehicleType,String vehicleNumber,long time,String durationType) {

        boolean parked;

        switch (vehicleType) {
            case "Car":
                parked = parkingLevel.parkCar(vehicleNumber,time,durationType);
                if(parked){
                    System.out.println("Car parked at: "+parkingLevel.getSpots(vehicleNumber));
                }
                break;
            case "Van":
                parked = parkingLevel.parkVan(vehicleNumber,time,durationType);
                if(parked){
                    System.out.println("Van parked at: "+parkingLevel.getSpots(vehicleNumber));
                }
                break;
            case "Bike":
                parked = parkingLevel.parkBike(vehicleNumber,time,durationType);
                if(parked){
                    System.out.println("Bike parked at: "+parkingLevel.getSpots(vehicleNumber));
                }
                break;

        }
    }
    public static void retrieve(ParkingLevel parkingLevel,String vehicleType, int spotNumber,ArrayList<Integer>time,String durationType,String vehicleNumber){
        boolean retrieved;

        switch (vehicleType){
            case "Car":
                retrieved=parkingLevel.retrieveCar(spotNumber,time,durationType,vehicleNumber);
                if(retrieved){
                    System.out.println("Car slot available");
                }
                break;
            case "Van":
                retrieved=parkingLevel.retrieveVan(spotNumber,time,durationType,vehicleNumber);
                if(retrieved) {
                    System.out.println("Van slot available");
                }
                break;
            case "Bike":
                retrieved=parkingLevel.retrieveBike(spotNumber,time,durationType,vehicleNumber);
                if(retrieved) {
                    System.out.println("Bike slot available");
                }
                break;
        }

    }
    public static ArrayList<Object> duration(String durations){
        ArrayList<Object>data=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter duration");
        System.out.println("1.Short duration(less than 24 hours)");
        System.out.println("2.Long duration(more than a day)");
        int duration = scanner.nextInt();
        long daysSeconds=0;
        long monthsSeconds=0;
        if (duration == 1) {
            daysSeconds=entryTime();
            durations="Short";
        } else if (duration == 2) {
            monthsSeconds=entryDays();
            durations="Long";
        }
        data.add(durations);
        data.add(daysSeconds);
        data.add(monthsSeconds);
        return data;
    }
    private static long  entryTime(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Entry Time");
        System.out.print("Enter Hour(HH): ");
        int hour= scanner.nextInt();

        System.out.print("Entry minutes(MM): ");
        int minutes= scanner.nextInt();
        return hoursToSeconds(hour,minutes);
    }
    public static long entryDays(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter date");
        int date= scanner.nextInt();
        System.out.println("Enter ongoing month");
        int month= scanner.nextInt();
        return daysToSeconds(date,month);
    }
    public static long hoursToSeconds(int hours, int minutes) {
        return ((long) hours * 60 * 60) + (minutes * 60L);
    }

    public static long daysToSeconds(int days, int month) {
        int daysInMonth;

        if (month == 1 || month == 3 || month == 5 ||month==6|| month == 7|| month==10 || month==12) {
            daysInMonth = 31;
        } else if (month == 2) {
            daysInMonth = 28;
        } else {
            daysInMonth = 30;
        }

        if (days < 0 || days > daysInMonth) {
            throw new IllegalArgumentException("Invalid number of days for the given month");
        }

        long monthsInSeconds = (month - 1) * daysInMonthToSeconds(31);
        long daysInSeconds = days * 24 * 60 * 60;
        return daysInSeconds + monthsInSeconds;

    }
    public static long daysInMonthToSeconds(int daysInMonth) {
        return (long) daysInMonth * 24 * 60 * 60;
    }
    public static int secondsToHours(long seconds){
        return (int)seconds/3600;
    }
    public static int secondsToDays(long second){

        return (int) (second / (24 * 3600));
    }
}

