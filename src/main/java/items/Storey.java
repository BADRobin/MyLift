package main.java.items;

import java.util.HashSet;
import java.util.Set;

public class Storey {
    private final int storeyNumber;
    private Set<Passenger> dispatchStoryContainer;
    private Set<Passenger> arrivalStoryContainer;


    public Storey(int storeyNumber) {
        super();
        this.storeyNumber = storeyNumber;
        dispatchStoryContainer = new HashSet<>();
        arrivalStoryContainer = new HashSet<>();
    }

    public void addNewPassenger(Passenger passenger){
        dispatchStoryContainer.add(passenger);
    }

    public Set<Passenger> getDispatchStoryContainer() {
        return dispatchStoryContainer;
    }

    public Set<Passenger> getArrivalStoryContainer() {
        return arrivalStoryContainer;
    }

    public int getStoreyNumber() {
        return storeyNumber;
    }


    public void boardingPassenger(Passenger passenger){
        dispatchStoryContainer.remove(passenger);
    }

    public void deboardingPassenger(Passenger passenger){
        arrivalStoryContainer.add(passenger);
    }

    @Override
    public String toString() {
        return "Storey [storeyNumber=" + storeyNumber
                + ", dispatchStoryContainer=" + dispatchStoryContainer
                + ", arrivalStoryContainer=" + arrivalStoryContainer + "]";
    }
}
