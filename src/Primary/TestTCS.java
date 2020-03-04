package Primary;

import Graphics.Traffic.Car;
import Primary.Team01_Files.Road;
import Primary.Team01_Files.Timing;

import java.util.LinkedList;

/**
 * TestTCS is the access point for Traffic Control System (TCS) interaction with the testbed.
 * There exist a total of 5 method calls for interacting with the testbed properly.
 * These 5 methods are detailed and demo'd below. Additional tips are included for
 * interacting with the testbed.
 *
 *   Methods of interest.
 *       Class: Lanes
 *           public boolean getCarOnLane()
 *           public boolean getEmergencyOnLane()
 *           public void setColor(SignalColor color)
 *
 *       Class: Lights
 *           public void setColor(SignalColor c)
 *           public boolean isPedestrianAt()
 *
 */
public class TestTCS extends Thread {

    /*   Start TEAM01 variables/Methods for TICS */


    //here is a set of "registers" that will keep
    //track of events inside of TICS along with setters
    //so that other objects can set if the event happened
    private boolean pedPresentN = false, pedPresentS = false,
                    pedPresentW = false, pedPresentE = false;
    private boolean carPresentNS = false, carPresentWE = false;
    private boolean carTurnN = false, carTurnS = false,
                    carTurnW = false, carTurnE = false;

    public void setPedPresentN() {
        pedPresentN = true;
    }

    public void setPedPresentS() {
        pedPresentS = true;
    }

    public void setPedPresentW() {
        pedPresentW = true;
    }

    public void setPedPresentE() {
        pedPresentE = true;
    }

    public void setCarPresentNS() {
        carPresentNS = true;
    }

    public void setCarPresentWE() {
        carPresentWE = true;
    }

    public void setCarTurnN() {
        carTurnN = true;
    }

    public void setCarTurnS() {
        carTurnS = true;
    }

    public void setCarTurnE(){
        carTurnE = true;
    }

    public void setCarTurnW(){
        carTurnW = true;
    }

    //variables for TICS internal state tracker here
    enum States{
        dayMode,
        nightMode,
        fourWayStopMode,
        emergencyMode,
        transitionMode
    }

    States curState = States.dayMode;
    States prevState = States.dayMode;

    /*
     * This is called by the roads to notify TICS
     * it is changing lights
     */
    public void setTransition(){
        prevState = curState;
        curState = States.transitionMode;
    }

    //The timer will consists of the start time and will use
    //a delta time as the main timer. Delta time will be in
    //seconds
    private long startTime = 0;
    private long deltaTime = 0;

    /*   End TEAM01 variables/Methods for TICS */

    private int count = 0;

    private Boolean running = true;

    /**
     * TestTCS.begin() is the communication point between the testbed and the
     * TCS being tested on. Interactions between the testbed and TCS should all
     * be laid out in this method.
     */
    public void begin() {

        //TimedModeTest.run();
        //ResponsiveTest.run();
        //EmergencyModeTest.run();

    }

    /*
     * This is the old begin method. I kept it here for reference
     */
    public void testBegin() {


        /*
        RED (Color.RED),
        YELLOW (Color.YELLOW),
        GREEN (Color.GREEN),
        BLACK (Color.BLACK);

        SignalColor is an enum holding possible signal colors.
         */
//        SignalColor north_south_color, east_west_color;
//
//        /*
//        This is a useful way of grouping lights by direction.
//        Here we are grouping parallel directions north with south, and east with west.
//         */
//        LinkedList<Lanes> north_south = new LinkedList<>();
//        LinkedList<Lanes> east_west = new LinkedList<>();
//        for(Lanes l: Lanes.values())
//        {
//            if(l.toString().contains("N") || l.toString().contains("S")) north_south.add(l);
//            else east_west.add(l);
//        }
//-----------------------
                /*
        RED (Color.RED),
        YELLOW (Color.YELLOW),
        GREEN (Color.GREEN),
        BLACK (Color.BLACK);

        SignalColor is an enum holding possible signal colors.
         */
        SignalColor north_south_color, east_west_color,north_south_left_color,east_west_left_color ;

        /*
        This is a useful way of grouping lights by direction.
        Here we are grouping parallel directions north with south, and east with west.
         */
        LinkedList<Lanes> north_south = new LinkedList<>();
        LinkedList<Lanes> east_west = new LinkedList<>();
        LinkedList<Lanes> east_west_left = new LinkedList<>();
        LinkedList<Lanes> north_south_left = new LinkedList<>();


        for(Lanes l: Lanes.values())
        {
            System.out.println(l.toString());
            if(l.toString().contains("N") || l.toString().contains("S")) {
                if(l.toString().contains("1")) {
                    north_south_left.add(l);
                }
                else{
                    north_south.add(l);
                }
            }
            else {
                if(l.toString().contains("1")) {
                    east_west_left.add(l);
                }
                else{
                    east_west.add(l);
                }
            }
        }


        while(running){
            double timingTestNORTHLEFT = Timing.getTiming(20,"NORTH","LEFT");
            double timingTestNORTHOTHER = Timing.getTiming(20,"NORTH","OTHER");
            double timingTestSOUTHLEFT = Timing.getTiming(20,"SOUTH","LEFT");
            double timingTestSOUTHOTHER = Timing.getTiming(20,"SOUTH","OTHER");
            double timingTestEASTLEFT = Timing.getTiming(20,"EAST","LEFT");
            double timingTestEASTOTHER = Timing.getTiming(20,"EAST","OTHER");
            double timingTestWESTLEFT = Timing.getTiming(20,"WEST","LEFT");
            double timingTestWESTOTHER = Timing.getTiming(20,"WEST","OTHER");

            System.out.println("North Left Timing Now in seconds"+ timingTestNORTHLEFT );
            System.out.println("North Other Two Lanes Timing Now in seconds"+ timingTestNORTHOTHER );
            System.out.println("South Left Timing Now in seconds"+ timingTestSOUTHLEFT );
            System.out.println("South Other Two Lanes Timing Now in seconds"+ timingTestSOUTHOTHER );
            System.out.println("East Left Timing Now in seconds"+ timingTestEASTLEFT );
            System.out.println("East Other Two Lanes Timing Now in seconds"+ timingTestEASTOTHER );
            System.out.println("West Left Timing Now in seconds"+ timingTestWESTLEFT );
            System.out.println("West Other Two Lanes Timing Now in seconds"+ timingTestWESTOTHER );

//
//            /*
//            This is a simple way of alternating the states of signal colors on a timed basis.
//             */
//
//            if (count %  4 ==  0){
//                north_south_color = SignalColor.GREEN;
//                east_west_color = SignalColor.RED;
//            } else if (count % 4 == 1){
//                north_south_color = SignalColor.YELLOW;
//                east_west_color = SignalColor.RED;
//            } else if (count % 4 == 2){
//                north_south_color = SignalColor.RED;
//                east_west_color = SignalColor.GREEN;
//            } else {
//                north_south_color = SignalColor.RED;
//                east_west_color = SignalColor.YELLOW;
//            }
//
//            /*
//            This changes our grouping of lanes to the colors specified above.
//             */
//            for(Lanes l: north_south)
//            {
//                l.setColor(north_south_color);
//            }
//            for(Lanes l: east_west)
//            {
//                l.setColor(east_west_color);
//            }
//            Lights.WEST.setColor(SignalColor.GREEN);
//            count ++;

                        /*
            This is a simple way of alternating the states of signal colors on a timed basis.
             */

            //Assign Times for each line keep the states of left turnes seperate
            //Total of

            if (count %  8 ==  0){
                north_south_color = SignalColor.GREEN;
                east_west_color = SignalColor.RED;
                north_south_left_color = SignalColor.RED;
                east_west_left_color = SignalColor.RED;
            } else if (count % 8 == 1){
                north_south_color = SignalColor.YELLOW;
                east_west_color = SignalColor.RED;
                north_south_left_color = SignalColor.RED;
                east_west_left_color = SignalColor.RED;
            } else if (count % 8 == 2){
                north_south_color = SignalColor.RED;
                east_west_color = SignalColor.RED;
                north_south_left_color = SignalColor.GREEN;
                east_west_left_color = SignalColor.RED;
            }
            else if (count % 8 == 3){
                north_south_color = SignalColor.RED;
                east_west_color = SignalColor.RED;
                north_south_left_color = SignalColor.YELLOW;
                east_west_left_color = SignalColor.RED;
            }
            else if (count % 8 == 4){
                north_south_color = SignalColor.RED;
                east_west_color = SignalColor.GREEN;
                north_south_left_color = SignalColor.RED;
                east_west_left_color = SignalColor.RED;
            }
            else if (count % 8 == 5){
                north_south_color = SignalColor.RED;
                east_west_color = SignalColor.YELLOW;
                north_south_left_color = SignalColor.RED;
                east_west_left_color = SignalColor.RED;
            }
            else if (count % 8 == 6){
                north_south_color = SignalColor.RED;
                east_west_color = SignalColor.RED;
                north_south_left_color = SignalColor.RED;
                east_west_left_color = SignalColor.GREEN;
            }
            else {
                north_south_color = SignalColor.RED;
                east_west_color = SignalColor.RED;
                north_south_left_color = SignalColor.RED;
                east_west_left_color = SignalColor.YELLOW;
            }

            /*
            This changes our grouping of lanes to the colors specified above.
             */
            for(Lanes l: north_south) {
                if (l.toString().contains("2") || l.toString().contains("3")) {
                    l.setColor(north_south_color);
                    Lights.WEST.setColor(SignalColor.GREEN);
                    Lights.EAST.setColor(SignalColor.GREEN);
                    Lights.NORTH.setColor(SignalColor.RED);
                    Lights.SOUTH.setColor(SignalColor.RED);
                }

            }
            for(Lanes l: east_west)
            {
                if (l.toString().contains("2") || l.toString().contains("3")) {
                    l.setColor(east_west_color);
                    Lights.NORTH.setColor(SignalColor.GREEN);
                    Lights.SOUTH.setColor(SignalColor.GREEN);
                    Lights.WEST.setColor(SignalColor.RED);
                    Lights.EAST.setColor(SignalColor.RED);
                }

            }
            for(Lanes l: north_south_left) {
                if (l.toString().contains("1")) {
                    l.setColor(north_south_left_color);
                    Lights.NORTH.setColor(SignalColor.RED);
                    Lights.SOUTH.setColor(SignalColor.RED);
                    Lights.WEST.setColor(SignalColor.RED);
                    Lights.EAST.setColor(SignalColor.RED);
                }
            }
            for(Lanes l: east_west_left)
            {
                if (l.toString().contains("1")) {
                    l.setColor(east_west_left_color);
                    Lights.NORTH.setColor(SignalColor.RED);
                    Lights.SOUTH.setColor(SignalColor.RED);
                    Lights.WEST.setColor(SignalColor.RED);
                    Lights.EAST.setColor(SignalColor.RED);
                }
            }


            count ++;

            testSensors();

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            deltaTime = (System.currentTimeMillis() - startTime) / 1000;
        }

        Road roadNS = new Road(this, Road.RoadType.NS);
        Road roadWE = new Road(this, Road.RoadType.WE);
        roadNS.setActive(true);
        roadWE.setActive(false);
        startTime = System.currentTimeMillis();
        while(running){

            roadNS.update(deltaTime);
            roadWE.update(deltaTime);

            switch (curState){
                case dayMode:
                    break;
                case nightMode:
                    break;
                case emergencyMode:
                    break;
                case transitionMode:
                    break;
                case fourWayStopMode:
                    break;
            }

            deltaTime = (System.currentTimeMillis() - startTime) / 1000;
        }
        System.out.println("Test ended..");
    }

    public void end(){
        running = false;
    }

    /*
    Loops over the ped crosswalks and car lanes to
    print if each has traffic waiting on it
    */
    private void testSensors() {
        for (Lanes l : Lanes.values()) {
//            System.out.println(l.toString() + " has car waiting: " + l.isCarOnLane());
        }

        for (Lights l : Lights.values()){
//            System.out.println(l.toString() + " has ped waiting: " + l.isPedestrianAt());
        }
    }

}
