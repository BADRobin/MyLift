package main.java.items;


import main.java.basic.TransportationState;
import main.java.working.Controller;
import main.java.basic.Container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Building {
    private final Elevator elevator;
    private final List<Storey> storeys;
    private final Controller controller;
    private final Properties properties;
    private final Set<Passenger> passengers;

    public Building (Properties properties){
    super();
    this.properties = properties;
        Random random = new Random();
        int storiesNumber = properties.getStoriesNumber();
        elevator = new Elevator(properties.getElevatorCapacity(), random.nextInt(storiesNumber));
        storeys = new ArrayList<>();
            for (int i = 0; i < properties.getStoriesNumber(); i++){
                storeys.add(new Storey(i));
            }
            passengers = new HashSet<>();
        for (int i = 0; i < properties.getPassengersNumber() ; i++) {
            int dispatchStory = random.nextInt(storiesNumber);
            int destinationStory = random.nextInt(storiesNumber - 1);
            if (destinationStory >= dispatchStory) {
                    dispatchStory ++;
            }
            Passenger passenger = new Passenger(i, dispatchStory, destinationStory, Container.DISPATCH_STORY_CONTAINER);
            storeys.get(dispatchStory).addNewPassenger(passenger);
            passengers.add(passenger);
        }
        controller = new Controller(this);
    }

    public Properties getProperties() {
        return properties;
    }

    public Controller getController() {
        return controller;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public List<Storey> getStoreys() {
        return storeys;
    }
    @Override
    public String toString (){
        return "Building [elevator=" + elevator + ", storeys=" + storeys + "]";
    }

    public boolean isValidTransportation(){
        if (!elevator.getElevatorContainer().isEmpty()) {
            return false;
        }
int  passengersNumber =0;
        for (Storey storey: storeys){
            if (!storey.getDispatchStoryContainer().isEmpty()) {
                return false;
            }
            for (Passenger passenger :storey.getArrivalStoryContainer()){
                if (passenger.getTransportationState() != TransportationState.COMPLETED) {
                    return false;
                }
                if (passenger.getDestinationStory() != storey.getStoreyNumber()) {
                    return false;
                }
                passengersNumber++;
            }

        }
        if (passengersNumber != properties.getPassengersNumber()) {
            return false;
        }
        return true;
    }
}
