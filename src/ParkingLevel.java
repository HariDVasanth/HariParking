import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class ParkingLevel {
    private  int levelNumber;
    Scanner scanner=new Scanner(System.in);
    private ArrayList<ParkingSpot[][]>cars=new ArrayList<>();
    private ArrayList<ParkingSpot[][]>vans=new ArrayList<>();
    private ArrayList<ParkingSpot[][]>bikes=new ArrayList<>();
    private  ParkingSpot[][] carSpots;
    private  ParkingSpot[][] vanSpots;
    private  ParkingSpot[][] bikeSpots;
    private HashMap<String,ParkingSpot> details=new HashMap<>();

    public ParkingLevel(){

    }
    public void addSpots(int levelNumber,int carRow, int carColumn, int bikeRow, int bikeColumn,int vanRow,int vanColumn) {
        this.levelNumber = levelNumber;
        this.carSpots = new ParkingSpot[carRow][carColumn];
        this.vanSpots = new ParkingSpot[vanRow][vanColumn];
        this.bikeSpots = new ParkingSpot[bikeRow][bikeColumn];

        initializeCarSpots(carRow,carColumn);
        initializeVanSpots(vanRow,vanColumn);
        initializeBikeSpots(bikeRow,bikeColumn);
    }

    private void initializeCarSpots(int carRow,int carColumn) {
        int spotNumber=1;
        for (int i = 0; i < carRow; i++) {
            for (int j = 0; j < carColumn; j++) {
                carSpots[i][j]=new ParkingSpot("Car",spotNumber);
                spotNumber++;

            }
        }
        cars.add(carSpots);
    }
    private void initializeVanSpots(int vanRow,int vanColumn) {
        int spotNumber=1;
        for (int i = 0; i < vanRow; i++) {
            for(int j=0;j< vanColumn;j++){
                vanSpots[i][j]=new ParkingSpot("Van", spotNumber);
                spotNumber++;
            }
        }
        vans.add(vanSpots);
    }
    private void initializeBikeSpots(int bikeRow,int bikeColumn) {
        int spotNumber=1;
        for (int i = 0; i < bikeRow; i++) {
            for (int j = 0; j < bikeColumn; j++) {
                bikeSpots[i][j] = new ParkingSpot("Bike",spotNumber);
                spotNumber++;
            }
        }
        bikes.add(bikeSpots);
    }
    public ArrayList<ParkingSpot[][]> getCars() {
        return cars;
    }
    public ArrayList<ParkingSpot[][]> getVans() {
        return vans;
    }

    public ArrayList<ParkingSpot[][]> getBikes() {
        return bikes;
    }

    public boolean areCarSpotsAvailable() {
        ParkingSpot[][] carSpots = getCars().get(getCars().size() - 1);
        for (ParkingSpot[] row : carSpots) {
            for (ParkingSpot spot : row) {
                if (!spot.isOccupied()) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean areBikeSpotsAvailable(){
        ParkingSpot[][] bikeSpots = getBikes().get(getBikes().size() - 1);
        for (ParkingSpot[] row : bikeSpots) {
            for (ParkingSpot spot : row) {
                if (!spot.isOccupied()) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean areVanSpotsAvailable(){
        ParkingSpot[][] vanSpots = getVans().get(getVans().size() - 1);
        for (ParkingSpot[] row : vanSpots) {
            for (ParkingSpot spot : row) {
                if (!spot.isOccupied()) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean parkCar(String vehicleNumber,long time,String durationType) {
        String vehicleType="Car";
        return parkVehicle(cars,vehicleNumber,time,durationType,vehicleType);
    }

    public boolean parkVan(String vehicleNumber,long time,String durationType) {
        String vehicleType="Van";
        return parkVehicle(vans,vehicleNumber,time,durationType,vehicleType);
    }

    public boolean parkBike(String vehicleNumber,long time,String durationType) {
        String vehicleType="Bike";
        return parkVehicle(bikes,vehicleNumber,time,durationType,vehicleType);
    }
    private boolean parkVehicle(ArrayList<ParkingSpot[][]> vehicleList,String vehicleNumber,long seconds,String durationType,String vehicleType) {
        for (int levelIndex = 0;levelIndex<= vehicleList.size() - 1; levelIndex++) {
            ParkingSpot[][] currentLevelSpots = vehicleList.get(levelIndex);
            for (ParkingSpot[] currentLevelSpot : currentLevelSpots) {
                for (ParkingSpot spot : currentLevelSpot) {
                    if (!spot.isOccupied()) {
                        spot.occupy();
                        spot.setTime(seconds,vehicleType);

                        spot.setDuration(durationType);
                        details.put(vehicleNumber, spot);

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printLevelStatus() {
        for(int i=1;i<=levelNumber;i++) {
            System.out.println("Level " + i + " Status:");
            printSpotStatus("Car Spots: " + "\n", getCars().get(i-1));
            printSpotStatus("Van Spots: " + "\n", getVans().get(i-1));
            printSpotStatus("Bike Spots: " + "\n",getBikes().get(i-1));
            System.out.println();
        }
    }

    public void printSpotStatus(String spotType, ParkingSpot[][] spots) {
        System.out.print(spotType);
        for (ParkingSpot[] row : spots) {
            for (ParkingSpot spot : row) {
                System.out.print(spot.isOccupied() ? "O " : "V ");
            }
            System.out.println();
        }
    }
    public int getSpots(String vehicleNumber){
        int spot=0;
        for(Map.Entry<String,ParkingSpot>entry:details.entrySet()){
            String vehicle=entry.getKey();
            ParkingSpot value=entry.getValue();
            if(vehicleNumber.equals(vehicle)) {
                spot = value.getSpotNumber();
            }
        }
        return spot;
    }
    public boolean retrieveCar(int seatNumber,ArrayList<Integer>time,String durationType,String vehicleNumber){

        return retrieveVehicle(cars,seatNumber,time,durationType,vehicleNumber);
    }
    public boolean retrieveBike(int seatNumber,ArrayList<Integer>time,String durationType,String vehicleNumber){
        return retrieveVehicle(bikes,seatNumber,time,durationType,vehicleNumber);
    }
    public boolean retrieveVan(int seatNumber,ArrayList<Integer>time,String durationType,String vehicleNumber){
        return retrieveVehicle(vans,seatNumber,time,durationType,vehicleNumber);
    }

    public boolean retrieveVehicle(ArrayList<ParkingSpot[][]> spots, int seatNumber, ArrayList<Integer> time, String durationType,String vehicleNumber) {
        for (ParkingSpot[][] spotList : spots) {
            for (ParkingSpot[] row : spotList) {
                for (ParkingSpot spot : row) {
                    if (spot.getSpotNumber() == seatNumber && spot.isOccupied() && spot.getDurationType().equals(durationType)) {
                        if(getTime(durationType, time,vehicleNumber,seatNumber)){
                            spot.vacate();
                            details.remove(getVehicleNumberForSpot(spot));
                            return true;
                        }
                    }
                }
            }
        }

        // No matching spot
        System.out.println("Invalid details. No matching spot found.");
        return false;
    }



    public boolean getTime(String durationType, ArrayList<Integer> time,String vehicleNumber,int seatNumber) {
        String vehicleType="";

        for (Map.Entry<String, ParkingSpot> entry : details.entrySet()) {
            String key=entry.getKey();
            if(key.equals(vehicleNumber)) {
                ParkingSpot value = entry.getValue();
                vehicleType = value.getVehicleType();

                if (durationType.equals("Long")) {
                    long entryTimestamp = Main.daysToSeconds(time.get(0), time.get(1));
                    long parkedTimestamp = value.getTime(vehicleType).get(seatNumber-1);
                    long daysDifference = Main.secondsToDays(entryTimestamp) - Main.secondsToDays(parkedTimestamp);
                    long fare= Fare.fareDay(daysDifference, vehicleType);
                    System.out.println("Amount to pay: " + fare);
                    System.out.print("Enter the amount to pay: ");
                    long amount= scanner.nextLong();
                    if(fare==amount){
                        value.getTime(vehicleType).remove(seatNumber-1);
                        return true;
                    }
                    else {
                        System.out.println("Payment does not match the required amount. Transaction canceled.");
                    }

                } else if (durationType.equals("Short")) {
                    long entryTimestamp = Main.hoursToSeconds(time.get(0), time.get(1));
                    long parkedTimestamp = value.getTime(vehicleType).get(0);
                    long hoursDifference = Main.secondsToHours(entryTimestamp) - Main.secondsToHours(parkedTimestamp);
                    long fare= Fare.fareHour(hoursDifference, vehicleType);
                    System.out.println("Amount to pay: " + fare);
                    System.out.print("Enter the amount to pay: ");
                    long amount= scanner.nextLong();
                    if(fare==amount){
                        value.getTime(vehicleType).remove(seatNumber-1);
                        return true;
                    } else {
                        System.out.println("Payment does not match the required amount. Transaction canceled.");
                    }

                }
            }
        }

        return false;
    }
    private String getVehicleNumberForSpot(ParkingSpot spot) {
        for (Map.Entry<String, ParkingSpot> entry : details.entrySet()) {
            if (entry.getValue().equals(spot)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
