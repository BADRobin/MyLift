package main.java.working;

import main.java.basic.TransportationState;
import main.java.items.Building;
import main.java.items.Passenger;
import main.java.basic.Directions;
import java.util.Set;

public class TransportTask extends Thread {
    private final Passenger passenger;
    private final Building building;
    private final Controller controller;
    private Set<Passenger> elevatorContainer;
    private Set<Passenger> dispatchStoryContainer;
    private Directions direction;

    public TransportTask(Passenger passenger, Building building, Controller controller) {
        super();
        this.passenger = passenger;
        passenger.setTransportTask(this);
        this.building = building;
        this.controller = controller;
        elevatorContainer = building.getElevator().getElevatorContainer();
        dispatchStoryContainer = building.getStoreys().get(passenger.getDispatchStory()).getDispatchStoryContainer();
        if (passenger.getDestinationStory() >passenger.getDispatchStory()) {
                direction = Directions.UP;
        }else{
            direction = Directions.DOWN;
        }
    }

@Override
    public void run(){


    try{
        synchronized (building) {
            passenger.setTransportationState(TransportationState.IN_PROGRESS);
            building.notify();
        }
        synchronized (dispatchStoryContainer) {
            dispatchStoryContainer.wait();
            while(!controller.boardingPassenger(passenger)){
                dispatchStoryContainer.wait();
            }
            dispatchStoryContainer.notifyAll();
        }
        synchronized (elevatorContainer) {
            elevatorContainer.wait();
            while(!controller.deboardingPassenger(passenger)){
                elevatorContainer.wait();
            }
            elevatorContainer.notifyAll();
        }


    }catch(InterruptedException e){
        e.printStackTrace();
    }

    passenger.setTransportationState(TransportationState.COMPLETED);
}

    public Directions getDirection() {
        return direction;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public void interrupt() {

        super.interrupt();
        passenger.setTransportationState(TransportationState.ABORTED);
    }
}

