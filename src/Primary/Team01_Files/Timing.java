package Primary.Team01_Files;

import Graphics.Traffic.Car;
import Primary.Lanes;
import Primary.Lights;
import Primary.TestTCS;
import javafx.geometry.Side;

public class Timing {




    public static double getTiming(double defaultiming,String side, String laneDirection) {
        Integer[] allCounters = Car.getCounters();
        int totalCarsNeededtoPass = 100;
        //Collecting Data Phase - Get At Least 100 Cars Passed Before Building
        //The Algorithm to Calculate Timing For efficiency
        int totalCarsSpawned = 0;
        for (int i = 0; i <= 7; i++) {
            totalCarsSpawned += allCounters[i];
        }

        double Defaulttiming= defaultiming;
//        System.out.println("Total Cars Spaned"+totalCarsSpawned);
        double CounterN1 = allCounters[0];
        double CounterN23 = allCounters[1];
        double CounterS1 = allCounters[2];
        double CounterS23 = allCounters[3];
        double CounterW1 = allCounters[4];
        double CounterW23 = allCounters[5];
        double CounterE1 = allCounters[6];
        double CounterE23 = allCounters[7];
        if (totalCarsSpawned >= totalCarsNeededtoPass && CounterN1>=1 && CounterN23>=1 &&CounterE1>=1 &&CounterE23>=1 &&CounterW1>=1 &&CounterW23>=1 &&CounterS1>=1 && CounterS23>=1) {
        //Declaring counters for each lane


        //Setting up Timing for Each Lane here
        // Gives us the percentage of traffic being on the lane
            double TimingN1_Percentage = CounterN1 / totalCarsSpawned * 100;
//            System.out.println(CounterN1);
//            System.out.println(TimingN1_Percentage);
            double TimingN23_Percentage = CounterN23 / totalCarsSpawned * 100;
//            System.out.println(CounterN23);
//            System.out.println(TimingN23_Percentage);
            double TimingS1_Percentage = CounterS1 / totalCarsSpawned * 100;
//            System.out.println(CounterS1);
//            System.out.println(TimingS1_Percentage);
            double TimingS23_Percentage = CounterS23 / totalCarsSpawned * 100;
//            System.out.println(CounterS23);
//            System.out.println(TimingS23_Percentage);
            double TimingE1_Percentage = CounterE1 / totalCarsSpawned * 100;
//            System.out.println(CounterE1);
//            System.out.println(TimingE1_Percentage);

            double TimingE23_Percentage = CounterE23 / totalCarsSpawned * 100;
//            System.out.println(CounterE23);
//            System.out.println(TimingE23_Percentage);

            double TimingW1_Percentage = CounterW1 / totalCarsSpawned * 100;
//            System.out.println(CounterW1);
//            System.out.println(TimingW1_Percentage);

            double TimingW23_Percentage = CounterW23 / totalCarsSpawned * 100;
//            System.out.println(CounterW23);
//            System.out.println(TimingW23_Percentage);
//
//            System.out.println("Calculated Timing Percentage");

        //Getting Total Timing to Let All the Lanes Pass
            double totalTimingForAllLanes = 60;

        //Equally Splitting the Lanes Depending on The Cars Previously Passed
            double TimingN1 = totalTimingForAllLanes * TimingN1_Percentage / 100;
//            System.out.println("Timing N1" + TimingN1);
            double TimingN23 = totalTimingForAllLanes * TimingN23_Percentage / 100;
            double TimingS1 = totalTimingForAllLanes * TimingS1_Percentage / 100;
            double TimingS23 = totalTimingForAllLanes * TimingS23_Percentage / 100;
            double TimingE1 = totalTimingForAllLanes * TimingE1_Percentage / 100;
            double TimingE23 = totalTimingForAllLanes * TimingE23_Percentage / 100;
            double TimingW1 = totalTimingForAllLanes * TimingW1_Percentage / 100;
            double TimingW23 = totalTimingForAllLanes * TimingW23_Percentage / 100;
//            System.out.println("Calculated Timings");


        //Check if total cars passed is bigger than pre-defined 100

            if (side == "NORTH") {
                if (laneDirection == "LEFT") {
                    return TimingN1;
                } else {
                    return TimingN23;
                }
            } else if (side == "SOUTH") {
                if (laneDirection == "LEFT") {
                    return TimingS1;
                } else {
                    return TimingS23;
                }
            } else if (side == "WEST") {
                if (laneDirection == "LEFT") {
                    return TimingW1;
                } else {
                    return TimingW23;
                }
            } else if (side == "EAST") {
                if (laneDirection == "LEFT") {
                    return TimingE1;
                } else {
                    return TimingE23;
                }
            }
        }
        return Defaulttiming;
//        System.out.println(allCounters[0]);

    }
}
