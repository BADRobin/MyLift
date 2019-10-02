package main.java.items;

import java.util.HashSet;
import java.util.Set;

public class Elevator {
    private final int capacity;
    private int currentStory;
    private Set<Passenger> elevatorConteiner;



    public Elevator(int capacity, int currentStory) {
        this.capacity = capacity;
        this.currentStory = currentStory;
        elevatorConteiner = new HashSet<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentStory() {
        return currentStory;
    }

    public void setCurrentStory(int currentStory) {
        this.currentStory = currentStory;
    }

    public Set<Passenger> getElevatorConteiner() {
        return elevatorConteiner;
    }

        public void boardingPassenger(Passenger passenger){
            elevatorConteiner.add(passenger);
        }
        public void deboardingPassenger (Passenger passenger){
            elevatorConteiner.remove(passenger);
        }
@Override
public String toString(){
return "Elevator container [capacity=" + capacity + ", current story" + currentStory
        + "elevator conteiner" + elevatorConteiner + "]";
}
}

