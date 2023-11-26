public class Fare {


    public static long fareHour(long hour, String vehicleType) {

        switch (vehicleType) {
            case "Car":
                return hour*100;
            case "Bike":
                return hour * 60;
            case "Van":
                return hour*120;
        }
        return 0;
    }
    public static long fareDay(long days,String vehicleType){
        switch (vehicleType) {
            case "Car":
                return days*200;
            case "Bike":
                return days * 360;
            case "Van":
                return days*450;
        }
        return 0;
    }


}