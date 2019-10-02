package main.java.items;

import main.java.basic.Container;
import main.java.basic.TransportationState;
import main.java.working.TransportTask;

public class Passenger {
    private final int id;
    private final int dispatchStory;
    private final int destinationStory;
    private TransportTask transportTask;
    private TransportationState transportationState;
    private Container currentContainer;

    public Passenger(int id, int dispatchStory, int destinationStory,  Container currentContainer) {
        super();
        this.id = id;
        this.dispatchStory = dispatchStory;
        this.destinationStory = destinationStory;
        transportationState = transportationState.NON_STARTED;
        this.currentContainer = currentContainer;
    }
    public TransportationState getTransportationState(){
        return transportationState;
    }
    public void setTransportationState(TransportationState transportationState){
        this.transportationState = transportationState;
    }
    public TransportTask getTransportTask() {
        return transportTask;
    }

    public void setTransportTask (TransportTask transportTask){
        this.transportTask = transportTask;
    }

    public int getId() {
        return id;
    }

    public int getDispatchStory() {
        return dispatchStory;
    }

    public int getDestinationStory() {
        return destinationStory;
    }
    public Container getCurrentContainer(){
        return currentContainer;
    }
    public void setCurrentContainer(Container currentContainer) {
        this.currentContainer = currentContainer;
    }

    @Override
    public String toString(){
        return "Passenger [id=" + id + ", dispatchStory=" + dispatchStory +  ", destinationStory=" + destinationStory
                + ", transportationState=" +transportationState + "]";

    }
    @Override
        public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
        public boolean equals(Object obj){
        if (this == obj)
        return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
    Passenger other = (Passenger) obj;
        if (id != other.id)
            return false;
        return true;

        }

    public String getCurrentState(){
        return "Passenger" +id + "in" + currentContainer + "transportation state" + transportationState;
    }



}
