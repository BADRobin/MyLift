package main.java.items;

public class Properties {
    private static final int BASE_DELAY =1001;
    private static final int DELAY_MULTIPLIER = 10;
    private int storiesNumber;
    private int elevatorCapacity;
    private int passengersNumber;
    private int animationBoost;


    public Properties(int storiesNumber, int elevatorCapacity, int passengersNumber, int animationBoost){
        super();
        this.storiesNumber = storiesNumber;
        this.elevatorCapacity = elevatorCapacity;
        this.passengersNumber = passengersNumber;
        this.animationBoost = animationBoost;
    }

    public int getStoriesNumber() {
        return storiesNumber;
    }

    public void setStoriesNumber(int storiesNumber) {
        this.storiesNumber = storiesNumber;
    }

    public int getElevatorCapacity() {
        return elevatorCapacity;
    }

    public void setElevatorCapacity(int elevatorCapacity) {
        this.elevatorCapacity = elevatorCapacity;
    }

    public int getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(int passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

    public int getAnimationBoost() {
        return animationBoost;
    }

    public void setAnimationBoost(int animationBoost) {
        this.animationBoost = animationBoost;
    }

    public int getDelay(){
    if (animationBoost > 100 || animationBoost == 0){
        return 0;
    }
    return BASE_DELAY - animationBoost *DELAY_MULTIPLIER;
    }

}
