import java.util.ArrayList;

public class ParkingSpot {
    private String vehicleType;
    private int spotNumber;

    private ArrayList<Long> carTime=new ArrayList<>();
    private ArrayList<Long>bikeTime=new ArrayList<>();
    private ArrayList<Long>vanTime=new ArrayList<>();
    private boolean occupied;
    private String durationType;


    public ParkingSpot(String vehicleType, int spotNumber) {
        this.vehicleType = vehicleType;
        this.spotNumber = spotNumber;
        this.occupied = false;

    }
    public void setTime(Long time,String vehicleType){
        switch (vehicleType){
            case "Car":
                carTime.add(time);
                break;
            case "Bike":
                bikeTime.add(time);
                break;
            case "Van":
                vanTime.add(time);
                break;

        }
    }
    public boolean isOccupied() {
        return occupied;
    }
    public void occupy() {
        occupied = true;
    }
    public void vacate() {
        occupied = false;
    }
    public void setDuration(String duration) {
        this.durationType = duration;
    }
    public ArrayList<Long> getTime(String vehicleType){
        switch (vehicleType){
            case "Car":
                return carTime;
            case "Bike":
                return bikeTime;
            case "Van":
                return vanTime;
        }
        return null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public String getDurationType() {
        return durationType;
    }
    public String getVehicleType() {
        return vehicleType;
    }

}


