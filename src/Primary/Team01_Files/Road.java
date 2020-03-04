package Primary.Team01_Files;

import Primary.*;

public class Road {

    public enum RoadType{
        NS,
        WE
    }

    //These variables should be set by the implementer
    //and be treated as final once set, these are just
    //default values I put in.
    protected int GREEN_WAIT_TIME = 5;
    protected int YELLOW_WAIT_TIME = 3;

    //sets the time when the last light in the road
    //was changed to green, e.g. after the left turns
    //are done this will be set
    protected int greenSetTime = 0;

    protected Lanes leftUp, midUp, rightUp,
                    leftDown, midDown, rightDown;
    protected Lights leftPed, rightPed;


    protected final RoadType rType;

    protected TestTCS owner;

    //if the intersection is the current
    //active intersection do extra actions
    //specified in the update method
    protected boolean isActive = false;

    public Road(TestTCS owner, RoadType type){
        rType = type;
        this.owner = owner;

        switch (rType){
            case NS:
                leftUp = Lanes.N1;
                leftDown = Lanes.S1;
                midUp = Lanes.N2;
                midDown = Lanes.S2;
                rightUp = Lanes.N3;
                rightDown = Lanes.S3;
                leftPed = Lights.WEST;
                rightPed = Lights.EAST;
                break;

            case WE:
                leftUp = Lanes.E1;
                leftDown = Lanes.W1;
                midUp = Lanes.E2;
                midDown = Lanes.W2;
                rightUp = Lanes.E3;
                rightDown = Lanes.W3;
                leftPed = Lights.NORTH;
                rightPed = Lights.SOUTH;
                break;
        }
    }

    /*
     * returns the wait time for the green light
     *
     * @return
     *     integer value for the wait time in seconds
     */
    public final int getGreenWaitTime(){
        return GREEN_WAIT_TIME;
    }

    /*
     * returns the wait time for the yellow light
     *
     * @return
     *     integer value for the wait time in seconds
     */
    public final int getYellowWaitTime(){
        return YELLOW_WAIT_TIME;
    }

    public void setActive(boolean active){
        this.isActive = active;
    }

    /*
     * This function will poll the lanes and lights to check
     * if there are pedestrians/cars/emergencys present in the lanes.
     * If any one of these are present the method will register
     * this event in TICS via the member variable owner.
     */
    protected final void pollRoad() {
        switch (rType) {
            case NS:
                if (leftUp.getEmergencyOnLane() || leftDown.getEmergencyOnLane()) {
                    //add set emergency logic here
                    break;
                }
                if (leftUp.isCarOnLane()) {
                    owner.setCarTurnN();
                }
                if (leftDown.isCarOnLane()) {
                    owner.setCarTurnS();
                }
                if (midUp.isCarOnLane() || midDown.isCarOnLane() ||
                        rightUp.isCarOnLane() || rightDown.isCarOnLane()) {
                    owner.setCarPresentNS();
                }
                if (leftPed.isPedestrianAt()) {
                    owner.setPedPresentW();
                }
                if (rightPed.isPedestrianAt()) {
                    owner.setPedPresentE();
                }
                break;
            case WE:
                if (leftUp.getEmergencyOnLane() || leftDown.getEmergencyOnLane()) {
                    //add set emergency logic here
                    break;
                }
                if (leftUp.isCarOnLane()) {
                    owner.setCarTurnE();
                }
                if (leftDown.isCarOnLane()) {
                    owner.setCarTurnW();
                }
                if (midUp.isCarOnLane() || midDown.isCarOnLane() ||
                        rightUp.isCarOnLane() || rightDown.isCarOnLane()) {
                    owner.setCarPresentWE();
                }
                if (leftPed.isPedestrianAt()) {
                    owner.setPedPresentN();
                }
                if (rightPed.isPedestrianAt()) {
                    owner.setPedPresentS();
                }
                break;
        }
    }

    public void update(long deltaTime){
        pollRoad();
        if(isActive){
            if(deltaTime - greenSetTime > GREEN_WAIT_TIME){
                owner.setTransition();
            }
        }
        else {

        }
    }
}
