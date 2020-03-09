package Primary.Team01_Files;

import Graphics.Traffic.Car;

public class Lane {

    public static boolean checkLane(String LaneSpefied) {
        boolean carsOnLane = false;
        for (Primary.Lanes l : Primary.Lanes.values()) {
            if (l.toString() == LaneSpefied) {
//                System.out.println(l.toString() + " has car waiting: " + l.isCarOnLane());
                carsOnLane = l.isCarOnLane();
            }
        }
        return carsOnLane;
    }

    public static boolean checkEmergencyonLane(String LaneSpefied) {
        boolean emergencyOnLane = false;
        for (Primary.Lanes l : Primary.Lanes.values()) {
            if (l.toString() == LaneSpefied) {
//                System.out.println(l.toString() + " has car waiting: " + l.isCarOnLane());
                emergencyOnLane = l.getEmergencyOnLane();
            }
        }
        return emergencyOnLane;
    }
}
