package main.java.ui;

import main.java.items.Passenger;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class PresentationStorey extends JPanel {
    private static final long serialVersionUID = -8503820548929834090L;
    private static final int PASSENGERS_VIEW_MAX = 5;
    private static int passengersViewNumber = 1;
    private StoreyContainer dispathStoryContainer;
    private StoreyContainer arrivalStoryContainer;
    private ElevatorContainer elevatorContainer;

    public PresentationStorey(int storeyNumber, Set<Passenger> dispathStory, Set<Passenger> arrivalStory) {
        super();
        setBackground(Color.white);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, UIDimensions.PASSENGERS_CONTAINER_WIDTH));
        JLabel jLabel = new JLabel(String.valueOf(storeyNumber));
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(UIDimensions.PRESENTATION_STORY_WIDTH, UIDimensions.PASSENGERS_CONTAINER_WIDTH));
        jPanel.add(jLabel);

        dispathStoryContainer = new StoreyContainer(dispathStory);
        arrivalStoryContainer = new StoreyContainer(arrivalStory);

        elevatorContainer = new ElevatorContainer(null, passengersViewNumber);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(jPanel);
        add(arrivalStoryContainer);
        add(elevatorContainer);
        add(dispathStoryContainer);
        setVisible(true);
    }

    public void update(Set<Passenger> dispathStory, Set<Passenger> arrivalStory) {
        dispathStoryContainer.setPassengers(dispathStory);
        arrivalStoryContainer.setPassengers(arrivalStory);
        elevatorContainer.setPassengers(null);
    }

    public void setElevatorPassengers(Set<Passenger> elevatorStory) {
        elevatorContainer.setPassengers(elevatorStory);
    }


    public static void setPassengersViewNumber(int capacity) {
        if(capacity < PASSENGERS_VIEW_MAX){
            passengersViewNumber = capacity;
        }else{
            passengersViewNumber = PASSENGERS_VIEW_MAX;
        }
    }
}
