package main.java.basic;

public enum Container {
    DISPATCH_STORY_CONTAINER("dispatchStoryContainer"),
    ELEVATOR_CONTAINER("elevatorContainer"),
    ARRIVAL_STORY_CONTAINER("arrivalStoryContainer");

    private String containerName;

    private Container(String containerName){
        this.containerName=containerName;
    }
    @Override
    public String toString(){
        return containerName;
    }
}

