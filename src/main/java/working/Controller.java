package main.java.working;

import com.sun.javafx.scene.traversal.Direction;
import main.java.basic.Directions;
import main.java.items.Building;
import main.java.items.Elevator;
import main.java.items.Passenger;

import java.util.Random;
import java.util.Set;

public class Controller implements Runnable {
    private final Building building;
    private final Elevator elevator;
    private int currentStory;
    private int transferPassenger = 0;
    private final totalPassengers;
    private final totalStories;
    private Directions direction;
    private Set<Passenger> eleveitorConteiner;
    private final int elevaitorCapacity;
    private final int delay;
    private volatile boolean aborted = false;


    public Controller(Building building, Elevator elevator, int currentStory, int elevaitorCapacity, int delay) {
        super();
        this.building = building;
        totalPassengers = building.getProperties().getPassengersNumber();
        currentStory = elevator.getCurrentStory();
        totalStories = building.getTotalStoriys().size();
        elevator = building.getElevator();
        elevaitorCapacity = elevator.getCapacity();
        eleveitorConteiner = elevator.getElevatorConteiner();
        delay = building.getProperties().getDelay();
            if (new Random().nextInt(2)==0){
                    direction = Direction.UP;
            }else {
                    direction = Directions.DOWN;
            }
    }

    public Building getBuilding() {
        return building;
    }

    public int getTransferPassenger() {
        return transferPassenger;
    }
    public int getTotalPassengers() {
        return totalPassengers;
    }
    public void run(){
        for (Passenger passenger : building.get)
    }
}
