package main.java.working;

import main.java.basic.Action;
import main.java.basic.Directions;
import main.java.items.Building;
import main.java.items.Elevator;
import main.java.items.Passenger;
import main.java.basic.Container;
import main.java.basic.TransportationState;
import main.java.transaction.ElevatorProtocol;
import java.util.Random;
import java.util.Set;

public class Controller implements Runnable{
    private final Building building;
    private final Elevator elevator;
    private int currentStory;
    private int transferPassengers = 0;
    private final int totalPassengers;
    private final int totalStories;
    private Directions direction;
    private Set<Passenger> elevatorContainer;
    private final int elevatorCapacity =5;
    private final int delay;
    private volatile boolean aborted = false;


    public Controller(Building building) {
        super();
        this.building = building;
        elevator = building.getElevator();
        currentStory = elevator.getCurrentStory();
        totalPassengers = building.getProperties().getPassengersNumber();
        totalStories = building.getStoreys().size();
        elevatorContainer = elevator.getElevatorContainer();
//        elevatorCapacity = elevator.getCapacity();
        delay = building.getProperties().getDelay();
        if(new Random().nextInt(2) == 0){
            direction = Directions.UP;
        }else{
            direction = Directions.DOWN;
        }
    }

    public Building getBuilding() {
        return building;
    }

    public int getTransferPassengers() {
        return transferPassengers;
    }

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public void run(){

        for(Passenger passenger : building.getPassengers()){
            TransportTask task = new TransportTask(passenger, building, this);
            synchronized (building) {
                task.start();
                try {
                    building.wait();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }
        ElevatorProtocol.LOGGER.info(Action.STARTING_TRANSPORTATION);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        // движение лифта
        while (true){
            if (currentStory == 0){
                direction = Directions.UP;
            }
            if (currentStory == totalStories - 1){
                direction = Directions.DOWN;
            }
            try{
                // выгрузка пассажиров из лифта
                synchronized (elevatorContainer) {
                    if(isDeboadingPassenger()){
                        elevatorContainer.notifyAll();
                    }
                    while(isDeboadingPassenger()){
                        elevatorContainer.wait();
                    }
                }

                Set<Passenger> dispatchStoryContainer =
                        building.getStoreys().get(currentStory).getDispatchStoryContainer();

                // погрузка пассажиров в лифт
                synchronized (dispatchStoryContainer) {
                    if(isBoadingPassenger(dispatchStoryContainer)){
                        dispatchStoryContainer.notifyAll();
                    }
                    while(isBoadingPassenger(dispatchStoryContainer)){
                        dispatchStoryContainer.wait();
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            // окончание движения
            if(transferPassengers == totalPassengers){
                break;
            }
            if(aborted){
                return;
            }

            switch (direction) {
                case UP:
                    currentStory++;
                    break;
                case DOWN:
                    currentStory--;
                    break;
            }
            ElevatorProtocol.LOGGER.info(Action.MOVING_ELEVATOR + getLoggingMessage());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            elevator.setCurrentStory(currentStory);
        }

        if(building.isValidTransportation()){
            ElevatorProtocol.LOGGER.info(Action.VALID_TRANSPORTATION);
        }
        ElevatorProtocol.LOGGER.info(Action.COMPLETION_TRANSPORTATION);
    }


    public synchronized boolean boardingPassenger(Passenger passenger){
        if(aborted){
            return false;
        }
        if(passenger.getTransportTask().getDirection() == direction &&
                elevatorContainer.size() < elevatorCapacity){
            ElevatorProtocol.LOGGER.info(Action.BOARDING_OF_PASSENGER + getLoggingMessage(passenger));
            elevator.boardingPassenger(passenger);
            building.getStoreys().get(passenger.getDispatchStory()).boardingPassenger(passenger);
            passenger.setCurrentContainer(Container.ELEVATOR_CONTAINER);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            return true;
        }else{
            return false;
        }
    }


    public synchronized boolean deboardingPassenger(Passenger passenger){
        if(aborted){
            return false;
        }
        if(passenger.getDestinationStory() == currentStory){
            ElevatorProtocol.LOGGER.info(Action.DEBOARDING_OF_PASSENGER + getLoggingMessage(passenger));
            building.getStoreys().get(currentStory).deboardingPassenger(passenger);;
            elevator.deboardingPassenger(passenger);
            passenger.setCurrentContainer(Container.ARRIVAL_STORY_CONTAINER);
            transferPassengers++;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            return true;
        }else{
            return false;
        }
    }


    public void abortTransportation(){
        aborted = true;
        ElevatorProtocol.LOGGER.info(Action.ABORTING_TRANSPORTATION);
        for(Passenger passenger : building.getPassengers()){
            if(TransportationState.COMPLETED != passenger.getTransportationState()){
                passenger.getTransportTask().interrupt();
            }
            ElevatorProtocol.LOGGER.info(passenger.getCurrentState());
        }
    }

    private boolean isBoadingPassenger(Set<Passenger> dispatchStoryContainer){
        if(elevatorContainer.size() == elevatorCapacity){
            return false;
        }
        for(Passenger passenger : dispatchStoryContainer){
            if(passenger.getTransportTask().getDirection() == direction){
                return true;
            }
        }
        return false;
    }

    private boolean isDeboadingPassenger(){
        if(elevatorContainer.isEmpty()){
            return false;
        }
        for(Passenger passenger : elevatorContainer){
            if(passenger.getDestinationStory() == currentStory){
                return true;
            }
        }
        return false;
    }

    private String getLoggingMessage(){
        int previousStory;
        if (direction == Directions.UP){
            previousStory = currentStory - 1;
        }else {
            previousStory = currentStory + 1;
        }
        return " (from story-" + previousStory + " to story-" + currentStory + ")";
    }

    private String getLoggingMessage(Passenger passenger){
        return " (passanger" + passenger.getId() + " on story-" + currentStory + ")";
    }
}